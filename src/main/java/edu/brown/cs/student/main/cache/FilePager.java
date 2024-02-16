package edu.brown.cs.student.main.cache;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FilePager implements Pager<String, HttpResponse<String>> {


  public HttpResponse<String> pager(String address)
      throws IOException, InterruptedException, URISyntaxException {
    HttpRequest buildApiRequest = HttpRequest.newBuilder().uri(new URI(address)).GET().build();
    HttpResponse<String> dessert = HttpClient.newBuilder().build()
        .send(buildApiRequest, HttpResponse.BodyHandlers.ofString());
    return dessert;
  }

  public FilePager(String address) throws IOException, InterruptedException, URISyntaxException {
    pager(address);
  }
}
