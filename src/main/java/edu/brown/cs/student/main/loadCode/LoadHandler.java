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
 * this handler serves the purposes of loading files of the format
 * http://localhost:3232/loadcsv?filepath=Data/run
 * http://localhost:3232/loadcsv?filepath=/Users/samsalseda/Desktop/cs%20320/server-rmkao-ssalseda/src/test/postsecondary_education.csv
 */
//
public class LoadHandler implements Route {
  private GlobalGlove global;
  // set up global variable to load in data
  public LoadHandler(GlobalGlove global) {
    this.global = global;
  }

  /**
   * find, load, and parse any requested CSV file
   *
   * @param request
   * @param response
   * @return a response map with the proper file
   */
  @Override
  public Object handle(Request request, Response response) {
    String filePath = request.queryParams("filepath");

    Map<String, Object> responseMap = new HashMap<>();

    try (FileReader transit = new FileReader(filePath)) {
      // parse is a success
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
      // it was not found
      responseMap.put("result", "File not found");
      responseMap.put("time", LocalDateTime.now());
      return responseMap;
    }
  }
}
