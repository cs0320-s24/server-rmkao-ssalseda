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
   * @param jsonActivity
   * @return List of list of strings representing the json data
   */
  public static Map<String, String> deserializeCode(String jsonData) {
    try {
      // Initializes Moshi
      Moshi moshi = new Moshi.Builder().build();

      Type listType = Types.newParameterizedType(List.class, List.class, String.class);
      JsonAdapter<List<List<String>>> adapter = moshi.adapter(listType);

      // Deserialize JSON
      List<List<String>> statesArray = adapter.fromJson(jsonData);

      Map<String, String> stateMap = new HashMap<>();

      // fill the map
      for (List<String> state : statesArray) {
        stateMap.put(state.get(0), state.get(1));
      }

      return stateMap;
    } catch (IOException e) {
      e.printStackTrace();

      //// I know this is bad, will fix later

      return null;
    }
  }
}
