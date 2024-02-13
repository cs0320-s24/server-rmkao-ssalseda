package edu.brown.cs.student.main.loadCode;

    import java.io.IOException;
    import java.net.URI;
    import java.net.URISyntaxException;
    import java.net.http.HttpClient;
    import java.net.http.HttpRequest;
    import java.net.http.HttpResponse;
    import java.time.LocalDateTime;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Set;
    import spark.Request;
    import spark.Response;
    import spark.Route;

/**
 * This class is used to illustrate how to build and send a GET request then prints the response. It
 * will also demonstrate a simple Moshi deserialization from online data.
 */
// TODO 1: Check out this Handler. How can we make it only get activities based on participant #?
// See Documentation here: https://www.boredapi.com/documentation
public class LoadHandler implements Route {

  @Override
  public Object handle(Request request, Response response) throws Exception {
    return null;
  }
}
