package edu.brown.cs.student.main.requestCode;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** */
public class stateCodesAPIUtilities {

  /**
   * @param jsonData
   * @return List of list of strings representing the json data
   */
  public static Map<String, String> deserializeStates(String jsonData) {
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
      e.printStackTrace();

      //// I know this is bad, will fix later

      return null;
    }
  }

  /**
   * @param jsonData
   * @return List of list of strings representing the json data
   */
  public static Map<String, String> deserializeCounties(String jsonData) {
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
      e.printStackTrace();

      //// I know this is bad, will fix later

      return null;
    }
  }
}
