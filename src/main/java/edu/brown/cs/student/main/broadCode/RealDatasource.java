package edu.brown.cs.student.main.broadCode;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RealDatasource implements Datasource {
  public RealDatasource() {}

  //  @Override
  //  public String getStateCodes() throws URISyntaxException, IOException, InterruptedException {
  //    HttpRequest buildApiRequest =
  //        HttpRequest.newBuilder()
  //            .uri(new URI("https://api.census.gov/data/2010/dec/sf1?get=NAME&for=state:*"))
  //            .GET()
  //            .build();
  //
  //    // Send that API request then store the response in this variable. Note the generic type.
  //    HttpResponse<String> sentApiResponse =
  //        HttpClient.newBuilder().build().send(buildApiRequest,
  // HttpResponse.BodyHandlers.ofString());
  //
  //    return sentApiResponse.body();
  //  }
  //
  //  @Override
  //  public String getCountyCodes(String state)
  //      throws URISyntaxException, IOException, InterruptedException {
  //    HttpRequest buildApiRequest =
  //        HttpRequest.newBuilder()
  //            .uri(
  //                new URI(
  //                    "https://api.census.gov/data/2010/dec/sf1?get=NAME&for=county:*&in=state:"
  //                        + state))
  //            .GET()
  //            .build();
  //
  //    // Send that API request then store the response in this variable. Note the generic type.
  //    HttpResponse<String> sentApiResponse =
  //        HttpClient.newBuilder().build().send(buildApiRequest,
  // HttpResponse.BodyHandlers.ofString());
  //
  //    return sentApiResponse.body();
  //  }

  @Override
  public String getData(String constructedString)
      throws URISyntaxException, IOException, InterruptedException {
    HttpRequest buildApiRequest =
        HttpRequest.newBuilder().uri(new URI(constructedString)).GET().build();

    // Send that API request then store the response in this variable. Note the generic type.
    HttpResponse<String> sentApiResponse =
        HttpClient.newBuilder().build().send(buildApiRequest, HttpResponse.BodyHandlers.ofString());

    return sentApiResponse.body();
  }
}
