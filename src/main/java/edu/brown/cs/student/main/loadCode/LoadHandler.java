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
 * This class handles the 'loadcsv' endpoint.
 */
public class LoadHandler implements Route {
  private GlobalGlove global;

  /**
   * This constructor saves the global loaded file object locally
   * @param global the global loaded file object
   */
  public LoadHandler(GlobalGlove global) {
    this.global = global;
  }

  /**
   * This method uses a CSVParser to parse a given filepath and then saves the result to
   * a global object.
   * @return a map containing whether the loading was successful, the filepath, and the time the
   * file was loaded.
   * @throws Exception if the file could not be found
   */
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
    } catch (Exception e) {
      e.printStackTrace();
      responseMap.put("result", "File not found");
      responseMap.put("time", LocalDateTime.now());
      return responseMap;
    }
  }
}
