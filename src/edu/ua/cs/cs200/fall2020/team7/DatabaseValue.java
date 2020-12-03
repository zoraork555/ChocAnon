package edu.ua.cs.cs200.fall2020.team7;

/**
 * Database value wrapper class for multiple types.
 * @author Noah Overcash (11907775)
 */
public class DatabaseValue {
  /**
   * Enum to support DatabaseValue.
   */
  public enum DatabaseValueType {
    INT, STRING, FLOAT;
  }

  public DatabaseValueType type;
  public int integerValue;
  public float floatValue;
  public String stringValue;

  /**
   * Create a string database value.
   * @param value string
   */
  public DatabaseValue(String value) {
    type = DatabaseValueType.STRING;
    stringValue = value;
  }

  /**
   * Create an integer database value.
   * @param value int
   */
  public DatabaseValue(int value) {
    type = DatabaseValueType.INT;
    integerValue = value;
    floatValue = value;
  }

  /**
   * Create a float database value.
   * @param value float
   */
  public DatabaseValue(float value) {
    type = DatabaseValueType.FLOAT;
    floatValue = value;
  }
}
