package edu.brown.cs.student.main.loadCode;

import edu.brown.cs.student.main.GlobalGlove;
import edu.brown.cs.student.main.csvparser.CSVParser;
import edu.brown.cs.student.main.csvparser.RowCreator;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This class is used to illustrate how to build and send a GET request then prints the response. It
 * will also demonstrate a simple Moshi deserialization from online data.
 */
// TODO 1: Check out this Handler. How can we make it only get activities based on participant #?
// See Documentation here: https://www.boredapi.com/documentation

// http://localhost:3232/loadcsv?filepath=Hello_world
public class LoadHandler implements Route {
  private GlobalGlove global;

  public LoadHandler(GlobalGlove global) {
    this.global = global;
  }

  @Override
  public Object handle(Request request, Response response) throws Exception {
    String filePath = request.queryParams("filepath");

    Map<String, Object> responseMap = new HashMap<>();

    try (FileReader transit = new FileReader(filePath)) {

      CSVParser<List<String>> parser = new CSVParser(transit, new RowCreator());
      this.global.setGlobalFile(parser.parse());
      responseMap.put("result", "success");
      responseMap.put("activity", filePath);
      responseMap.put("time", LocalDateTime.now());
      this.global.setTime();

      return responseMap;

      // map indicating success and time only
    } catch (Exception e) {
      e.printStackTrace();
      // This is a relatively unhelpful exception message. An important part of this sprint will be
      // in learning to debug correctly by creating your own informative error messages where Spark
      // falls short.
      responseMap.put("result", "File not found");
      responseMap.put("time", LocalDateTime.now());
      return responseMap;
    }
  }
}
