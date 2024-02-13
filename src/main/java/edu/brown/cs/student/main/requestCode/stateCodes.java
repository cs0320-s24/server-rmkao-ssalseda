package edu.brown.cs.student.main.requestCode;

public class stateCodes {
  private String NAME;
  private String state;

  public stateCodes(String name, String state) {
    this.NAME = name;
    this.state = state;
  }

  public stateCodes() {}

    @Override
   public String toString() {
     return "For " + this.NAME + " the code is " + this.state;
  }
}
