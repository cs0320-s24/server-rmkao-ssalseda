// package edu.brown.cs.student.server;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
//
// import edu.brown.cs.student.main.server.WebAPIResponse;
// import java.io.IOException;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.Map;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
//
// public class UnitTests {
//
//  @BeforeEach
//  public void setup() {
//    // No setup
//  }
//
//  @AfterEach
//  public void teardown() {
//    // No setup
//  }
//
//  @Test
//  public void testFrom_ValidIngredientsList() throws IOException {
//
//    String nestedLists = "[[\"NAME\",\"state\"],\n"
//        + "[\"Alabama\",\"01\"],\n"
//        + "[\"Alaska\",\"02\"],\n";
//    Map<String, Object> responseMap = new HashMap<>();
//    String result = new WebAPIResponse(responseMap).serialize();
//    // This might throw an IOException, but if so JUnit will mark the test as failed.
//    assertEquals(4, soup.getIngredients().size());
//  }
//
//
//
//
//  @Test
//  public void testTo_ValidSoup() throws IOException {
//    Soup potato = new Soup("potato", Arrays.asList("potato", "water"), true);
//    String json = SoupAPIUtilities.serializeSoup(potato);
//    //System.out.println(json);
//    // Don't try to parse the string yourself to test it.
//    // Instead, use a Json library to look at the info provided.
//    Soup result = SoupAPIUtilities.deserializeSoup(json);
//    // This will FAIL if we don't define equals in Soup
//    assertEquals(potato, result);
//    // If the above produces an exception, the JUnit test will fail.
//  }
// }
