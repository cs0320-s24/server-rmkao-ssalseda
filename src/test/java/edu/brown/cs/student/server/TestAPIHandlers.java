package edu.brown.cs.student.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import edu.brown.cs.student.main.GlobalCache;
import edu.brown.cs.student.main.GlobalGlove;
import edu.brown.cs.student.main.broadCode.BroadbandHandler;
import edu.brown.cs.student.main.broadCode.MockedDatasource;
import edu.brown.cs.student.main.loadCode.LoadHandler;
import edu.brown.cs.student.main.searchCode.SearchHandler;
import edu.brown.cs.student.main.server.WebAPIResponse;
import edu.brown.cs.student.main.viewCode.ViewHandler;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Spark;

public class TestAPIHandlers {
  private JsonAdapter<WebAPIResponse> adapter;
  private GlobalCache globalCache;
  private GlobalGlove globalFile;

  public TestAPIHandlers() {
    Moshi moshi = new Moshi.Builder().build();
    this.adapter = moshi.adapter(WebAPIResponse.class);

    this.globalCache = new GlobalCache();
    this.globalFile = new GlobalGlove();
  }

  @BeforeAll
  public static void initial_setup() {
    Spark.port(0);

    Logger.getLogger("").setLevel(Level.WARNING); // empty name = root logger
  }

  @BeforeEach
  public void setup() {
    // setup like we do in main in Server.java
    GlobalGlove globalFile = new GlobalGlove();
    GlobalCache PlentyCache = new GlobalCache();

    Spark.get("loadcsv", new LoadHandler(globalFile));
    Spark.get("viewcsv", new ViewHandler(globalFile));
    Spark.get("searchcsv", new SearchHandler(globalFile));
    Spark.get("broadband", new BroadbandHandler(new MockedDatasource(), this.globalCache));

    Spark.init();
    Spark.awaitInitialization();
  }

  @AfterEach
  public void teardown() {
    Spark.unmap("loadcsv");
    Spark.unmap("viewcsv");
    Spark.unmap("searchcsv");
    Spark.unmap("broadband");
    Spark.awaitStop();
  }

  private static HttpURLConnection tryRequest(String request) throws IOException {
    URL requestURL = new URL("http://localhost:" + Spark.port() + "/" + request);
    HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();

    clientConnection.setRequestMethod("GET");

    clientConnection.connect();
    return clientConnection;
  }

  @Test
  public void testLoadCSV() {
    try {
      HttpURLConnection clientConnection =
          tryRequest("loadcsv?filepath=src/test/csv/postsecondary_education.csv");
      assertEquals(200, clientConnection.getResponseCode());
      WebAPIResponse response =
          adapter.fromJson(new Buffer().readFrom(clientConnection.getInputStream()));
      Map<String, Object> responseMap = response.responseMap();
      assertEquals("success", responseMap.get("result"));
      assertEquals("src/test/csv/postsecondary_education.csv", responseMap.get("activity"));
    } catch (IOException e) {
      System.err.println("testLoadCSV failed");
    }
  }

  @Test
  public void testViewCSV() {
    // construct csv as list of list of strings
    List<List<String>> data = new ArrayList<>();
    List<String> row1 = new ArrayList<>();
    row1.add("header1");
    row1.add("header2");
    row1.add("header3");
    List<String> row2 = new ArrayList<>();
    row2.add("thing1");
    row2.add("thing2");
    row2.add("thing3");
    data.add(row1);
    data.add(row2);
    try {
      // load simple csv
      HttpURLConnection load = tryRequest("loadcsv?filepath=src/test/csv/simple.csv");
      assertEquals(200, load.getResponseCode());

      // call viewcsv
      HttpURLConnection view = tryRequest("viewcsv");
      assertEquals(200, view.getResponseCode());
      WebAPIResponse response = adapter.fromJson(new Buffer().readFrom(view.getInputStream()));
      Map<String, Object> responseMap = response.responseMap();

      // compare results
      assertEquals("success", responseMap.get("result"));
      assertEquals(data, responseMap.get("data"));
    } catch (IOException e) {
      System.err.println("testViewCSV failed");
    }
  }

  @Test
  public void testSearchCSV() {
    // construct correct search answer using list of list of strings
    List<List<String>> data = new ArrayList<>();
    List<String> row = new ArrayList<>();
    row.add("thing1");
    row.add("thing2");
    row.add("thing3");
    data.add(row);

    try {
      // load simple csv
      HttpURLConnection load = tryRequest("loadcsv?filepath=src/test/csv/simple.csv");
      assertEquals(200, load.getResponseCode());

      // call searchcsv
      HttpURLConnection search =
          tryRequest("searchcsv?searchby=name&index=header2&header=true&term=thing2");
      assertEquals(200, search.getResponseCode());
      WebAPIResponse response = adapter.fromJson(new Buffer().readFrom(search.getInputStream()));
      Map<String, Object> responseMap = response.responseMap();

      // compare results
      assertEquals("success", responseMap.get("result"));
      assertEquals(data, responseMap.get("data"));
    } catch (IOException e) {
      System.err.println("testViewCSV failed");
    }
  }
}
