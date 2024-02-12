package edu.brown.cs.student;

import static org.testng.AssertJUnit.assertEquals;

import edu.brown.cs.student.main.CSVParser;
import edu.brown.cs.student.main.RowCreator;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {
  CSVParser parser;

  @Before
  public void setup() {}

  /** Testing basic functionality - with and without headers */
  @Test
  public void basicParseTest() {
    try {
      FileReader reader = new FileReader("data/census/dol_ri_earnings_disparity.csv");
      CSVParser parser = new CSVParser(reader, new RowCreator());
      List<List<String>> data = parser.parse();

      /* TESTING LENGTH OF DATA */
      // With headers
      assertEquals(data.size(), 7);

      // Without headers
      reader = new FileReader("data/tests/no_headers.csv");
      parser = new CSVParser(reader, new RowCreator());
      data = parser.parse();
      assertEquals(data.size(), 8);
    } catch (IOException e) {
      System.err.println("basic parse test failed");
    }
  }

  /** testing parser using StringReader */
  @Test
  public void readerTest() {
    StringReader reader = new StringReader("cat1,cat2\nthing1,thing2\nthing3,thing4");
    CSVParser parser = new CSVParser(reader, new RowCreator());
    List<List<String>> data = parser.parse();
    assertEquals(data.size(), 3);
  }

  /** Inconsistent column count */
  @Test
  public void inconsistentColumnTest() {
    try {
      FileReader reader = new FileReader("data/malformed/malformed_signs.csv");
      CSVParser parser = new CSVParser(reader, new RowCreator());
      List<List<String>> data = parser.parse();

      // test data length
      assertEquals(13, data.size());
    } catch (IOException e) {
      System.err.println("inconsistent column test failed");
    }
  }
}
