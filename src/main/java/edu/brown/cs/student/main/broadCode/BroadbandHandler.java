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
    String stateName = request.queryParams("state");
    String countyName = request.queryParams("county");
    responseMap.put("state name", stateName);
    responseMap.put("county name", countyName);

    try {
      String stateCodesJson = dataSource.getStateCodes();
      Map<String, String> stateMap = stateCodesAPIUtilities.deserializeStates(stateCodesJson);
      String stateCode = stateMap.get(stateName);

      String countyCodesJson = dataSource.getCountyCodes(stateCode);
      Map<String, String> countyMap = stateCodesAPIUtilities.deserializeCounties(countyCodesJson);
      String countyCode = countyMap.get(countyName + ", " + stateName);

      String codeJson = dataSource.getBroadband(countyCode, stateCode);
      responseMap.put("result", "success");
      responseMap.put("broadband", codeJson);
      responseMap.put("time", LocalDateTime.now());
    } catch (Exception e) {

    }
    return responseMap;
  }
}
