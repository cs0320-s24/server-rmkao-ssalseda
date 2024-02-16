package edu.brown.cs.student.main.cache;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class FilePager implements Pager<String, String> {

  /**
   * the data is extracted from through the API call, and it is returned.
   * @param address
   * @return String of requested data
   */
  public String pager(String address)
      throws IOException, InterruptedException, URISyntaxException {
     Map<String, Object> innerMap = new HashMap<>();
try{
    HttpRequest buildApiRequest = HttpRequest.newBuilder().uri(new URI(address)).GET().build();
    HttpResponse<String> dessert = HttpClient.newBuilder().build()
        .send(buildApiRequest, HttpResponse.BodyHandlers.ofString());

    return dessert.body();
  } catch (Exception e) {
return "Call to API unsuccessful. Try again!";}
  }
}
