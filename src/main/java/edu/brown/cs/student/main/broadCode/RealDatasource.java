package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.GlobalCache;
import edu.brown.cs.student.main.cache.CachedFilePager;
import edu.brown.cs.student.main.cache.FilePager;
import edu.brown.cs.student.main.cache.Pager;
import edu.brown.cs.student.main.server.Datasource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RealDatasource implements Datasource {
private GlobalCache cacheSys;
  public RealDatasource(GlobalCache cacheSys) {
    this.cacheSys = cacheSys;
  }

  @Override
  public String getStateCodes() throws URISyntaxException, IOException, InterruptedException {
    HttpRequest buildApiRequest =
        HttpRequest.newBuilder()
            .uri(new URI("https://api.census.gov/data/2010/dec/sf1?get=NAME&for=state:*"))
            .GET()
            .build();

    // Send that API request then store the response in this variable. Note the generic type.
    HttpResponse<String> sentApiResponse =
        HttpClient.newBuilder().build().send(buildApiRequest, HttpResponse.BodyHandlers.ofString());

    return sentApiResponse.body();
  }

  @Override
  public String getCountyCodes(String state)
      throws URISyntaxException, IOException, InterruptedException {
    HttpRequest buildApiRequest =
        HttpRequest.newBuilder()
            .uri(
                new URI(
                    "https://api.census.gov/data/2010/dec/sf1?get=NAME&for=county:*&in=state:"
                        + state))
            .GET()
            .build();

    // Send that API request then store the response in this variable. Note the generic type.
    HttpResponse<String> sentApiResponse =
        HttpClient.newBuilder().build().send(buildApiRequest, HttpResponse.BodyHandlers.ofString());

    return sentApiResponse.body();
  }

  @Override
  public Map<String, Object> getBroadband(String county, String state)
      throws URISyntaxException, IOException, InterruptedException, ExecutionException {
    String constructedString =
        "https://api.census.gov/data/2021/acs/acs1/subject/variables?get=NAME,S2802_C03_022E&for=county:"
            + county
            + "&in=state:"
            + state;
//    HttpRequest buildApiRequest =
//        HttpRequest.newBuilder().uri(new URI(constructedString)).GET().build();

    // Send that API request then store the response in this variable. Note the generic type.
    return new CachedFilePager(new FilePager(), this.cacheSys, constructedString).pager(constructedString);
    //    new CachedFilePager(HttpClient.newBuilder().build()
    //        .send(buildApiRequest, HttpResponse.BodyHandlers.ofString()));

  }

//  private String camo(CachedFilePager cachedFilePager, String key) throws ExecutionException {
//    return cachedFilePager.pager(key);
//  }

}
