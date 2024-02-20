package edu.brown.cs.student.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.brown.cs.student.main.broadCode.stateCodesAPIUtilities;
import edu.brown.cs.student.main.server.WebAPIResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitTests {

  @BeforeEach
  public void setup() {
    // No setup
  }

  @AfterEach
  public void teardown() {
    // No setup
  }

  @Test
  public void serializeResponseMap() throws IOException {
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("data1", 1);
    responseMap.put("data2", "interesting");
    responseMap.put("data3", "red");
    responseMap.put("string", "string");
    String result = new WebAPIResponse(responseMap).serialize();
    assertEquals(result, "{\"responseMap\":{\"string\":\"string\",\"data3\":\"red\",\"data2\":\"interesting\",\"data1\":1}}");
  }
@Test
  public void deserializeState() throws IOException {
    String state = "[[\"NAME\",\"state\"],\n"
        + "[\"Alabama\",\"01\"],\n"
        + "[\"Alaska\",\"02\"]]";
    Map<String, String> result = stateCodesAPIUtilities.deserializeStates(state);
    Map<String, String> ideal = new HashMap<>();
    ideal.put("Alaska", "02");
    ideal.put("Alabama", "01");
    ideal.put("NAME", "state");
    assertEquals(result, ideal);
    System.out.println(result);
  }
  @Test
  public void deserializeCounty() throws IOException {
    String county = "[[\"NAME\",\"state\",\"county\"],\n"
        + "[\"Colusa County, California\",\"06\",\"011\"],\n"
        + "[\"Butte County, California\",\"06\",\"007\"]]";
    Map<String, String> ideal = new HashMap<>();
    ideal.put("Butte County, California", "007");
    ideal.put("Colusa County, California", "011");
    ideal.put("NAME", "county");
    Map<String, String> result = stateCodesAPIUtilities.deserializeCounties(county);
    assertEquals(result, ideal);
  }
  @Test
  public void deserializeACS() throws IOException {
    String county = "[[\"NAME\",\"state\",\"county\"],\n"
        + "[\"Colusa County, California\",\"06\",\"011\"],\n"
        + "[\"Butte County, California\",\"06\",\"007\"]]";
    List<List<String>> ideal = new ArrayList<>();
    List<String> one = new ArrayList<>();
    one.add("NAME");
    one.add("state");
    one.add("county");
    List<String> two = new ArrayList<>();
    two.add("Colusa County, California");
    two.add("06");
    two.add("011");
    List<String> three = new ArrayList<>();
    three.add("Butte County, California");
    three.add("06");
    three.add("007");
    ideal.add(one);
    ideal.add(two);
    ideal.add(three);
    List<List<String>> result = stateCodesAPIUtilities.deserializeACS(county);
    assertEquals(result, ideal);
  }}