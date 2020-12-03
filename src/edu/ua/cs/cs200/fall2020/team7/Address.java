package edu.ua.cs.cs200.fall2020.team7;

/**
 * Basic container class for an address.
 * @author Noah Overcash (11907775)
 */
public class Address {
  protected String streetAddress;
  protected String city;
  protected String state;
  protected int zipCode;
  
  /**
   * Create a new Address object.
   * @param streetAddress street address
   * @param city city
   * @param state state
   * @param zipCode zip code
   */
  public Address(String streetAddress, String city, String state, int zipCode) {
    this.streetAddress = streetAddress;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }

  /**
   * Get string representation of address.
   */
  public String toString() {
    return streetAddress + "\n" + city + ", " + state + " " + zipCode;
  }
  
  /**
   * Get address street address.
   * @return the street address
   */
  public String getStreetAddress() {
    return streetAddress;
  }

  /**
   * Set address street address.
   * @param streetAddress the street address to set
   */
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  /**
   * Get address city.
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * Set address city.
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Get address state.
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * Set address state.
   * @param state the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Get address ZIP code.
   * @return the zipCode
   */
  public int getZip() {
    return zipCode;
  }

  /**
   * Set address ZIP code.
   * @param zipCode the ZIP code to set
   */
  public void setZip(int zipCode) {
    this.zipCode = zipCode;
  }
}
