package edu.brown.cs.student.main.broadCode;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class uses Moshi to deserialize a JSON strings into usable maps. */
public class stateCodesAPIUtilities {

  /**
   * This function deserializes a JSON containing states and their corresponding codes into a map.
   *
   * @param jsonData the JSON string
   * @return a Map that maps every state's name to its code
   * @throws IndexOutOfBoundsException if the state name or code could not be accessed
   * @throws IOException if the moshi adapter cannot initially deserialize the JSON
   */
  public static Map<String, String> deserializeStates(String jsonData)
      throws IndexOutOfBoundsException, IOException {
    try {
      // Initializes Moshi
      Moshi moshi = new Moshi.Builder().build();

      Type listType = Types.newParameterizedType(List.class, List.class, String.class);
      JsonAdapter<List<List<String>>> adapter = moshi.adapter(listType);

      // Deserialize JSON
      List<List<String>> itemsArray = adapter.fromJson(jsonData);

      Map<String, String> codeMap = new HashMap<>();

      // fill the map
      for (List<String> state : itemsArray) {
        codeMap.put(state.get(0), state.get(1));
      }
      return codeMap;
    } catch (IOException e) {
      throw new IOException("Could not deserialize JSON of state codes: " + e.getMessage());
    }
  }

  /**
   * This function deserializes a JSON containing counties and their corresponding codes into a map.
   *
   * @param jsonData the JSON string
   * @return a Map that maps each county name to its code
   * @throws IndexOutOfBoundsException if the county name or code could not be accessed
   * @throws IOException if the moshi adapter cannot initially deserialize the JSON
   */
  public static Map<String, String> deserializeCounties(String jsonData)
      throws IndexOutOfBoundsException, IOException {
    try {
      // Initializes Moshi
      Moshi moshi = new Moshi.Builder().build();

      Type listType = Types.newParameterizedType(List.class, List.class, String.class);
      JsonAdapter<List<List<String>>> adapter = moshi.adapter(listType);

      // Deserialize JSON
      List<List<String>> countiesArray = adapter.fromJson(jsonData);

      Map<String, String> countyMap = new HashMap<>();

      // fill the map
      for (List<String> county : countiesArray) {
        countyMap.put(county.get(0), county.get(2));
      }
      return countyMap;
    } catch (IOException e) {
      throw new IOException("Could not deserialize JSON of county codes: " + e.getMessage());
    }
  }

  /**
   * @param jsonActivity
   * @return
   */
  public static List<List<String>> deserializeACS(String jsonActivity) throws IOException {
    try {
      // Initializes Moshi
      Moshi moshi = new Moshi.Builder().build();

      // Initializes an adapter to a list of list of string class then uses it to parse the JSON.

      Type listType = Types.newParameterizedType(List.class, List.class, String.class);
      JsonAdapter<List<List<String>>> adapter = moshi.adapter(listType);

      return adapter.fromJson(jsonActivity);
    }
    catch (IOException e) {
      throw new IOException("Could not deserialize search: " + e.getMessage());
    }
  }


}
