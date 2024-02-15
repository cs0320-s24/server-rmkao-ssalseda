package edu.brown.cs.student.main.server;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Datasource {
  public String getData(String county, String state)
      throws URISyntaxException, IOException, InterruptedException;

  public String getStateCodes() throws URISyntaxException, IOException, InterruptedException;

  public String getCountyCodes(String state)
      throws URISyntaxException, IOException, InterruptedException;
}
