package edu.brown.cs.student.main.server;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public interface Datasource {
  public String getBroadband(String county, String state)
      throws URISyntaxException, IOException, InterruptedException, ExecutionException;

  public String getStateCodes() throws URISyntaxException, IOException, InterruptedException;

  public String getCountyCodes(String state)
      throws URISyntaxException, IOException, InterruptedException;
}
