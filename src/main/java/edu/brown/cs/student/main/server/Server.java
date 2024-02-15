package edu.brown.cs.student.main.server;

import static spark.Spark.after;

import edu.brown.cs.student.main.broadCode.BroadbandHandler;
import edu.brown.cs.student.main.broadCode.RealDatasource;
import edu.brown.cs.student.main.loadCode.LoadHandler;
import edu.brown.cs.student.main.requestCode.RequestHandler;
import edu.brown.cs.student.main.searchCode.SearchHandler;
import edu.brown.cs.student.main.viewCode.ViewHandler;
import spark.Spark;

public class Server {
  public static void main(String[] args) {
    int port = 3232;
    Spark.port(port);

    after(
        (request, response) -> {
          response.header("Access-Control-Allow-Origin", "*");
          response.header("Access-Control-Allow-Methods", "*");
        });

    // Setting up the handler for the GET /order and /activity endpoints
    Spark.get("request", new RequestHandler());
    Spark.get("loadcsv", new LoadHandler());
    Spark.get("viewcsv", new ViewHandler());
    Spark.get("searchcsv", new SearchHandler());
    Spark.get("broadband", new BroadbandHandler(new RealDatasource()));
    Spark.init();
    Spark.awaitInitialization();

    System.out.println("Server started at http://localhost:" + port);
  }
}
