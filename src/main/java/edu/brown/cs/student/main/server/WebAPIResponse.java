package edu.brown.cs.student.main.server;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.Map;

public record WebAPIResponse(Map<String, Object> responseMap) {
  public String serialize() {
    try {
      // Initialize Moshi which takes in this class and returns it as JSON!
      Moshi moshi = new Moshi.Builder().build();
      JsonAdapter<WebAPIResponse> adapter = moshi.adapter(WebAPIResponse.class);
      return adapter.toJson(this);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}
