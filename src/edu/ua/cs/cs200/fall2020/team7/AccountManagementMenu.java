package edu.ua.cs.cs200.fall2020.team7;

import java.util.HashMap;

/**
 * Account management menu.  Implements basic storage features for user/providers.
 * @author Noah Overcash (11907775)
 */
public class AccountManagementMenu {
  /**
   * Add a Member to the database.
   * @param m Member data
   */
  public static void addMember(Member m) {
    HashMap<String, DatabaseValue> rowData = new HashMap<String, DatabaseValue>();
    rowData.put("ID", new DatabaseValue(m.getId()));
    rowData.put("NAME", new DatabaseValue(m.getName()));
    rowData.put("STREET_ADDRESS", new DatabaseValue(m.getAddress().getStreetAddress()));
    rowData.put("CITY", new DatabaseValue(m.getAddress().getCity()));
    rowData.put("STATE", new DatabaseValue(m.getAddress().getState()));
    rowData.put("ZIP", new DatabaseValue(m.getAddress().getZip()));
    rowData.put("STATUS", new DatabaseValue(m.isSuspended() ? "SUSPENDED" : "ACTIVE"));
    
    Database.insert("MEMBERS", rowData);
  }

  /**
   * Get a member from the database.
   * @param memberId member ID
   * @return The member or null if it does not exist
   */
  public static Member getMember(int memberId) {
    HashMap<String, DatabaseValue> row = Database.getSingleRow("MEMBERS", "`ID`=" + memberId);

    if (row == null) {
      return null;
    }

    return new Member(
      row.get("ID").integerValue,
      row.get("NAME").stringValue,
      new Address(
        row.get("STREET_ADDRESS").stringValue,
        row.get("CITY").stringValue,
        row.get("STATE").stringValue,
        row.get("ZIP").integerValue
      ),
      row.get("STATUS").stringValue == null ? false : row.get("STATUS").stringValue.equals("SUSPENDED")
    );
  }

  /**
   * Overwrite a Member in the database.
   * @param memberId Member ID number
   * @param m Member data
   */
  public static void updateMember(int memberId, Member m) {
    HashMap<String, DatabaseValue> rowData = new HashMap<String, DatabaseValue>();
    rowData.put("ID", new DatabaseValue(m.getId()));
    rowData.put("NAME", new DatabaseValue(m.getName()));
    rowData.put("STREET_ADDRESS", new DatabaseValue(m.getAddress().getStreetAddress()));
    rowData.put("CITY", new DatabaseValue(m.getAddress().getCity()));
    rowData.put("STATE", new DatabaseValue(m.getAddress().getState()));
    rowData.put("ZIP", new DatabaseValue(m.getAddress().getZip()));
    rowData.put("STATUS", new DatabaseValue(m.isSuspended() ? "SUSPENDED" : "ACTIVE"));
    
    Database.update("MEMBERS", rowData, "`ID`=" + memberId);
  }

  /**
   * Delete a Member from the database.
   * @param memberId Member ID number
   */
  public static void deleteMember(int memberId) {
    Database.delete("MEMBERS", "`ID`=" + memberId);
  }


  /**
   * Add a Provider to the database.
   * @param p Provider data
   */
  public static void addProvider(Provider p) {
    HashMap<String, DatabaseValue> rowData = new HashMap<String, DatabaseValue>();
    rowData.put("ID", new DatabaseValue(p.getId()));
    rowData.put("NAME", new DatabaseValue(p.getName()));
    rowData.put("STREET_ADDRESS", new DatabaseValue(p.getAddress().getStreetAddress()));
    rowData.put("CITY", new DatabaseValue(p.getAddress().getCity()));
    rowData.put("STATE", new DatabaseValue(p.getAddress().getState()));
    rowData.put("ZIP", new DatabaseValue(p.getAddress().getZip()));
    
    Database.insert("PROVIDERS", rowData);
  }

  /**
   * Get a member from the database.
   * @param providerId member ID
   * @return The member or null if it does not exist
   */
  public static Provider getProvider(int providerId) {
    HashMap<String, DatabaseValue> row = Database.getSingleRow("PROVIDERS", "`ID`=" + providerId);

    if (row == null) {
      return null;
    }

    return new Provider(
      row.get("ID").integerValue,
      row.get("NAME").stringValue,
      new Address(
        row.get("STREET_ADDRESS").stringValue,
        row.get("CITY").stringValue,
        row.get("STATE").stringValue,
        row.get("ZIP").integerValue
      )
    );
  }

  /**
   * Overwrite a Provider in the database.
   * @param providerId Provider ID number
   * @param p Provider data
   */
  public static void updateProvider(int providerId, Provider p) {
    HashMap<String, DatabaseValue> rowData = new HashMap<String, DatabaseValue>();
    rowData.put("ID", new DatabaseValue(p.getId()));
    rowData.put("NAME", new DatabaseValue(p.getName()));
    rowData.put("STREET_ADDRESS", new DatabaseValue(p.getAddress().getStreetAddress()));
    rowData.put("CITY", new DatabaseValue(p.getAddress().getCity()));
    rowData.put("STATE", new DatabaseValue(p.getAddress().getState()));
    rowData.put("ZIP", new DatabaseValue(p.getAddress().getZip()));
    
    Database.update("PROVIDERS", rowData, "`ID`=" + providerId);
  }

  /**
   * Delete a Provider from the database.
   * @param providerId Provider ID number
   */
  public static void deleteProvider(int providerId) {
    Database.delete("PROVIDERS", "`ID`=" + providerId);
  }
}
