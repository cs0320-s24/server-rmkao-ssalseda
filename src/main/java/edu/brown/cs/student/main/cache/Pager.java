package edu.brown.cs.student.main.cache;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/**
 * @param <REQUEST> the type of a sent request
 * @param <RESPONSE> the type of the value being received
 */
public interface Pager<REQUEST, RESPONSE> {
  RESPONSE pager(REQUEST request)
      throws IOException, InterruptedException, URISyntaxException, ExecutionException;
}
