package edu.brown.cs.student.main.server;

import java.util.Map;

public interface Datasource {
  public Map<String, Object> getData(String county, String state);
}
