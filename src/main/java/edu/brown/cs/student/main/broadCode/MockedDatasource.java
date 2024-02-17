package edu.brown.cs.student.main.broadCode;

import java.io.IOException;
import java.net.URISyntaxException;

public class MockedDatasource implements Datasource {
  private String mockedData;

  public MockedDatasource() {}
  //
  //  @Override
  //  public Map<String, Object> getBroadband(String county, String state) {
  //    return null;
  //  }
  //
  //  @Override
  //  public String getStateCodes() {
  //    return "[[\"NAME\",\"state\"],\n"
  //        + "[\"Alabama\",\"01\"],\n"
  //        + "[\"Alaska\",\"02\"],\n"
  //        + "[\"Arizona\",\"04\"],\n"
  //        + "[\"Arkansas\",\"05\"],\n"
  //        + "[\"California\",\"06\"],\n"
  //        + "[\"Louisiana\",\"22\"],\n"
  //        + "[\"Kentucky\",\"21\"],\n"
  //        + "[\"Colorado\",\"08\"],\n"
  //        + "[\"Connecticut\",\"09\"],\n"
  //        + "[\"Delaware\",\"10\"],\n"
  //        + "[\"District of Columbia\",\"11\"],\n"
  //        + "[\"Florida\",\"12\"],\n"
  //        + "[\"Georgia\",\"13\"],\n"
  //        + "[\"Hawaii\",\"15\"],\n"
  //        + "[\"Idaho\",\"16\"],\n"
  //        + "[\"Illinois\",\"17\"],\n"
  //        + "[\"Indiana\",\"18\"],\n"
  //        + "[\"Iowa\",\"19\"],\n"
  //        + "[\"Kansas\",\"20\"],\n"
  //        + "[\"Maine\",\"23\"],\n"
  //        + "[\"Maryland\",\"24\"],\n"
  //        + "[\"Massachusetts\",\"25\"],\n"
  //        + "[\"Michigan\",\"26\"],\n"
  //        + "[\"Minnesota\",\"27\"],\n"
  //        + "[\"Mississippi\",\"28\"],\n"
  //        + "[\"Missouri\",\"29\"],\n"
  //        + "[\"Montana\",\"30\"],\n"
  //        + "[\"Nebraska\",\"31\"],\n"
  //        + "[\"Nevada\",\"32\"],\n"
  //        + "[\"New Hampshire\",\"33\"],\n"
  //        + "[\"New Jersey\",\"34\"],\n"
  //        + "[\"New Mexico\",\"35\"],\n"
  //        + "[\"New York\",\"36\"],\n"
  //        + "[\"North Carolina\",\"37\"],\n"
  //        + "[\"North Dakota\",\"38\"],\n"
  //        + "[\"Ohio\",\"39\"],\n"
  //        + "[\"Oklahoma\",\"40\"],\n"
  //        + "[\"Oregon\",\"41\"],\n"
  //        + "[\"Pennsylvania\",\"42\"],\n"
  //        + "[\"Rhode Island\",\"44\"],\n"
  //        + "[\"South Carolina\",\"45\"],\n"
  //        + "[\"South Dakota\",\"46\"],\n"
  //        + "[\"Tennessee\",\"47\"],\n"
  //        + "[\"Texas\",\"48\"],\n"
  //        + "[\"Utah\",\"49\"],\n"
  //        + "[\"Vermont\",\"50\"],\n"
  //        + "[\"Virginia\",\"51\"],\n"
  //        + "[\"Washington\",\"53\"],\n"
  //        + "[\"West Virginia\",\"54\"],\n"
  //        + "[\"Wisconsin\",\"55\"],\n"
  //        + "[\"Wyoming\",\"56\"],\n"
  //        + "[\"Puerto Rico\",\"72\"]]";
  //  }
  //
  //  @Override
  //  public String getCountyCodes(String state) {
  //    return "[[\"NAME\",\"state\",\"county\"],\n"
  //        + "[\"Colusa County, California\",\"06\",\"011\"],\n"
  //        + "[\"Butte County, California\",\"06\",\"007\"],\n"
  //        + "[\"Alameda County, California\",\"06\",\"001\"],\n"
  //        + "[\"Alpine County, California\",\"06\",\"003\"],\n"
  //        + "[\"Amador County, California\",\"06\",\"005\"],\n"
  //        + "[\"Calaveras County, California\",\"06\",\"009\"],\n"
  //        + "[\"Contra Costa County, California\",\"06\",\"013\"],\n"
  //        + "[\"Del Norte County, California\",\"06\",\"015\"],\n"
  //        + "[\"Kings County, California\",\"06\",\"031\"],\n"
  //        + "[\"Glenn County, California\",\"06\",\"021\"],\n"
  //        + "[\"Humboldt County, California\",\"06\",\"023\"],\n"
  //        + "[\"Imperial County, California\",\"06\",\"025\"],\n"
  //        + "[\"El Dorado County, California\",\"06\",\"017\"],\n"
  //        + "[\"Fresno County, California\",\"06\",\"019\"],\n"
  //        + "[\"Inyo County, California\",\"06\",\"027\"],\n"
  //        + "[\"Kern County, California\",\"06\",\"029\"],\n"
  //        + "[\"Mariposa County, California\",\"06\",\"043\"],\n"
  //        + "[\"Lake County, California\",\"06\",\"033\"],\n"
  //        + "[\"Lassen County, California\",\"06\",\"035\"],\n"
  //        + "[\"Los Angeles County, California\",\"06\",\"037\"],\n"
  //        + "[\"Madera County, California\",\"06\",\"039\"],\n"
  //        + "[\"Marin County, California\",\"06\",\"041\"],\n"
  //        + "[\"Orange County, California\",\"06\",\"059\"],\n"
  //        + "[\"Mendocino County, California\",\"06\",\"045\"],\n"
  //        + "[\"Merced County, California\",\"06\",\"047\"],\n"
  //        + "[\"Modoc County, California\",\"06\",\"049\"],\n"
  //        + "[\"Mono County, California\",\"06\",\"051\"],\n"
  //        + "[\"Monterey County, California\",\"06\",\"053\"],\n"
  //        + "[\"Napa County, California\",\"06\",\"055\"],\n"
  //        + "[\"Nevada County, California\",\"06\",\"057\"],\n"
  //        + "[\"San Bernardino County, California\",\"06\",\"071\"],\n"
  //        + "[\"Sacramento County, California\",\"06\",\"067\"],\n"
  //        + "[\"San Benito County, California\",\"06\",\"069\"],\n"
  //        + "[\"Placer County, California\",\"06\",\"061\"],\n"
  //        + "[\"Plumas County, California\",\"06\",\"063\"],\n"
  //        + "[\"Riverside County, California\",\"06\",\"065\"],\n"
  //        + "[\"San Joaquin County, California\",\"06\",\"077\"],\n"
  //        + "[\"San Diego County, California\",\"06\",\"073\"],\n"
  //        + "[\"San Francisco County, California\",\"06\",\"075\"],\n"
  //        + "[\"Siskiyou County, California\",\"06\",\"093\"],\n"
  //        + "[\"San Luis Obispo County, California\",\"06\",\"079\"],\n"
  //        + "[\"San Mateo County, California\",\"06\",\"081\"],\n"
  //        + "[\"Santa Barbara County, California\",\"06\",\"083\"],\n"
  //        + "[\"Santa Clara County, California\",\"06\",\"085\"],\n"
  //        + "[\"Santa Cruz County, California\",\"06\",\"087\"],\n"
  //        + "[\"Shasta County, California\",\"06\",\"089\"],\n"
  //        + "[\"Sierra County, California\",\"06\",\"091\"],\n"
  //        + "[\"Yuba County, California\",\"06\",\"115\"],\n"
  //        + "[\"Solano County, California\",\"06\",\"095\"],\n"
  //        + "[\"Sonoma County, California\",\"06\",\"097\"],\n"
  //        + "[\"Stanislaus County, California\",\"06\",\"099\"],\n"
  //        + "[\"Sutter County, California\",\"06\",\"101\"],\n"
  //        + "[\"Tehama County, California\",\"06\",\"103\"],\n"
  //        + "[\"Trinity County, California\",\"06\",\"105\"],\n"
  //        + "[\"Ventura County, California\",\"06\",\"111\"],\n"
  //        + "[\"Yolo County, California\",\"06\",\"113\"],\n"
  //        + "[\"Tulare County, California\",\"06\",\"107\"],\n"
  //        + "[\"Tuolumne County, California\",\"06\",\"109\"]]";
  //  }

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
          + "[\"Louisiana\",\"22\"],\n";
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
          + "[\"Humboldt County, California\",\"06\",\"023\"],\n";
    } else {
      return "";
    }
  }
}
