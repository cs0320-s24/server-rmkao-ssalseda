package edu.brown.cs.student.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.GlobalCache;
import edu.brown.cs.student.main.GlobalGlove;
import edu.brown.cs.student.main.broadCode.BroadbandHandler;
import edu.brown.cs.student.main.broadCode.MockedDatasource;
import edu.brown.cs.student.main.loadCode.LoadHandler;
import edu.brown.cs.student.main.searchCode.SearchHandler;
import edu.brown.cs.student.main.viewCode.ViewHandler;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import spark.Spark;

public class TestAPIHandlers {
  private JsonAdapter<Map<String, Object>> adapter;
  private GlobalCache globalCache;
  private GlobalGlove globalFile;

  public TestAPIHandlers() {
    Moshi moshi = new Moshi.Builder().build();
    Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
    this.adapter = moshi.adapter(type);

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

  public void testLoadCSV() throws IOException {
    HttpURLConnection clientConnection =
        tryRequest("loadcsv?filepath=src/test/postsecondary_education.csv");
    assertEquals(200, clientConnection.getResponseCode());
    Map<String, Object> response =
        adapter.fromJson(new Buffer().readFrom(clientConnection.getInputStream()));
    assertEquals("success", response.get("result"));
  }
}
