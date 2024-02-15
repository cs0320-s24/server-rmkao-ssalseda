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

public class SearchHandler implements Route {
  private GlobalGlove global;

  public SearchHandler(GlobalGlove globalFile) {
    this.global = globalFile;
  }

  // http://localhost:3232/searchcsv?
  // http://localhost:3232/searchcsv?searchby=column&index=IPEDS Race&header=true&term=Asian
  // searchby=(column or grid)
  // index= (number),
  // header= (string)
  // term = (string)
  @Override
  public Object handle(Request request, Response response) throws Exception {

    Map<String, Object> responseMap = new HashMap<>();
    if (this.global.checkFull()) {
      responseMap.put("result", "no file loaded");
      return responseMap;
    }

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


    Search searcher = new Search(this.global.getGlobalFile(), term, Boolean.parseBoolean(header), index, searchType);

    // Print results
    List<List<String>> result = searcher.getResult();

    // NEED TO CHANGE IT SO THE DATA IS A PROXY. RIGHT NOW IT DELETES I THINK

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
  }
  // given search term, the loaded csv will return all relevant rows as a json. Should allow all
  // parameters that are used in regular search (column/whole, index, term)
  // To keep the format simple, all responses must be serializations of a Map<String, Object>
  // object.
  //  For all replies, the map must contain a "result" field with value "success" in case of success
  //  or an error code in the case of an error: (see sprint doc)
}
