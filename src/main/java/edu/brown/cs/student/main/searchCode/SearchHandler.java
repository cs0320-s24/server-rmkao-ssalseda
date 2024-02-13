package edu.brown.cs.student.main.searchCode;

import spark.Request;
import spark.Response;
import spark.Route;

public class SearchHandler implements Route {

  // http://localhost:3232/searchcsv?
  // searchby=(column or grid)
  // index= (number),
  // header= (string)
  // term = (string)
  @Override
  public Object handle(Request request, Response response) throws Exception {
    String searchBy = request.queryParams("searchby");
    String index = request.queryParams("index");
    String header = request.queryParams("header");
    String term = request.queryParams("term");

    return null;
  }
  // given search term, the loaded csv will return all relevant rows as a json. Should allow all
  // parameters that are used in regular search (column/whole, index, term)
  // To keep the format simple, all responses must be serializations of a Map<String, Object>
  // object.
  //  For all replies, the map must contain a "result" field with value "success" in case of success
  //  or an error code in the case of an error: (see sprint doc)
}
