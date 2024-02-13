package edu.brown.cs.student.main.server;

import static spark.Spark.after;

import edu.brown.cs.student.main.loadCode.LoadHandler;
import edu.brown.cs.student.main.requestCode.RequestHandler;
import edu.brown.cs.student.main.searchCode.SearchHandler;
import edu.brown.cs.student.main.viewCode.ViewHandler;
import spark.Spark;

/// **
// * Top-level class for this demo. Contains the main() method which starts Spark and runs the
// various
// * handlers (2).
// *
// * <p>Notice that the OrderHandler takes in a state (menu) that can be shared if we extended the
// * restaurant They need to share state (a menu). This would be a great opportunity to use
// dependency
// * injection. If we needed more endpoints, more functionality classes, etc. we could make sure
// they
// * all had the same shared state.
// */
public class Server {
  // TODO 0: Read through this class and determine the shape of this project...
  // What are the endpoints that we can access... What happens if you go to them?
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
    //    Spark.get("broadband", new BroadbandHandler());
    Spark.init();
    Spark.awaitInitialization();

    // Notice this link alone leads to a 404... Why is that?
    System.out.println("Server started at http://localhost:" + port);
  }
}
