package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.server.Datasource;
import java.io.IOException;
import java.net.URISyntaxException;

public class MockedDatasource implements Datasource {
  private String mockedData;

  public MockedDatasource() {}

  @Override
  public String getData(String county, String state) {
    return null;
  }

  @Override
  public String getStateCodes() {
    return null;
  }

  @Override
  public String getCountyCodes(String state)
      throws URISyntaxException, IOException, InterruptedException {
    return null;
  }
}
