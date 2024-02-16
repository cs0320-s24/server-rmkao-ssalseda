package edu.brown.cs.student.main.csvparser;

import java.util.ArrayList;
import java.util.List;

/**
 * This class uses a CSVParser to get parsed data and then searches through the data based on the
 * search criteria. It stores the matching rows in an instance variable.
 */
public class Search {
  private String searchValue;
  private List<List<String>> result;

  /**
   * This constructor calls parse, saves the returned data, processes it, then searches through it
   *
   * @param parser object to parse CSV file
   * @param searchValue the String that the user is searching for
   * @param hasHeaders whether the CSV file has headers
   * @param colIdentifier the column identifier (header name or index)
   * @param searchType the type of search (name, column, all)
   */
  public Search(
      CSVParser<List<String>> parser,
      String searchValue,
      boolean hasHeaders,
      String colIdentifier,
      SearchType searchType) {
    this.searchValue = searchValue;
    List<List<String>> data = null;
    data = parser.parse();

    // error check: Nothing was parsed
    if (data.isEmpty()) {
      throw new RuntimeException("No data from CSV was able to be parsed!");
    }
    //    if (hasHeaders) {
    //      data.remove(0);
    //    }
    if (searchType == SearchType.ALL) {
      this.result = this.searchData(data, searchValue);
    } else {
      int colIndex = this.getColIndex(data, colIdentifier, searchType);
      this.result = this.searchData(data, searchValue, colIndex);
    }
  }
  /// special search function that takes raw data not CSVParser
  public Search(
      List<List<String>> data,
      String searchValue,
      boolean hasHeaders,
      String colIdentifier,
      SearchType searchType) {
    this.searchValue = searchValue;

    // error check: Nothing was parsed
    if (data.isEmpty()) {
      throw new RuntimeException("No data from CSV was able to be parsed!");
    }
    int colIndex = this.getColIndex(data, colIdentifier, searchType);

    //    if (hasHeaders) {
    //      data.remove(0);
    //    }
    if (searchType == SearchType.ALL) {
      this.result = this.searchData(data, searchValue);
    } else {
      this.result = this.searchData(data, searchValue, colIndex);
    }
  }

  /**
   * This searchData searches for a certain value through the column with the given index
   *
   * @param data the parsed CSV data
   * @param searchValue the String to search for
   * @param colIndex the column to search in
   */
  public List<List<String>> searchData(List<List<String>> data, String searchValue, int colIndex) {
    List<List<String>> result = new ArrayList<>();
    for (List<String> row : data) {
      try {
        if (row.get(colIndex).toLowerCase().equals(searchValue)) {
          result.add(row);
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Could not search in row " + row + " (malformed).");
      }
    }
    if (result.isEmpty()) {
      // searchValue couldn't be found: return empty list
      System.out.println(
          "String '" + searchValue + "' could not be found in column " + colIndex + "!");
    }
    return result;
  }

  /**
   * This searchData searches for a certain value through every column
   *
   * @param data the parsed CSV data
   * @param searchValue the String to search for
   */
  public List<List<String>> searchData(List<List<String>> data, String searchValue) {
    List<List<String>> result = new ArrayList<>();
    for (List<String> row : data) {
      for (String token : row) {
        if (token.toLowerCase().equals(searchValue)) {
          result.add(row);
          break;
        }
      }
    }
    if (result.isEmpty()) {
      // searchValue couldn't be found: return empty list
      System.out.println("String '" + searchValue + "' could not be found!");
    }
    return result;
  }

  /**
   * This function gets the index of the column that the user inputted
   *
   * @param data the parsed data
   * @param colIdentifier the inputted column identifier (header name or index)
   * @param searchType the type of search (by name or by index)
   * @return the index of the column the user wants to search or -1 if searching all
   */
  private int getColIndex(List<List<String>> data, String colIdentifier, SearchType searchType) {
    if (searchType == SearchType.NAME) {

      // loop through header row to find matching header name's index
      for (int i = 0; i < data.get(0).size(); i++) {
        if (data.get(0).get(i).toLowerCase().equals(colIdentifier)) {
          return i;
        }
      }
      throw new IllegalArgumentException(
          "Could not find a column header with the name '" + colIdentifier + "'");
    } else if (searchType == SearchType.COLUMN) {
      int rowLength = data.get(0).size();
      int col = Integer.parseInt(colIdentifier);

      // Error check: col is of bounds
      if (col < 0 || col >= rowLength) {
        throw new IndexOutOfBoundsException("Index " + col + " is out of bounds!");
      }
      return Integer.parseInt(colIdentifier);
    }
    return -1;
  }

  /**
   * Getter for the final results of the search
   *
   * @return the rows that matched the search criteria
   */
  public List<List<String>> getResult() {
    return this.result;
  }
}
