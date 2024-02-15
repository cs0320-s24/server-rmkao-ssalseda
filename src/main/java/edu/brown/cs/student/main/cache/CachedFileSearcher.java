package edu.brown.cs.student.main.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * A class that wraps a FileServer instance and caches responses for efficiency. Notice that the
 * interface hasn't changed at all. This is an example of the proxy pattern; callers will interact
 * with the CachedFileServer, rather than the "real" data source.
 *
 * <p>This version uses a Guava cache class to manage the cache.
 */
public class CachedFileSearcher implements Searcher<String, String> {
  private final Searcher<String, String> wrappedSearcher;
  private final LoadingCache<String, Collection<String>> cache;

  /**
   * Proxy class: wrap an instance of Searcher (of any kind) and cache its results.
   *
   * <p>There are _many_ ways to implement this! We could use a plain HashMap, but then we'd have to
   * handle "eviction" ourselves. Lots of libraries exist. We're using Guava here, to demo the
   * strategy pattern.
   *
   * @param toWrap the Searcher to wrap
   */
  public CachedFileSearcher(Searcher<String, String> toWrap) {
    this.wrappedSearcher = toWrap;

    // Look at the docs -- there are lots of builder parameters you can use
    //   including ones that affect garbage-collection (not needed for Server).
    this.cache =
        CacheBuilder.newBuilder()
            // How many entries maximum in the cache?
            .maximumSize(10)
            // How long should entries remain in the cache?
            .expireAfterWrite(1, TimeUnit.MINUTES)
            // Keep statistical info around for profiling purposes
            .recordStats()
            .build(
                // Strategy pattern: how should the cache behave when
                // it's asked for something it doesn't have?
                new CacheLoader<>() {
                  @Override
                  public Collection<String> load(String key) {
                    System.out.println("called load for: " + key);
                    // If this isn't yet present in the cache, load it:
                    return wrappedSearcher.search(key);
                  }
                });
  }

  @Override
  public Collection<String> search(String target) {
    // "get" is designed for concurrent situations; for today, use getUnchecked:
    Collection<String> result = cache.getUnchecked(target);
    // For debugging and demo (would remove in a "real" version):
    System.out.println(cache.stats());
    return result;
  }

  // This would have been a more direct way to start on building a proxy
  //  (but I like using Guava's cache)
  /*
  public Collection<String> search(String target) {
      // Pass through: call the wrapped object
      return this.wrappedSearcher.searchLines(target);
  }
   */
}
