package edu.ua.cs.cs200.fall2020.team7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Database connection/handler class.  Maintains a SQLite connection and performs operations.
 * @author Noah Overcash (11907775)
 */
public class Database {
  static final String CONNECTION_URL = "jdbc:sqlite:chocan.db";
  
  protected static Connection conn = null;

  /**
   * Get a table's schema information of columns and column types.
   * @param table table name
   * @return Pairs of column name and column type ("INT" or "STRING")
   */
  public static String[][] getTableSchema(String table) {
    switch (table.toUpperCase()) {
      case "MEMBERS": {
        String[][] schema = {
            {"ID", "INT"},
            {"NAME", "STRING"},
            {"STREET_ADDRESS", "STRING"},
            {"CITY", "STRING"},
            {"STATE", "STRING"},
            {"ZIP", "INT"},
            {"STATUS", "STRING"},
        };
        return schema;
      }
      case "PROVIDERS": {
        String[][] schema = {
            {"ID", "INT"},
            {"NAME", "STRING"},
            {"STREET_ADDRESS", "STRING"},
            {"CITY", "STRING"},
            {"STATE", "STRING"},
            {"ZIP", "INT"},
        };
        return schema;
      }
      case "SERVICES": {
        String[][] schema = {
            {"ID", "INT"},
            {"NAME", "STRING"},
            {"FEE", "FLOAT"},
        };
        return schema;
      }
      case "RECORDS": {
        String[][] schema = {
            {"RECORDED_DATE_TIME", "TEXT"},
            {"SERVICE_DATE", "TEXT"},
            {"PROVIDER_ID", "INTEGER"},
            {"MEMBER_ID", "INTEGER"},
            {"SERVICE_CODE", "INTEGER"},
            {"COMMENTS", "TEXT"},
        };
        return schema;
      }
      default: {
        String[][] schema = {};
        return schema;
      }
    }
  }

