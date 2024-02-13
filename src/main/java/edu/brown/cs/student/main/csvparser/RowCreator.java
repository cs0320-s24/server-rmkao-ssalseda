package edu.brown.cs.student.main.csvparser;

import java.util.List;

/** This class creates list of strings out of a list of strings. */
public class RowCreator implements CreatorFromRow<List<String>> {

  /** Constructor */
  public RowCreator() {}

  /**
   * This create function creates a list of strings out of a list of strings.
   *
   * @param row the row from the CSV
   * @return a list of strings
   * @throws FactoryFailureException
   */
  @Override
  public List<String> create(List<String> row) throws FactoryFailureException {
    return row;
  }
}
