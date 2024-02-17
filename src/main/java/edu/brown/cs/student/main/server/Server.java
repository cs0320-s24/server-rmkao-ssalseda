package edu.brown.cs.student.main.server;

import static spark.Spark.after;

import edu.brown.cs.student.main.GlobalCache;
import edu.brown.cs.student.main.GlobalGlove;
import edu.brown.cs.student.main.broadCode.BroadbandHandler;
import edu.brown.cs.student.main.broadCode.RealDatasource;
import edu.brown.cs.student.main.loadCode.LoadHandler;
import edu.brown.cs.student.main.searchCode.SearchHandler;
import edu.brown.cs.student.main.viewCode.ViewHandler;
import spark.Spark;

/**
 * The heart and soul of this API. The server class serves as the central hub of all API
 * interactions. The server directs the user's request to the appropriate handler as well as
 * instantiating global variables.
 */
public class Server {
  /**
   * All the server's work to set up the API.
   *
   * @param args
   */
  public static void main(String[] args) {
    // recommended port
    int port = 3232;
    Spark.port(port);

    after(
        (request, response) -> {
          response.header("Access-Control-Allow-Origin", "*");
          response.header("Access-Control-Allow-Methods", "*");
        });
    // global variables
    GlobalCache globalCache = new GlobalCache();
    GlobalGlove globalFile = new GlobalGlove();
    // Setting up the handler for the all possible endpoints
    Spark.get("loadcsv", new LoadHandler(globalFile));
    Spark.get("viewcsv", new ViewHandler(globalFile));
    Spark.get("searchcsv", new SearchHandler(globalFile));
    Spark.get("broadband", new BroadbandHandler(new RealDatasource(), globalCache));

    Spark.init();
    Spark.awaitInitialization();

    System.out.println("Server started at http://localhost:" + port);
  }
}
