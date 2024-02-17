package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.requestCode.stateCodesAPIUtilities;
import edu.brown.cs.student.main.server.Datasource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This class is the handler for the 'broadband' endpoint. It finds percentage of broadband coverage
 * in a specific county in the United States by using API calls to the US census.
 */
public class BroadbandHandler implements Route {
  private Datasource dataSource;

  /**
   * In this constructor we save our datasource as an instance variable
   *
   * @param dataSource the source of data
   */
  public BroadbandHandler(Datasource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * This function uses API calls to convert state name and county name into state code and county
   * code, then finally queries for broadband coverage using those codes.
   *
   * @return a map containing the time the date and time all data was retrieved, whether the
   *     retrieval was successful, and the matching data that was retrieved (percentage broadband
   *     internet subscription)
   */
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
    } catch (IndexOutOfBoundsException | IOException e) {
      System.err.println(e.getMessage());
      responseMap.put("result", "error_bad_json");
    } catch (URISyntaxException e) {
      System.err.println("Bad request: " + e.getMessage());
      responseMap.put("result", "error_bad_request");
    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e.getMessage());
      responseMap.put("result", "error_datasource");
    }
    return responseMap;
  }
}
