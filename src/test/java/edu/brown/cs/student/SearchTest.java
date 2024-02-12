package edu.brown.cs.student;

import static org.testng.AssertJUnit.assertEquals;

import edu.brown.cs.student.main.CSVParser;
import edu.brown.cs.student.main.RowCreator;
import edu.brown.cs.student.main.Search;
import edu.brown.cs.student.main.SearchType;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

/** This class tests Search.java */
public class SearchTest {

  @Test
  public void isPresent() {
    try {
      // PRESENT
      FileReader reader = new FileReader("data/census/dol_ri_earnings_disparity.csv");
      CSVParser parser = new CSVParser(reader, new RowCreator());

      Search searcher = new Search(parser, "$673.14", true, "1", SearchType.ALL);
      List<List<String>> result = searcher.getResult();
      assertEquals(1, result.size());
      assertEquals("[[RI, Hispanic/Latino, $673.14, 74596.18851, $0.64, 14%]]", result.toString());
    } catch (IOException e) {
      System.err.println("search basic test failed");
    }
  }

  @Test
  public void isNotPresent() {
    // NOT PRESENT
    try {
      FileReader reader = new FileReader("data/census/dol_ri_earnings_disparity.csv");
      CSVParser parser = new CSVParser(reader, new RowCreator());

      Search searcher = new Search(parser, "WHEEEEEEE", true, "1", SearchType.ALL);
      List<List<String>> result = searcher.getResult();
      assertEquals(0, result.size());
      assertEquals("[]", result.toString());
    } catch (IOException e) {
      System.err.println("search basic test failed");
    }
  }

  @Test
  public void presentWrongColumn() {
    // NOT PRESENT
    try {
      FileReader reader = new FileReader("data/census/dol_ri_earnings_disparity.csv");
      CSVParser parser = new CSVParser(reader, new RowCreator());

      Search searcher = new Search(parser, "$471.07", true, "1", SearchType.COLUMN);
      List<List<String>> result = searcher.getResult();
      assertEquals(0, result.size());
      assertEquals("[]", result.toString());
    } catch (IOException e) {
      System.err.println("present wrong column test failed");
    }
  }

  @Test
  public void byColumnName() {
    // NOT PRESENT
    try {
      FileReader reader = new FileReader("data/census/dol_ri_earnings_disparity.csv");
      CSVParser parser = new CSVParser(reader, new RowCreator());

      Search searcher = new Search(parser, "2%", true, "Employed Percent", SearchType.NAME);
      List<List<String>> result = searcher.getResult();
      assertEquals(1, result.size());
      assertEquals("[[RI,Multiracial, $971.89 ,8883.049171, $0.92 ,2%]]", result.toString());
    } catch (IOException e) {
      System.err.println("by column name test failed");
    }
  }
}
