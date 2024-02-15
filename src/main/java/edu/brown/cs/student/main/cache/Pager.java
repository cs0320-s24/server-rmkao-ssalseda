package edu.brown.cs.student.main.cache;

import java.util.Collection;

/**
 * Notice that there are many kinds of search that might provide this interface. The interface isn't
 * aware of files, lines, etc. It's not even aware of what the types are. When a class implements
 * this interface, it might make these type variables concrete.
 *
 * <p>We've given the type variables better names than "T" or "S"; while "T" is traditional, it's
 * often helpful to give something that describes the purpose of the variable.
 *
 * @param <REQUEST> the type of an element returned in the result
 * @param <RESPONSE> the type of the value being searched for
 */
public interface Pager<REQUEST, RESPONSE> {
  Collection<RESPONSE> page(REQUEST request);
}
