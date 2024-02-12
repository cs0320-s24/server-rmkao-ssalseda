package edu.brown.cs.student.main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** The Main class of our project. This is where execution begins. */
public final class Main {
  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  /** The run function gathers all user input and passes it into the Search and CSVParser classes */
  private void run() {
    boolean validInput = false;
    boolean hasHeaders;
    SearchType searchType = null;
    Scanner in = new Scanner(System.in);
    List<String> userInputList = new ArrayList<>();

    userInputList = Arrays.asList(this.args);

    if (userInputList.size() == 3) {
      if (userInputList.get(1).equals("y") || userInputList.get(1).equals("n")) {
        validInput = true;
      } else {
        System.out.println("Please enter 'y' or 'n' for [contains headers]!");
      }
    } else {
      System.out.println("Please enter a valid number of arguments!\n");
    }
    if (!validInput) {
      throw new IllegalArgumentException("User input not valid.");
    }

    // save inputs into variables
    String fileName = userInputList.get(0);
    String containsHeaders = userInputList.get(1).toLowerCase();
    String searchValue = userInputList.get(2).toLowerCase();
    hasHeaders = containsHeaders.toLowerCase().equals("y") ? true : false;

    validInput = false;
    while (!validInput) {
      System.out.print(
          "Enter 'name' to search by header name or 'column' to search by column index"
              + " (enter nothing to search all columns) ");
      String searchTypeString = in.nextLine().toLowerCase();
      switch (searchTypeString) {
        case "name":
          if (!hasHeaders) { // Error check: Searching by header in a header-less file
            System.out.println("You cannot search by header name in a header-less file!\n");
          } else {
            searchType = SearchType.NAME;
            validInput = true;
          }
          break;
        case "column":
          searchType = SearchType.COLUMN;
          validInput = true;
          break;
        case "":
          searchType = SearchType.ALL;
          validInput = true;
          break;
        default:
          System.out.println("Please enter a valid input!\n");
          break;
      }
    }

    String colIdentifier = "";
    if (searchType != SearchType.ALL) {
      System.out.print("Enter a column identifier: ");
      colIdentifier = in.nextLine().toLowerCase();
    }

    String filePath = "data/" + fileName;
    // check if the file exists
    try {
      FileReader reader = new FileReader(filePath);
      CSVParser<List<String>> parser = new CSVParser(reader, new RowCreator());
      Search searcher = new Search(parser, searchValue, hasHeaders, colIdentifier, searchType);

      // Print results
      List<List<String>> result = searcher.getResult();
      System.out.println("Found " + result.size() + " row(s) containing " + searchValue + ":");
      for (List<String> row : result) {
        System.out.println(row);
      }

    } catch (IOException e) {
      System.err.println("File '" + filePath + "' could not be found!");
    }
  }
}
