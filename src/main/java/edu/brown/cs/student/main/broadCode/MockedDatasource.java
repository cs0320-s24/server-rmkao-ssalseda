package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.server.Datasource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class MockedDatasource implements Datasource {
  private String mockedData;

  public MockedDatasource() {}

  @Override
  public Map<String, Object> getBroadband(String county, String state) {
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
