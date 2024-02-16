package edu.brown.cs.student.main.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * A class that wraps a FileServer instance and caches responses for efficiency. Notice that the
 * interface hasn't changed at all. This is an example of the proxy pattern; callers will interact
 * with the CachedFileServer, rather than the "real" data source.
 *
 * <p>This version uses a Guava cache class to manage the cache.
 */
public class CachedFilePager implements Pager<String, HttpResponse<String>> {
  private final Pager<String, HttpResponse<String>> wrappedSearcher;
  private final LoadingCache<String, HttpResponse<String>> cache;

  /**
   * Proxy class: wrap an instance of Searcher (of any kind) and cache its results.
   *
   * <p>There are _many_ ways to implement this! We could use a plain HashMap, but then we'd have to
   * handle "eviction" ourselves. Lots of libraries exist. We're using Guava here, to demo the
   * strategy pattern.
   *
   * @param toWrap the Searcher to wrap
   */
  public CachedFilePager(Pager<String, HttpResponse<String>> toWrap) {
    this.wrappedSearcher = toWrap;

    // Look at the docs -- there are lots of builder parameters you can use
    //   including ones that affect garbage-collection (not needed for Server).
    this.cache =
        CacheBuilder.newBuilder()
            // How many entries maximum in the cache?
            .maximumSize(25)
            // How long should entries remain in the cache?
            .expireAfterWrite(5, TimeUnit.MINUTES)
            // Keep statistical info around for profiling purposes
            .recordStats()
            .build(
                // Strategy pattern: how should the cache behave when
                // it's asked for something it doesn't have?
                new CacheLoader<>() {
                  @Override

                  public HttpResponse<String> load(String key)
                      throws IOException, InterruptedException, URISyntaxException, ExecutionException {
                    System.out.println("called load for: " + key);
                    // If this isn't yet present in the cache, load it:
                    return wrappedSearcher.pager(key);
                  }
                });
  }

  @Override
  public HttpResponse<String> pager(String target) throws ExecutionException {
    // "get" is designed for concurrent situations; for today, use getUnchecked:
    HttpResponse<String> result = cache.getUnchecked(target);
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
