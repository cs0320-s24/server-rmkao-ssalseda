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
   * Using guava to manage evictions, but settled on manual additions.
   *
   * @param toWrap the Searcher to wrap
   * @param cache
   */
  public CachedFilePager(Pager<String, String> toWrap, GlobalCache GlobeCash, String guide)
      throws IOException, URISyntaxException, ExecutionException, InterruptedException {
    // simply calls get method if already present
    this.answer = GlobeCash.cache.getIfPresent(guide);
    // builds out a responseMap for a new search and uploads it to the cache.
    if (this.answer == null) {
      this.answer = new HashMap<>();
      String value = toWrap.pager(guide);
      this.answer.put("result", "success");
      this.answer.put("broadband", value);
      this.answer.put("time", LocalDateTime.now());
      GlobeCash.GlobalAdd(guide, this.answer);
    }
    GlobeCash.print();
  }

  /**
   * @param s
   * @return produces answer
   */
  public Map<String, Object> pager(String s) {
    return this.answer;
  }
}
