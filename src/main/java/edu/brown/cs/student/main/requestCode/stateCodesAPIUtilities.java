package edu.brown.cs.student.main.requestCode;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/** */
public class stateCodesAPIUtilities {

  /**
   * @param jsonActivity
   * @return
   */
  public static List<List<String>> deserializeCode(String jsonActivity) {
    try {
      // Initializes Moshi
      Moshi moshi = new Moshi.Builder().build();

      // Initializes an adapter to an Activity class then uses it to parse the JSON.
      //      JsonAdapter<stateCodes> adapter = moshi.adapter(stateCodes.class);

      Type listType = Types.newParameterizedType(List.class, List.class, String.class);
      JsonAdapter<List<List<String>>> adapter = moshi.adapter(listType);

      List<List<String>> rightCode = adapter.fromJson(jsonActivity);

      return rightCode;
    }
    // Returns an empty activity... Probably not the best handling of this error case...
    // Notice an alternative error throwing case to the one done in OrderHandler. This catches
    // the error instead of pushing it up.
    catch (IOException e) {
      e.printStackTrace();

      //// I know this is bad, will fix later

      return null;
    }
  }
}