  /**
   * Execute a SQL commend.
   * @param sql The command to run
   */
  protected static void runSql(String sql) {
    Database.connectIfNotConnected();
    Statement stmt;
    try {
      stmt = Database.conn.createStatement();
      // System.out.println("Running " + sql);
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Connects to the database if the connection does not already exist.
   */
  protected static void connectIfNotConnected() {
    if (conn != null) {
      return;
    }
    try { 
      Database.conn = DriverManager.getConnection(Database.CONNECTION_URL);  

      System.out.println("Connection to SQLite has been established.");

      Database.runSql("CREATE TABLE IF NOT EXISTS MEMBERS (" 
                      + "`ID` INTEGER PRIMARY KEY," 
                      + "`NAME` TEXT," 
                      + "`STREET_ADDRESS` TEXT,"
                      + "`CITY` TEXT,"
                      + "`STATE` TEXT,"
                      + "`ZIP` INTEGER,"
                      + "`STATUS` TEXT COMMENT \"either 'ACTIVE' or 'SUSPENDED'\""
                      + ")");
      Database.runSql("CREATE TABLE IF NOT EXISTS PROVIDERS (" 
                      + "`ID` INTEGER PRIMARY KEY," 
                      + "`NAME` TEXT," 
                      + "`STREET_ADDRESS` TEXT,"
                      + "`CITY` TEXT,"
                      + "`STATE` TEXT,"
                      + "`ZIP` INTEGER"
                      + ")");
      Database.runSql("CREATE TABLE IF NOT EXISTS SERVICES (" 
                      + "`ID` INTEGER PRIMARY KEY," 
                      + "`NAME` TEXT," 
                      + "`FEE` FLOAT"
                      + ")");
      // pre-fill but do not duplicate
      Database.runSql("INSERT OR IGNORE INTO SERVICES "
                      + "(`ID`, `NAME`, `FEE`) VALUES "
                      + "(279861, \"General Practitioner Consultation\", 125.00),"
                      + "(470801, \"Psychiatrist Consultation\", 125.00),"
                      + "(598470, \"Dietitian Consultation\", 125.00),"
                      + "(723889, \"Physical\", 75.00),"
                      + "(855671, \"Blood-Chocolate Level Test\", 25.00),"
                      + "(869262, \"Stomach Pump\", 59.73),"
                      + "(883948, \"Aerobic Exercise Session\", 37.50)");
      Database.runSql("CREATE TABLE IF NOT EXISTS RECORDS (" 
                      + "`RECORDED_DATE_TIME` TEXT," 
                      + "`SERVICE_DATE` TEXT," 
                      + "`PROVIDER_ID` INTEGER," 
                      + "`MEMBER_ID` INTEGER," 
                      + "`SERVICE_CODE` INTEGER," 
                      + "`COMMENTS` TEXT"
                      + ")");
    } catch (SQLException e) {  
      e.printStackTrace();
    }
  }

  /**
   * Creates the database if it does not exist.
   * By calling connectIfNotConnected, the connection is made and
   *   the initial tables created.  This method is for convenience.
   */
  public static void createDatabase() {
    Database.connectIfNotConnected();
  }

  /**
   * Add a row of data to the given table.
   * @param table table name
   * @param row data
   */
  public static void insert(String table, HashMap<String, DatabaseValue> row) {
    Database.connectIfNotConnected();
    String sql = "INSERT INTO `" + table + "` (";
    String valueString = "";
    boolean first = true;
    for (Entry<String, DatabaseValue> entry : row.entrySet()) {
      if (!first) {
        sql += ", ";
        valueString += ", ";
      }
      sql += "`" + entry.getKey() + "`";
      valueString += "?";
      first = false;
    }
    sql += ") VALUES (" + valueString + ")";
    
    try {
      PreparedStatement stmt = Database.conn.prepareStatement(sql);
      // System.out.println("Running " + sql);
      int i = 1;
      for (Entry<String, DatabaseValue> entry : row.entrySet()) {
        switch (entry.getValue().type) {
          case STRING:
            stmt.setString(i, entry.getValue().stringValue);
            break;
          case INT:
            stmt.setInt(i, entry.getValue().integerValue);
            break;
          default:
            break;
        }
        i++;
      }
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Update row(s) of data in the given table.
   * @param table table name
   * @param toUpdate the list of items to be overwritten
   * @param query a SQL-style where clause to limit the rows being modified
   */
  public static void update(String table, HashMap<String, DatabaseValue> toUpdate, String query) {
    Database.connectIfNotConnected();
    String sql = "UPDATE `" + table + "` SET ";

    boolean first = true;
    for (Entry<String, DatabaseValue> entry : toUpdate.entrySet()) {
      if (!first) {
        sql += ", ";
      }
      sql += "`" + entry.getKey() + "`=?";
      first = false;
    }

    sql += " WHERE " + query;

    try {
      PreparedStatement stmt = Database.conn.prepareStatement(sql);
      // System.out.println("Running " + sql);
      int i = 1;
      for (Entry<String, DatabaseValue> entry : toUpdate.entrySet()) {
        switch (entry.getValue().type) {
          case STRING:
            stmt.setString(i, entry.getValue().stringValue);
            break;
          case INT:
            stmt.setInt(i, entry.getValue().integerValue);
            break;
          default:
            break;
        }
        i++;
      }
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete row(s) in the given table.
   * @param table table name
   * @param query a SQL-style where clause to limit the rows being deleted
   */
  public static void delete(String table, String query) {
    Database.connectIfNotConnected();
    String sql = "DELETE FROM `" + table + "` WHERE " + query;
    Database.runSql(sql);
  }

  /**
   * Get all values from a table.
   * @param table table name
   * @return the values
   */
  public static ArrayList<HashMap<String, DatabaseValue>> getValues(String table) {
    return Database.getValues(table, "");
  }

  /**
   * Get all values from a table.
   * @param table table name
   * @param query query in SQL-friendly WHERE parameter
   * @return the values
   */
  public static ArrayList<HashMap<String, DatabaseValue>> getValues(String table, String query) {
    return Database.getValues(table, query, "");
  }

  /**
   * Get all values matching a SQL-friendly WHERE query.
   * @param table table name
   * @param query query in SQL-friendly WHERE parameter
   * @param order order in SQL-friendly ORDER BY parameter
   * @return all matching rows
   */
  public static ArrayList<HashMap<String, DatabaseValue>> getValues(String table, String query, String order) {
    Database.connectIfNotConnected();
    String sql = "SELECT * FROM `" + table + "`";
    if (query != "") {
      sql += " WHERE " + query;
    }
    if (order != "") {
      sql += " ORDER BY " + order;
    }

    ArrayList<HashMap<String, DatabaseValue>> cleanResult =
        new ArrayList<HashMap<String, DatabaseValue>>();

    try {
      Statement stmt = Database.conn.createStatement();
      // System.out.println("Running " + sql);
      ResultSet results = stmt.executeQuery(sql);
      String[][] schema = Database.getTableSchema(table);

      while (results.next()) {
        HashMap<String, DatabaseValue> row = new HashMap<String, DatabaseValue>();
        for (String[] column : schema) {
          switch (column[1]) {
            case "STRING":
            case "TEXT":
              row.put(column[0], new DatabaseValue(results.getString(column[0])));
              break;
            case "INT":
            case "INTEGER":
              row.put(column[0], new DatabaseValue(results.getInt(column[0])));
              break;
            case "FLOAT":
              row.put(column[0], new DatabaseValue(results.getFloat(column[0])));
              break;
            default:
              System.out.println("Error in getValues aggregator for column " + column[0] + ".");
          }
        }
        cleanResult.add(row);
      }

      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return cleanResult;
  }

  /**
   * Get a single row from the table with the given query.
   * @param table table name
   * @param query query in SQL-friendly WHERE parameter
   * @return the row
   */
  public static HashMap<String, DatabaseValue> getSingleRow(String table, String query) {
    ArrayList<HashMap<String, DatabaseValue>> results = Database.getValues(table, query);

    return results.size() != 0 ? results.get(0) : null;
  }
}
