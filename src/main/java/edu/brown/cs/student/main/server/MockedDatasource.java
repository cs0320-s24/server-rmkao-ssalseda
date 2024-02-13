package edu.brown.cs.student.main.server;

import java.util.Map;

public class MockedDatasource implements Datasource {

  @Override
  public Map<String, Object> getData(String county, String state) {
    return null;
  }
}
