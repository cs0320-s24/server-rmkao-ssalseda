package edu.brown.cs.student.main.broadCode;

import java.io.IOException;
import java.net.URISyntaxException;

/** and interface that dictates the needed methods for all related to datasource */
public interface Datasource {
  public String getData(String constructedString)
      throws URISyntaxException, IOException, InterruptedException;
}
