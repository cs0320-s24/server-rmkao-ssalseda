package edu.brown.cs.student.main.viewCode;

import spark.Request;
import spark.Response;
import spark.Route;

public class ViewHandler implements Route {

  @Override
  public Object handle(Request request, Response response) throws Exception {
    return null;
  }
  // converts load CSV file to JSON and sends it

  //  To keep the format simple, all responses must be serializations of a Map<String, Object>
  // object.
  //  For all replies, the map must contain a "result" field with value "success" in case of success
  //  or an error code in the case of an error: (see sprint doc)

}
