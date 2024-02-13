package edu.brown.cs.student.main.csvparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * CSVParser uses a Reader object to parse a CSV into a generic list of objects.
 *
 * @param <T> the type of data object created from rows of the CSV
 */
public class CSVParser<T> {
  static final Pattern regexSplitCSVRow =
      Pattern.compile("\\s*,\\s*(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
  private BufferedReader in;
  private CreatorFromRow<T> rowCreator;

  /**
   * This constructor initializes instance variables
   *
   * @param reader the reader to read in data
   * @param rowCreator the CreatorFromRow class to create objects out of rows
   */
  public CSVParser(Reader reader, CreatorFromRow<T> rowCreator) {
    this.in = new BufferedReader(reader);
    this.rowCreator = rowCreator;
  }

  /**
   * This function loops through each row of the CSV, using the CreatorFromRow object to create an
   * object out of each row. It stores these objects in a list. It can detect malformed rows.
   *
   * @return the list of data objects
   */
  public List<T> parse() {
    List<T> data = new ArrayList<>();
    int rowLength = 0;

    String curLine = null;
    while (true) {
      try {
        // break while loop when we reach the end
        if (((curLine = this.in.readLine()) == null)) break;
      } catch (IOException e) {
        System.err.println(e.getMessage());
        continue;
      }
      // perform regex split on the line that was read from BufferedReader in
      List<String> row = Arrays.asList(regexSplitCSVRow.split(curLine));
      // set correct row length using first row
      if (data.isEmpty()) {
        rowLength = row.size();
      } else {
        if (row.size() != rowLength) {
          System.err.println("Found malformed row: " + row + ".");
        }
      }
      try {
        data.add(this.rowCreator.create(row));
      } catch (FactoryFailureException e) {
        System.out.println(e.getMessage() + ", skipping row " + e.row + ".");
      }
    }
    try {
      in.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException();
    }
    return data;
  }
}
