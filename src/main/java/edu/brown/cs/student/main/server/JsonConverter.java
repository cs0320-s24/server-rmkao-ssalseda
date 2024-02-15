package edu.brown.cs.student.main.server;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.util.List;

public class JsonConverter {

  public static String serializeToJson(List<List<String>> data) {
    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<List<List<String>>> adapter =
        moshi.adapter(Types.newParameterizedType(List.class, List.class));
    return adapter.toJson(data);
  }
}
