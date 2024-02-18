package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.GlobalCache;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/** and interface that dictates the needed methods for all related to datasource */
public interface Datasource {
  public String getData(String constructedString)
      throws URISyntaxException, IOException, InterruptedException;

  public Map<String, Object> getBroadband(String constructedString, GlobalCache globalCache)
      throws IOException, URISyntaxException, ExecutionException, InterruptedException;
}
