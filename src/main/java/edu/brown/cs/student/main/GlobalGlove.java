package edu.brown.cs.student.main;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GlobalGlove is a global holder that is charged with the moving of data between the handlers that
 * access local CSV's. By sealing the data in an object, the data is kept safer and only modified by
 * the accepted methods. This is also what allows the data to move so easily between handlers
 */
public class GlobalGlove {
  private List<List<String>> globalFile;
  private LocalDateTime time;

  /**
   * a simple method that initializes the data given an input
   *
   * @param globalFile the data to be stored.
   */
  public void setGlobalFile(List<List<String>> globalFile) {
    this.globalFile = globalFile;
  }

  /**
   * This variable sets the time as it is crucial to pass this information along to future
   * responseMaps.
   */
  public void setTime() {
    this.time = LocalDateTime.now();
  }

  /**
   * A simple getter for time of file retrieval
   *
   * @return the time when the file was read
   */
  public LocalDateTime getTime() {
    return this.time;
  }

  /**
   * a getter for the stored data. It makes its trip out of the object for presentation.
   *
   * @return returns the main data
   */
  public List<List<String>> getGlobalFile() {
    return globalFile;
  }
  /** This method checks to see if the file of data has been initialized or not. */
  public boolean checkFull() {
    return (this.globalFile == null);
  }
}
