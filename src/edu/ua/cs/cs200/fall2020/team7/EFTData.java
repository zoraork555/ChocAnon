package edu.ua.cs.cs200.fall2020.team7;

//import java.util.HashMap;

/**
 * Basic entity class for EFT data.
 * @author Cara Cannarozzi (11970792), Raymond Arndorfer (12023919)
 */
public class EFTData {
  private double serviceFee;
  private int serviceCount;
  private int providerId;
  
  /**
   * Create a new EFT data object based on the given info.
   * @param p The provider's ID
   */
  public EFTData(int p) {
    this.providerId = p;
    serviceFee = 0.00;
    serviceCount = 0;
  }
  
  /**
   * Get the service fee.
   * @return the service fee
   */
  public double getFee() {
    return serviceFee;
  }
  
  public int getConsultationCount() {
    return serviceCount;
  }
  
  /**
   * Get the provider's ID number.
   * @return the provider's id
   */
  public int getProviderId() {
    return providerId;
  }
  
  /**
   * Add a service to the provider's EFT data
   * @param service the service
   */
  public void addService(Service service) {
    if (service == null) {
      return;
    }
    serviceFee += service.getFee();
    serviceCount++;
  }
}
