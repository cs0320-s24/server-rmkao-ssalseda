package edu.brown.cs.student.main.server;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/** and interface that dictates the needed methods for all related to datasource */
public interface Datasource {

  /**
   * Retrieval function that uses many helpers to find the broadband information of a specific
   * county in a given state.
   *
   * @param county County of requested information.
   * @param state State in which county is.
   * @return a HashMap responseMap containing the data.
   * @throws URISyntaxException
   * @throws IOException
   * @throws InterruptedException
   * @throws ExecutionException
   */
  public Map<String, Object> getBroadband(String county, String state)
      throws URISyntaxException, IOException, InterruptedException, ExecutionException;

  /**
   * gets the state codes
   *
   * @return returns the code pairs
   * @throws URISyntaxException
   * @throws IOException
   * @throws InterruptedException
   */
  public String getStateCodes() throws URISyntaxException, IOException, InterruptedException;

  /**
   * performs a call to get the state codes
   *
   * @param state
   * @return state codes
   * @throws URISyntaxException
   * @throws IOException
   * @throws InterruptedException
   */
  public String getCountyCodes(String state)
      throws URISyntaxException, IOException, InterruptedException;
}
