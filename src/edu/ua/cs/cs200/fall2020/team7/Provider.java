package edu.ua.cs.cs200.fall2020.team7;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Object representation of a Provider.
 * @author Noah Overcash (11907775)
 */
public class Provider extends Entity {
  /**
   * Create a new member.
   * @param id the member ID
   * @param name the member name
   * @param address the member's address
   */
  public Provider(int id, String name, Address address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  /**
   * Get all known providers from the system.
   * @return all providers
   */
  public static Provider[] getAll() {
    ArrayList<HashMap<String, DatabaseValue>> allRows = Database.getValues("PROVIDERS");

    Provider[] providers = new Provider[allRows.size()];

    for (int i=0; i < allRows.size(); i++) {
      providers[i] = new Provider(
        allRows.get(i).get("ID").integerValue,
        allRows.get(i).get("NAME").stringValue,
        new Address(
          allRows.get(i).get("STREET_ADDRESS").stringValue,
          allRows.get(i).get("CITY").stringValue,
          allRows.get(i).get("STATE").stringValue,
          allRows.get(i).get("ZIP").integerValue
        )
      );
    }

    return providers;
  }
}
