package edu.brown.cs.student.main;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Object designed to give global and controlled access to different parts of the API.
 *
 * @param cache This cache is set up by the end-user (who can control maximum size and time of the
 *     files preserved in the cache). This particular cache is populated by search terms and their
 *     corresponding responseMaps which can be quickly dispensed instead of necessitating a long
 *     call to the ACS.
 */
public class GlobalCache {
  public Cache<String, Map<String, Object>> cache;

  /**
   * The constructor sets the basics for the cache and allows for user modifications. There are a
   * variety of other limits that users can configure here, but we thought these to be the most
   * useful without going overboard
   */
  public GlobalCache() {
    this.cache =
        CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .recordStats()
            .build();
  }

  /**
   * GlobalAdd is the mechanism by which all cached data is stored
   *
   * @param K the key is the search term or link
   * @param V the value is the entire responseMap associated with that search
   */
  public void GlobalAdd(String K, Map<String, Object> V) {
    this.cache.put(K, V);
  }

  /**
   * Print is mainly for the utility of the user as it allows one to monitor modifications made to
   * the cache
   */
  public void print() {
    System.out.println(cache.stats());
  }
}
