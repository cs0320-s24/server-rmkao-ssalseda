package edu.brown.cs.student.main.viewCode;

import edu.brown.cs.student.main.GlobalGlove;
import edu.brown.cs.student.main.server.JsonConverter;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This class handles the 'viewcsv' endpoint.
 */
public class ViewHandler implements Route {
  private GlobalGlove global;

  /**
   * This constructor saves the global loaded file object locally
   * @param globalFile the global loaded file object
   */
  public ViewHandler(GlobalGlove globalFile) {
    this.global = globalFile;
  }

  /**
   * This method converts the loaded csv into a JSON and displays it through the response map
   * @return a response map containing the result status, the json data, and the time of retrieval
   * @throws Exception
   */
  @Override
  public Object handle(Request request, Response response) throws Exception {
    Map<String, Object> responseMap = new HashMap<>();
    if (this.global.checkFull()) {
      responseMap.put("result", "no file loaded");
      return responseMap;
    }

    responseMap.put("result", "success");
    responseMap.put("data", JsonConverter.serializeToJson(global.getGlobalFile()));
    responseMap.put("time of retrieval", this.global.getTime());
    return responseMap;
  }
  // converts load CSV file to JSON and sends it

  //  To keep the format simple, all responses must be serializations of a Map<String, Object>
  // object.
  //  For all replies, the map must contain a "result" field with value "success" in case of success
  //  or an error code in the case of an error: (see sprint doc)

}
