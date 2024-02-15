package edu.brown.cs.student.main;

import java.time.LocalDateTime;
import java.util.List;

public class GlobalGlove {
  private List<List<String>> globalFile;
  private LocalDateTime time;

  public void setGlobalFile(List<List<String>> globalFile) {
    this.globalFile = globalFile;
  }

  public void setTime() {
    this.time = LocalDateTime.now();
  }

  public LocalDateTime getTime() {
    return this.time;
  }

  public List<List<String>> getGlobalFile() {
    return globalFile;
  }

  public boolean checkFull() {
    return (this.globalFile == null);
  }
}
