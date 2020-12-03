package edu.ua.cs.cs200.fall2020.team7;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;

/**
 * Bill Choc An Menu.
 * @author Cara Cannarozzi (11970792)
 */
public class BillChocAnMenu {
  /**
   * Validates that member is not suspended.
   * @param memberId Member ID number
   * @return membership validity
   */
  public static boolean validateMemberID(int memberId) {
    Member m = AccountManagementMenu.getMember(memberId);
    if (m.isSuspended()) {
      return false;
    }
    return true;
  }
  
  /**
   * Generates EFT data to bill ChocAn.
   * @param r Record of service
   */
  public static void billChocAn(Record r) {
    Provider p = AccountManagementMenu.getProvider(r.getProviderId());
    Service s = ProviderDirectory.lookupServiceByCode(r.getServiceCode());
    
    try {
      FileWriter myWriter = new FileWriter("EFT_Data.txt");
      PrintWriter printWriter = new PrintWriter(myWriter);
      printWriter.printf("Provider name: %s\n", p.getName());
      printWriter.printf("Provider ID number: %d\n", r.getProviderId());
      printWriter.printf("Service fee: %.2f\n", s.getFee());
      printWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
