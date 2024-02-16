package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.server.Datasource;

public class MockedDatasource implements Datasource {
  private String mockedData;

  public MockedDatasource() {}

  @Override
  public String getBroadband(String county, String state) {
    return null;
  }

  @Override
  public String getStateCodes() {
    return null;
  }

  @Override
  public String getCountyCodes(String state) {
    return null;
  }

  @Override
  public String getData(String query) {
    return null;
  }
}
