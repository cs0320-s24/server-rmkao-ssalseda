package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.requestCode.stateCodesAPIUtilities;
import edu.brown.cs.student.main.server.Datasource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
   * @param dataSource the source of data
   */
  public BroadbandHandler(Datasource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * This function uses API calls to convert state name and county name into state code and county
   * code, then finally queries for broadband coverage using those codes.
   * @return a map containing the time the date and time all data was retrieved, whether the
   * retrieval was successful, and the matching data that was retrieved (percentage broadband
   * internet subscription)
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
      String stateQuery = "https://api.census.gov/data/2010/dec/sf1?get=NAME&for=state:*";
      String stateCodesJson = dataSource.getData(stateQuery); // Census API call for state codes
      Map<String, String> stateMap = stateCodesAPIUtilities.deserializeStates(stateCodesJson);
      String stateCode = stateMap.get(stateName);
      if (stateCode == null) {
        System.err.println("bad request: invalid state");
        responseMap.put("result", "error_bad_request");
        return responseMap;
      }

      String countyQuery =
          "https://api.census.gov/data/2010/dec/sf1?get=NAME&for=county:*&in=state:" + stateCode;
      String countyCodesJson = dataSource.getData(countyQuery); // Census API call
      Map<String, String> countyMap = stateCodesAPIUtilities.deserializeCounties(countyCodesJson);
      String countyCode = countyMap.get(countyName + ", " + stateName);
      if (countyCode == null) {
        System.err.println("bad request: invalid county");
        responseMap.put("result", "error_bad_request");
        return responseMap;
      }

      String broadbandQuery =
          "https://api.census.gov/data/2021/acs/acs1/subject/variables?get=NAME,S2802_C03_022E&for=county:"
              + countyCode
              + "&in=state:"
              + stateCode;
      String codeJson = dataSource.getData(broadbandQuery); // Census API call
      responseMap.put("result", "success");
      responseMap.put("broadband", codeJson);
      responseMap.put("time", LocalDateTime.now());
    } catch (IndexOutOfBoundsException | IOException e) {
      System.err.println(e.getMessage());
      responseMap.put("result", "error_bad_json");
    } catch (URISyntaxException e) {
      System.err.println("Bad request: " + e.getMessage());
      responseMap.put("result", "error_bad_request");
    } catch (InterruptedException e) {

    }
    return responseMap;
  }
}
