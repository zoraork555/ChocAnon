package edu.ua.cs.cs200.fall2020.team7;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Object representation of a Member.
 * @author Noah Overcash (11907775), Cara Cannarozzi (11970792)
 */
public class Member extends Entity {
  protected boolean suspended;
  
  /**
   * Create a new member.
   * @param id the member ID
   * @param name the member name
   * @param address the member's address
   * @param suspended if the member is suspended
   */
  public Member(int id, String name, Address address, boolean suspended) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.suspended = suspended;
  }

  /**
   * Determine if the member is suspended.
   * @return the suspension status
   */
  public boolean isSuspended() {
    return this.suspended;
  }

  /**
   * Set the member's suspension state.
   * @param suspended the status to set
   */
  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }

  /**
   * Get all known members from the system.
   * @return all members
   */
  public static Member[] getAll() {
    ArrayList<HashMap<String, DatabaseValue>> allRows = Database.getValues("MEMBERS");

    Member[] members = new Member[allRows.size()];

    for (int i = 0; i < allRows.size(); i++) {
      members[i] = new Member(
        allRows.get(i).get("ID").integerValue,
        allRows.get(i).get("NAME").stringValue,
        new Address(
          allRows.get(i).get("STREET_ADDRESS").stringValue,
          allRows.get(i).get("CITY").stringValue,
          allRows.get(i).get("STATE").stringValue,
          allRows.get(i).get("ZIP").integerValue
        ),
        allRows.get(i).get("STATUS").stringValue == null ? false : allRows.get(i).get("STATUS").stringValue.equals("SUSPENDED")
      );
    }

    return members;
  }
}
