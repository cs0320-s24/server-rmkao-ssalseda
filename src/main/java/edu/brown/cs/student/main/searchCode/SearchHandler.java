package edu.brown.cs.student.main.searchCode;

import edu.brown.cs.student.main.GlobalGlove;
import edu.brown.cs.student.main.csvparser.Search;
import edu.brown.cs.student.main.csvparser.SearchType;
import edu.brown.cs.student.main.server.JsonConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

/** Handles all things related to searching a loaded CSV file. */
public class SearchHandler implements Route {
  private GlobalGlove global;

  /**
   * initializes the global data as it will be used to search
   *
   * @param globalFile
   */
  public SearchHandler(GlobalGlove globalFile) {
    this.global = globalFile;
  }

  /*
    this is the most complicated of the link forms. It must follow the format of
    http://localhost:3232/searchcsv?searchby=    &index=    &header=    &term=   \
    Where searchBy is either "column", "all", or "name".
    index is a number a header name or "none"
    header is "true" or "false" indicating the presence of headers.
    term is the search term.

    for example:
  http://localhost:3232/searchcsv?searchby=all&index=1Race&header=false&term=Asian

  http://localhost:3232/searchcsv?searchby=column&index=8&header=false&term=men


     */

  /**
   * Returns a responseMap with the requested information to be passed ot viewcsv.
   *
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Override
  public Object handle(Request request, Response response) throws Exception {
    Map<String, Object> responseMap = new HashMap<>();
    // check to see if a file has been loaded and advise.
    if (this.global.checkFull()) {
      responseMap.put("result", "No File Loaded. Please Use the loadcsv endpoint");
      return responseMap;
    }
    // converting all string inputs to their required types for pre-made search code.
    String searchBy = request.queryParams("searchby").toLowerCase();
    String index = request.queryParams("index").toLowerCase();
    String header = request.queryParams("header").toLowerCase();
    String term = request.queryParams("term").toLowerCase();
    SearchType searchType = null;
    switch (searchBy) {
      case "column":
        searchType = SearchType.COLUMN;
        break;
      case "all":
        searchType = SearchType.ALL;
        break;
      case "name":
        searchType = SearchType.NAME;
        break;
      default:
        System.out.println("Please enter a valid input!\n");
        break;
    }

    try {
      // Create a Search object
      Search searcher =
          new Search(
              this.global.getGlobalFile(), term, Boolean.parseBoolean(header), index, searchType);

      List<List<String>> result = searcher.getResult();
      responseMap.put("result", "success");
      System.out.println("Found " + result.size() + " row(s) containing " + term + ":");
      for (List<String> row : result) {
        System.out.println(row);
      }
      responseMap.put("data", JsonConverter.serializeToJson(result));
      responseMap.put("time of retrieval", this.global.getTime());
      responseMap.put("searchBy", searchBy);
      responseMap.put("index", index);
      responseMap.put("header", header);
      responseMap.put("term", term);
      responseMap.put("searchType", searchType);
      return responseMap;

    } catch (Exception e) {

      responseMap.put("result", "error_bad_request");
      return responseMap;
    }
  }
  // given search term, the loaded csv will return all relevant rows as a json. Should allow all
  // parameters that are used in regular search (column/whole, index, term)
  // To keep the format simple, all responses must be serializations of a Map<String, Object>
  // object.
  //  For all replies, the map must contain a "result" field with value "success" in case of success
  //  or an error code in the case of an error: (see sprint doc)
}
