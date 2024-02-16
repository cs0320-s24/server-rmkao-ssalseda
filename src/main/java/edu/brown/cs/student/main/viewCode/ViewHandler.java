package edu.brown.cs.student.main.viewCode;

import edu.brown.cs.student.main.GlobalGlove;
import edu.brown.cs.student.main.server.JsonConverter;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * this handler is in charge of allowing the end user to see the entirety of the data that was
 * loaded by the API. it is simply accessed by http://localhost:3232/viewcsv
 */
public class ViewHandler implements Route {
  private GlobalGlove global;

  /**
   * initializes file to be viewed
   *
   * @param globalFile loads file
   */
  public ViewHandler(GlobalGlove globalFile) {
    this.global = globalFile;
  }

  /**
   * Handle does the bulk of the work to transfer the file into a responseMap ready for deployment
   *
   * @param request
   * @param response
   * @return the responseMap for printing
   * @throws Exception
   */
  @Override
  public Object handle(Request request, Response response) throws Exception {
    Map<String, Object> responseMap = new HashMap<>();
    // confirms file is loaded.
    if (this.global.checkFull()) {
      responseMap.put("result", "No file uploaded! Please use the loadcsv endpoint");
      return responseMap;
    }

    responseMap.put("result", "success");
    responseMap.put("data", JsonConverter.serializeToJson(global.getGlobalFile()));
    responseMap.put("time of retrieval", this.global.getTime());
    return responseMap;
  }
}
