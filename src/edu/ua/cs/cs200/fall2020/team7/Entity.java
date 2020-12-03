package edu.ua.cs.cs200.fall2020.team7;

/**
 * Basic entity class for a member/provider.  Implemented concretely by Provider, Member
 * @author Noah Overcash (11907775)
 */
public abstract class Entity {
  protected int id;
  protected String name;
  protected Address address;

  /**
   * Get the entity ID.
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Set the entity ID.
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }
  
  /**
   * Get the entity name.
   * @return the name
   */
  public String getName() {
    return name;
  }
  
  /**
   * Set the entity name.
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Get the entity address.
   * @return the address
   */
  public Address getAddress() {
    return address;
  }
  
  /**
   * Set the entity address.
   * @param address the address to set
   */
  public void setAddress(Address address) {
    this.address = address;
  }
  
  /**
   * Get string representation.
   * For example, "Member ID 123456789 (name Joe Smith)".
   */
  public String toString() {
    return this.getClass().getSimpleName() + " #" + this.getId() + " (" + this.getName() + ")";
  }
}
