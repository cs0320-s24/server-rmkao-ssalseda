package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.server.Datasource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class RealDatasource implements Datasource {

  @Override
  public String getData(String county, String state) throws URISyntaxException, IOException, InterruptedException {
    return this.sendRequest(county, state);
  }

  private String sendRequest(String county, String state) throws URISyntaxException, IOException, InterruptedException {
    HttpRequest buildApiRequest =
        HttpRequest.newBuilder()
            .uri(new URI("https://api.census.gov/data/2021/acs/acs1/subject/variables?get=NAME,S2802_C03_022E&for=county:" + county + "&in=state:" + state))
            .GET()
            .build();

    // Send that API request then store the response in this variable. Note the generic type.
    HttpResponse<String> sentApiResponse =
        HttpClient.newBuilder().build().send(buildApiRequest, HttpResponse.BodyHandlers.ofString());

    // What's the difference between these two lines? Why do we return the body? What is useful from
    // the raw response (hint: how can we use the status of response)?
    System.out.println(sentApiResponse);
    System.out.println(sentApiResponse.body());

    return sentApiResponse.body();
  }
}
