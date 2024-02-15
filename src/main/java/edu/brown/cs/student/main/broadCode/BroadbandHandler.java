package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.requestCode.stateCodesAPIUtilities;
import edu.brown.cs.student.main.server.Datasource;
import java.time.LocalDateTime;
import java.util.HashMap;
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
  private Datasource dataSource;

  public BroadbandHandler(Datasource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Object handle(Request request, Response response) {
    Map<String, Object> responseMap = new HashMap<>();

    Set<String> params = request.queryParams();
    System.out.println(params);
    String county = request.queryParams("county");
    System.out.println(county);
    String state = request.queryParams("state");
    System.out.println(state);

    // add inputted query fields to response map
    responseMap.put("state name", state);
    responseMap.put("county name", county);

    // Query for map of state name to state code
    try {
      String stateCodesJson = dataSource.getStateCodes();
      Map<String, String> stateMap = stateCodesAPIUtilities.deserializeCode(stateCodesJson);
    } catch (Exception e) {

    }

    // We need to do this for counties as well
    // state = statesMap.get(state)
    // county = countiesMap.get(county)
    // if either is null, then error_bad_request

    try {
      String codeJson = dataSource.getData(county, state);
      responseMap.put("result", "success");
      responseMap.put("time", LocalDateTime.now());
      // Deserialize JSON into rows of data
      //      Map<String, String> code = stateCodesAPIUtilities.deserializeCode(codeJson);
      //
      //      responseMap.put("codeMatch", code);
    } catch (Exception e) {
      e.printStackTrace();
      responseMap.put("result", "error_datasource");
    }
    return responseMap;
  }
}
