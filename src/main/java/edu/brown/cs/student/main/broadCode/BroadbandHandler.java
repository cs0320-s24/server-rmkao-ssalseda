package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.requestCode.stateCodesAPIUtilities;
import edu.brown.cs.student.main.server.Datasource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import spark.Request;
import spark.Response;
import spark.Route;

public class BroadbandHandler implements Route {
  // must return data given state and county parameters

  //  To keep the format simple, all responses must be serializations of a Map<String, Object>
  // object.
  //  For all replies, the map must contain a "result" field with value "success" in case of success
  //  or an error code in the case of an error: (see sprint doc)
  private Datasource dataSource = new MockedDatasource();

  @Override
  public Object handle(Request request, Response response) {
    Set<String> params = request.queryParams();
    System.out.println(params);
    String county = request.queryParams("county");
    System.out.println(county);
    String state = request.queryParams("state");
    System.out.println(state);

    // Creates a hashmap to store the results of the request
    Map<String, Object> responseMap = new HashMap<>();
    try {
      String codeJson = dataSource.getData(county, state);
      // Deserializes JSON into rows of data
      List<List<String>> code = stateCodesAPIUtilities.deserializeCode(codeJson);
      responseMap.put("result", "success");
      responseMap.put("codeMatch", code);
    } catch (Exception e) {
      e.printStackTrace();
      responseMap.put("result", "Exception");
    }
    return responseMap;
  }
}
