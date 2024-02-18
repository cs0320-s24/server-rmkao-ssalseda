package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.GlobalCache;
import edu.brown.cs.student.main.cache.CachedFilePager;
import edu.brown.cs.student.main.cache.FilePager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RealDatasource implements Datasource {
  public RealDatasource() {}

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

  @Override
  public Map<String, Object> getBroadband(String constructedString, GlobalCache globalCache)
      throws IOException, URISyntaxException, ExecutionException, InterruptedException {
    return new CachedFilePager(new FilePager(), globalCache, constructedString)
        .pager(constructedString);
  }
}
