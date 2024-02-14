package edu.brown.cs.student.main.server;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface Datasource {
  public String getData(String county, String state) throws URISyntaxException, IOException, InterruptedException;
}
