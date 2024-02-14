package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.server.Datasource;
import java.util.HashMap;
import java.util.Map;

public class MockedDatasource implements Datasource {
  private String mockedData;
  public MockedDatasource() {

  }

  @Override
  public String getData(String county, String state) {
    return null;
  }
}
