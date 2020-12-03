package edu.ua.cs.cs200.fall2020.team7;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * GUI for Operator Terminal.
 *
 * @author Peter Zhang (11861641)
 */
public class OperatorTerminal {
  private static JTextField memIdField;
  private static JTextField provIdField;
  private static JTextArea infoArea;
  private static JTextField idField;
  private static JTextField nameField;
  private static JTextField streetField;
  private static JTextField cityField;
  private static JTextField stateField;
  private static JTextField zipField;

  /** Main entry point. */
  public static void main(String[] args) {
    final Member emptyM = new Member(0, "", new Address("", "", "", 0), false);
    final Provider emptyP = new Provider(0, "", new Address("", "", "", 0));
    JFrame operatorTermFrame = new JFrame("Operator Terminal");
    operatorTermFrame.setResizable(false);
    operatorTermFrame.setSize(600, 500);
    operatorTermFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    operatorTermFrame.getContentPane().setLayout(null);

    JLabel menuLabel = new JLabel("Operator Terminal");
    menuLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
    menuLabel.setBounds(10, 10, 300, 50);
    operatorTermFrame.getContentPane().add(menuLabel);

    JLabel memberLabel = new JLabel("Enter member id:");
    memberLabel.setBounds(10, 70, 200, 15);
    operatorTermFrame.getContentPane().add(memberLabel);

    memIdField = new JTextField();
    memIdField.setBounds(10, 95, 150, 25);
    operatorTermFrame.getContentPane().add(memIdField);
    memIdField.setColumns(10);

    JButton memAddButton = new JButton("Add Member");
    memAddButton.setBounds(170, 95, 200, 25);
    memAddButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            createMemManagerGui("Add", emptyM);
            infoArea.setText("");
          }
        });
    operatorTermFrame.getContentPane().add(memAddButton);

    JButton memViewButton = new JButton("View Member");
    memViewButton.setBounds(170, 130, 200, 25);
    memViewButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Member memberGot =
                AccountManagementMenu.getMember(Integer.parseInt(memIdField.getText()));
            if (memberGot != null) {
              createMemManagerGui("View", memberGot);
              infoArea.setText("");
            } else {
              infoArea.setText(memIdField.getText() + " (Member) does not exist.");
            }
          }
        });
    operatorTermFrame.getContentPane().add(memViewButton);

    JButton memUpdButton = new JButton("Update Member");
    memUpdButton.setBounds(170, 165, 200, 25);
    memUpdButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Member memberGot =
                AccountManagementMenu.getMember(Integer.parseInt(memIdField.getText()));
            if (memberGot != null) {
              createMemManagerGui("Edit", memberGot);
              infoArea.setText("");
            } else {
              infoArea.setText(memIdField.getText() + " (Member) does not exist.");
            }
          }
        });
    operatorTermFrame.getContentPane().add(memUpdButton);

    JButton memDelButton = new JButton("Delete Member");
    memDelButton.setBounds(170, 200, 200, 25);
    memDelButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Member memberGot =
                AccountManagementMenu.getMember(Integer.parseInt(memIdField.getText()));
            if (memberGot != null) {
              AccountManagementMenu.deleteMember(memberGot.getId());
              infoArea.setText("Deleted member: " + memberGot.getId() + ".");
            } else {
              infoArea.setText(memIdField.getText() + " (Member) does not exist.");
            }
          }
        });
    operatorTermFrame.getContentPane().add(memDelButton);

    JLabel provLabel = new JLabel("Enter provider id:");
    provLabel.setBounds(10, 230, 200, 15);
    operatorTermFrame.getContentPane().add(provLabel);

    provIdField = new JTextField();
    provIdField.setColumns(10);
    provIdField.setBounds(10, 255, 150, 25);
    operatorTermFrame.getContentPane().add(provIdField);

    JButton provAddButton = new JButton("Add Provider");
    provAddButton.setBounds(170, 255, 200, 25);
    provAddButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            createProvManagerGui("Add", emptyP);
            infoArea.setText("");
          }
        });
    operatorTermFrame.getContentPane().add(provAddButton);

    JButton provViewButton = new JButton("View Provider");
    provViewButton.setBounds(170, 290, 200, 25);
    provViewButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Provider providerGot =
                AccountManagementMenu.getProvider(Integer.parseInt(provIdField.getText()));
            if (providerGot != null) {
              createProvManagerGui("View", providerGot);
              infoArea.setText("");
            } else {
              infoArea.setText(provIdField.getText() + " (Provider) does not exist.");
            }
          }
        });
    operatorTermFrame.getContentPane().add(provViewButton);

    JButton provUpdButton = new JButton("Update Provider");
    provUpdButton.setBounds(170, 325, 200, 25);
    provUpdButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Provider providerGot =
                AccountManagementMenu.getProvider(Integer.parseInt(provIdField.getText()));
            if (providerGot != null) {
              createProvManagerGui("Update", providerGot);
              infoArea.setText("");
            } else {
              infoArea.setText(provIdField.getText() + " (Provider) does not exist.");
            }
          }
        });
    operatorTermFrame.getContentPane().add(provUpdButton);

    JButton provDelButton = new JButton("Delete Provider");
    provDelButton.setBounds(170, 360, 200, 25);
    provDelButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Provider providerGot =
                AccountManagementMenu.getProvider(Integer.parseInt(provIdField.getText()));
            if (providerGot != null) {
              AccountManagementMenu.deleteProvider(providerGot.getId());
              infoArea.setText("Deleted provider: " + providerGot.getId() + ".");
            } else {
              infoArea.setText(provIdField.getText() + " (Provider) does not exist.");
            }
          }
        });
    operatorTermFrame.getContentPane().add(provDelButton);

    infoArea = new JTextArea();
    infoArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
    infoArea.setBackground(UIManager.getColor("Button.background"));
    infoArea.setBounds(10, 400, 575, 60);
    operatorTermFrame.getContentPane().add(infoArea);

    operatorTermFrame.setVisible(true);
  }

  /** Create a GUI for managing members.
   *  @param type the type of manager to open: Edit, Add, or View 
   *  @param m a member object to load in the GUI*/
  public static void createMemManagerGui(String type, Member m) {
    JFrame manageFrame = new JFrame(type + " Member");
    manageFrame.setResizable(false);
    manageFrame.setSize(400, 300);
    manageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    manageFrame.getContentPane().setLayout(null);

    JLabel idLabel = new JLabel("Id:");
    idLabel.setBounds(10, 20, 75, 20);
    manageFrame.getContentPane().add(idLabel);

    idField = new JTextField();
    idField.setBounds(100, 20, 200, 20);
    manageFrame.getContentPane().add(idField);
    idField.setColumns(10);

    JLabel nameLabel = new JLabel("Name:");
    nameLabel.setBounds(10, 50, 75, 20);
    manageFrame.getContentPane().add(nameLabel);

    nameField = new JTextField();
    nameField.setBounds(100, 50, 200, 20);
    manageFrame.getContentPane().add(nameField);
    nameField.setColumns(10);

    JLabel streetLabel = new JLabel("Street:");
    streetLabel.setBounds(10, 80, 75, 20);
    manageFrame.getContentPane().add(streetLabel);

    streetField = new JTextField();
    streetField.setColumns(10);
    streetField.setBounds(100, 80, 200, 20);
    manageFrame.getContentPane().add(streetField);

    JLabel cityLabel = new JLabel("City:");
    cityLabel.setBounds(10, 110, 75, 20);
    manageFrame.getContentPane().add(cityLabel);

    cityField = new JTextField();
    cityField.setColumns(10);
    cityField.setBounds(100, 110, 200, 20);
    manageFrame.getContentPane().add(cityField);

    JLabel stateLabel = new JLabel("State:");
    stateLabel.setBounds(10, 140, 75, 20);
    manageFrame.getContentPane().add(stateLabel);

    stateField = new JTextField();
    stateField.setColumns(10);
    stateField.setBounds(100, 140, 200, 20);
    manageFrame.getContentPane().add(stateField);

    JLabel zipLabel = new JLabel("Zip Code:");
    zipLabel.setBounds(10, 170, 75, 20);
    manageFrame.getContentPane().add(zipLabel);

    zipField = new JTextField();
    zipField.setColumns(10);
    zipField.setBounds(100, 170, 200, 20);
    manageFrame.getContentPane().add(zipField);

    JLabel suspendedLabel = new JLabel("Suspended:");
    suspendedLabel.setBounds(10, 200, 75, 20);
    manageFrame.getContentPane().add(suspendedLabel);

    JCheckBox chckbxNewCheckBox = new JCheckBox("");
    chckbxNewCheckBox.setBounds(100, 200, 100, 20);
    manageFrame.getContentPane().add(chckbxNewCheckBox);

    JButton updateButton = new JButton("Update");
    updateButton.setBounds(70, 230, 150, 25);
    updateButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int memId = Integer.parseInt(idField.getText());
            String memName = nameField.getText();
            Address memAddress =
                new Address(
                    streetField.getText(),
                    cityField.getText(),
                    stateField.getText(),
                    Integer.parseInt(zipField.getText()));
            Boolean suspended = chckbxNewCheckBox.isSelected();
            Member newM = new Member(memId, memName, memAddress, suspended);
            AccountManagementMenu.updateMember(memId, newM);
            manageFrame.dispose();
          }
        });
    manageFrame.getContentPane().add(updateButton);

    if (type.equals("View")) { // viewing mode
      idField.setEditable(false);
      nameField.setEditable(false);
      streetField.setEditable(false);
      cityField.setEditable(false);
      stateField.setEditable(false);
      zipField.setEditable(false);
      chckbxNewCheckBox.setEnabled(false);
      updateButton.setVisible(false);
    }
    if (type.equals("Add")) {
      updateButton.setEnabled(false);
      updateButton.setVisible(false);
      JButton updateButton2 = new JButton("Add");
      updateButton2.setBounds(70, 230, 150, 25);
      updateButton2.addActionListener(
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int memId = Integer.parseInt(idField.getText());
              String memName = nameField.getText();
              Address memAddress =
                  new Address(
                      streetField.getText(),
                      cityField.getText(),
                      stateField.getText(),
                      Integer.parseInt(zipField.getText()));
              Boolean suspended = chckbxNewCheckBox.isSelected();
              Member newM = new Member(memId, memName, memAddress, suspended);
              AccountManagementMenu.addMember(newM);
              manageFrame.dispose();
            }
          });
      manageFrame.getContentPane().add(updateButton2);
    }
    // update fields
    idField.setText(Integer.toString(m.getId()));
    nameField.setText(m.getName());
    streetField.setText(m.getAddress().getStreetAddress());
    cityField.setText(m.getAddress().getCity());
    stateField.setText(m.getAddress().getState());
    zipField.setText(Integer.toString(m.getAddress().getZip()));
    if (m.isSuspended()) {
      chckbxNewCheckBox.setSelected(true);
    } else {
      chckbxNewCheckBox.setSelected(false);
    }
    if (idField.getText().equals("0")) { 
      idField.setText("");
    }
    if (zipField.getText().equals("0")) {
      zipField.setText("");
    }
    manageFrame.setVisible(true);
  }
  
  /** Create a GUI for managing providers.
   *  @param type the type of manager to open: Edit, Add, or View 
   *  @param p a provider object to load in the GUI*/
  public static void createProvManagerGui(String type, Provider p) {
    JFrame manageFrame = new JFrame(type + " Provider");
    manageFrame.setResizable(false);
    manageFrame.setSize(400, 300);
    manageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    manageFrame.getContentPane().setLayout(null);

    JLabel idLabel = new JLabel("Id:");
    idLabel.setBounds(10, 20, 75, 20);
    manageFrame.getContentPane().add(idLabel);

    idField = new JTextField();
    idField.setBounds(100, 20, 200, 20);
    manageFrame.getContentPane().add(idField);
    idField.setColumns(10);

    JLabel nameLabel = new JLabel("Name:");
    nameLabel.setBounds(10, 50, 75, 20);
    manageFrame.getContentPane().add(nameLabel);

    nameField = new JTextField();
    nameField.setBounds(100, 50, 200, 20);
    manageFrame.getContentPane().add(nameField);
    nameField.setColumns(10);

    JLabel streetLabel = new JLabel("Street:");
    streetLabel.setBounds(10, 80, 75, 20);
    manageFrame.getContentPane().add(streetLabel);

    streetField = new JTextField();
    streetField.setColumns(10);
    streetField.setBounds(100, 80, 200, 20);
    manageFrame.getContentPane().add(streetField);

    JLabel cityLabel = new JLabel("City:");
    cityLabel.setBounds(10, 110, 75, 20);
    manageFrame.getContentPane().add(cityLabel);

    cityField = new JTextField();
    cityField.setColumns(10);
    cityField.setBounds(100, 110, 200, 20);
    manageFrame.getContentPane().add(cityField);

    JLabel stateLabel = new JLabel("State:");
    stateLabel.setBounds(10, 140, 75, 20);
    manageFrame.getContentPane().add(stateLabel);

    stateField = new JTextField();
    stateField.setColumns(10);
    stateField.setBounds(100, 140, 200, 20);
    manageFrame.getContentPane().add(stateField);

    JLabel zipLabel = new JLabel("Zip Code:");
    zipLabel.setBounds(10, 170, 75, 20);
    manageFrame.getContentPane().add(zipLabel);

    zipField = new JTextField();
    zipField.setColumns(10);
    zipField.setBounds(100, 170, 200, 20);
    manageFrame.getContentPane().add(zipField);

    JButton updateButton = new JButton("Update");
    updateButton.setBounds(70, 230, 150, 25);
    updateButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int provId = Integer.parseInt(idField.getText());
            String provName = nameField.getText();
            Address provAddress =
                new Address(
                    streetField.getText(),
                    cityField.getText(),
                    stateField.getText(),
                    Integer.parseInt(zipField.getText()));
            Provider newP = new Provider(provId, provName, provAddress);
            AccountManagementMenu.updateProvider(provId, newP);
            manageFrame.dispose();
          }
        });
    manageFrame.getContentPane().add(updateButton);

    if (type.equals("View")) { // viewing mode
      idField.setEditable(false);
      nameField.setEditable(false);
      streetField.setEditable(false);
      cityField.setEditable(false);
      stateField.setEditable(false);
      zipField.setEditable(false);
      updateButton.setVisible(false);
    }
    if (type.equals("Add")) {
      updateButton.setEnabled(false);
      updateButton.setVisible(false);
      JButton updateButton2 = new JButton("Add");
      updateButton2.setBounds(70, 230, 150, 25);
      updateButton2.addActionListener(
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int provId = Integer.parseInt(idField.getText());
              String provName = nameField.getText();
              Address provAddress =
                  new Address(
                      streetField.getText(),
                      cityField.getText(),
                      stateField.getText(),
                      Integer.parseInt(zipField.getText()));
              Provider newP = new Provider(provId, provName, provAddress);
              AccountManagementMenu.addProvider(newP);
              manageFrame.dispose();
            }
          });
      manageFrame.getContentPane().add(updateButton2);
    }
    // update fields
    idField.setText(Integer.toString(p.getId()));
    nameField.setText(p.getName());
    streetField.setText(p.getAddress().getStreetAddress());
    cityField.setText(p.getAddress().getCity());
    stateField.setText(p.getAddress().getState());
    zipField.setText(Integer.toString(p.getAddress().getZip()));

    if (idField.getText().equals("0")) {
      idField.setText("");
    }
    if (zipField.getText().equals("0")) {
      zipField.setText("");
    }
    manageFrame.setVisible(true);
  }
}
