package edu.brown.cs.student.main.broadCode;

import edu.brown.cs.student.main.GlobalCache;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MockedDatasource implements Datasource {
  private String mockedData;

  public MockedDatasource() {}

  @Override
  public String getData(String constructedString)
      throws URISyntaxException, IOException, InterruptedException {
    if (constructedString.equals("https://api.census.gov/data/2010/dec/sf1?get=NAME&for=state:*")) {
      return "[[\"NAME\",\"state\"],\n"
          + "[\"Alabama\",\"01\"],\n"
          + "[\"Alaska\",\"02\"],\n"
          + "[\"Arizona\",\"04\"],\n"
          + "[\"Arkansas\",\"05\"],\n"
          + "[\"California\",\"06\"],\n"
          + "[\"Louisiana\",\"22\"]]";
    } else if (constructedString.equals(
        "https://api.census.gov/data/2010/dec/sf1?get=NAME&for=county:*&in=state:06")) {
      return "[[\"NAME\",\"state\",\"county\"],\n"
          + "[\"Colusa County, California\",\"06\",\"011\"],\n"
          + "[\"Butte County, California\",\"06\",\"007\"],\n"
          + "[\"Alameda County, California\",\"06\",\"001\"],\n"
          + "[\"Alpine County, California\",\"06\",\"003\"],\n"
          + "[\"Amador County, California\",\"06\",\"005\"],\n"
          + "[\"Calaveras County, California\",\"06\",\"009\"],\n"
          + "[\"Contra Costa County, California\",\"06\",\"013\"],\n"
          + "[\"Del Norte County, California\",\"06\",\"015\"],\n"
          + "[\"Kings County, California\",\"06\",\"031\"],\n"
          + "[\"Glenn County, California\",\"06\",\"021\"],\n"
          + "[\"Humboldt County, California\",\"06\",\"023\"]]";
    } else {
      return "";
    }
  }

  @Override
  public Map<String, Object> getBroadband(String constructedString, GlobalCache globalCache) {
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("result", "success");
    responseMap.put(
        "broadband",
        "[[\"NAME\",\"S2802_C03_022E\",\"state\",\"county\"],\n[\"Kings County, California\",\"83.5\",\"06\",\"031\"]]");
    return null;
  }
}
