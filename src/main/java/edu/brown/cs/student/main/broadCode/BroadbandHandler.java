package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.requestCode.stateCodesAPIUtilities;
import edu.brown.cs.student.main.server.Datasource;
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
    String stateName = request.queryParams("state");
    String countyName = request.queryParams("county");
    if (stateName == null) {
      System.err.println("bad request: missing state name");
      responseMap.put("result", "error_bad_request");
      return responseMap;
    }
    if (countyName == null) {
      System.err.println("bad request: missing county name");
      responseMap.put("result", "error_bad_request");
      return responseMap;
    }
    responseMap.put("state name", stateName);
    responseMap.put("county name", countyName);

    try {
      String stateCodesJson = dataSource.getStateCodes(); // Census API call
      Map<String, String> stateMap = stateCodesAPIUtilities.deserializeStates(stateCodesJson);
      String stateCode = stateMap.get(stateName);
      if (stateCode == null) {
        System.err.println("bad request: invalid state");
        responseMap.put("result", "error_bad_request");
        return responseMap;
      }

      String countyCodesJson = dataSource.getCountyCodes(stateCode); // Census API call
      Map<String, String> countyMap = stateCodesAPIUtilities.deserializeCounties(countyCodesJson);
      String countyCode = countyMap.get(countyName + ", " + stateName);
      if (countyCode == null) {
        System.err.println("bad request: invalid county");
        responseMap.put("result", "error_bad_request");
        return responseMap;
      }

      Map<String, Object> fromCache =
          dataSource.getBroadband(countyCode, stateCode); // Census API call

      return fromCache;
      //      responseMap.put("result", "success");
      //      responseMap.put("broadband", codeJson);
      //      responseMap.put("time", LocalDateTime.now());
    } catch (Exception e) {

    }
    return responseMap;
  }
}
