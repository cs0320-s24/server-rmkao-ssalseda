package edu.brown.cs.student.main.cache;

import edu.brown.cs.student.main.GlobalCache;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * A class that wraps a FileServer instance and caches responses for efficiency. Notice that the
 * interface hasn't changed at all. This is an example of the proxy pattern; callers will interact
 * with the CachedFileServer, rather than the "real" data source.
 */
public class CachedFilePager implements Pager<String, Map<String, Object>> {
  public Map<String, Object> answer;

  /**
   * This constructor performs the main functionality of the class. Using the guide string, it will
   * retrieve any previous identical queries from the cache or make a new one if it hasn't been
   * cached.
   *
   * @param toWrap the file pager
   * @param globalCache the global cache
   * @param guide the string to check against cache
   */
  public CachedFilePager(Pager<String, String> toWrap, GlobalCache globalCache, String guide)
      throws IOException, URISyntaxException, ExecutionException, InterruptedException {
    // simply calls get method if already present
    this.answer = globalCache.cache.getIfPresent(guide);
    // builds out a responseMap for a new search and uploads it to the cache.
    if (this.answer == null) {
      this.answer = new HashMap<>();
      String value = toWrap.pager(guide);
      this.answer.put("result", "success");
      this.answer.put("broadband", value);
      this.answer.put("time", LocalDateTime.now().toString());
      globalCache.GlobalAdd(guide, this.answer);
    }
    globalCache.print();
  }

  /**
   * @param s
   * @return produces answer
   */
  public Map<String, Object> pager(String s) {
    return this.answer;
  }
}
