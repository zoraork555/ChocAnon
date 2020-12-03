package edu.ua.cs.cs200.fall2020.team7;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * GUI for Manager Terminal.
 *
 * @author Peter Zhang (11861641)
 */
public class ManagerTerminal {
  private static JTextField memIdField;
  private static JTextField provIdField;
  private static JTextArea infoArea;
  private static JComboBox minutesBox;
  private static JComboBox hoursBox;

  /** Main entry point. */
  public static void main(String[] args) {

    JFrame managerTermFrame = new JFrame("Manager Terminal");
    managerTermFrame.setResizable(false);
    managerTermFrame.setSize(600, 500);
    managerTermFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    managerTermFrame.getContentPane().setLayout(null);

    JLabel menuLabel = new JLabel("Manager Terminal");
    menuLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
    menuLabel.setBounds(10, 10, 300, 50);
    managerTermFrame.getContentPane().add(menuLabel);

    JLabel memberLabel = new JLabel("Enter member id:");
    memberLabel.setBounds(10, 70, 200, 15);
    managerTermFrame.getContentPane().add(memberLabel);

    memIdField = new JTextField();
    memIdField.setBounds(10, 95, 150, 25);
    managerTermFrame.getContentPane().add(memIdField);
    memIdField.setColumns(10);

    JButton memButton = new JButton("Generate Member Report");
    memButton.setBounds(170, 95, 200, 25);
    memButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int memberId = Integer.parseInt(memIdField.getText());
            Member memberGot = AccountManagementMenu.getMember(memberId);
            if (memberGot != null) { // verify and get from databate then print
              ReportGenerator.generateMemberReport(memberGot);
              infoArea.setText(
                  "Successfully generated member report for member: " + memberId + ".");
            } else {
              infoArea.setText(memberId + " (Member) does not exist.");
            }
          }
        });
    managerTermFrame.getContentPane().add(memButton);

    JLabel provLabel = new JLabel("Enter provider id:");
    provLabel.setBounds(10, 130, 200, 15);
    managerTermFrame.getContentPane().add(provLabel);

    provIdField = new JTextField();
    provIdField.setColumns(10);
    provIdField.setBounds(10, 155, 150, 25);
    managerTermFrame.getContentPane().add(provIdField);

    JButton provButton = new JButton("Generate Provider Report");
    provButton.setBounds(170, 155, 200, 25);
    provButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int providerId = Integer.parseInt(provIdField.getText());
            Provider providerGot = AccountManagementMenu.getProvider(providerId);
            if (providerGot != null) { // verify and get from databate then print
              ReportGenerator.generateProviderReport(providerGot);
              infoArea.setText(
                  "Successfully generated provider report for provider: " + providerId + ".");
            } else {
              infoArea.setText(providerId + " (Provider) does not exist.");
            }
          }
        });
    managerTermFrame.getContentPane().add(provButton);

    JButton summaryButton = new JButton("Generate Summary Report");
    summaryButton.setBounds(170, 215, 200, 25);
    summaryButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // generate summary report TODO make sure reports are generatable
            if (true) {
              ReportGenerator.generateSummaryReport();
              infoArea.setText("Successfully generated summary report.");
            }
          }
        });
    managerTermFrame.getContentPane().add(summaryButton);

    JButton eftButton = new JButton("Generate EFT Report");
    eftButton.setBounds(170, 275, 200, 25);
    eftButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // generate eft report TODO make sure reports are generatable
            if (true) {
              ReportGenerator.generateEFTReport();
              infoArea.setText("Successfully generated EFT report.");
            }
          }
        });
    managerTermFrame.getContentPane().add(eftButton);

    infoArea = new JTextArea();
    infoArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
    infoArea.setBackground(UIManager.getColor("Button.background"));
    infoArea.setEditable(false);
    infoArea.setBounds(10, 320, 575, 140);
    managerTermFrame.getContentPane().add(infoArea);

    minutesBox = new JComboBox();
    minutesBox.setModel(
        new DefaultComboBoxModel(
            new String[] {
              "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
              "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
              "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41",
              "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55",
              "56", "57", "58", "59"
            }));
    minutesBox.setBounds(510, 95, 40, 20);
    managerTermFrame.getContentPane().add(minutesBox);

    hoursBox = new JComboBox();
    hoursBox.setModel(
        new DefaultComboBoxModel(
            new String[] {
              "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
              "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"
            }));
    hoursBox.setBounds(450, 95, 40, 20);
    managerTermFrame.getContentPane().add(hoursBox);

    JLabel timerLabel = new JLabel("Timer Override:");
    timerLabel.setBounds(450, 70, 100, 15);
    managerTermFrame.getContentPane().add(timerLabel);

    JButton overrideButton = new JButton("Override");
    overrideButton.setBounds(450, 125, 100, 25);
    overrideButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int hour = Integer.parseInt(hoursBox.getSelectedItem().toString());
            int min = Integer.parseInt(minutesBox.getSelectedItem().toString());
            int timeInMilli = (hour * 60 * 60 * 1000) + (min * 60 * 1000) - 64800000;
            Time newTime = new Time(timeInMilli);
            infoArea.setText("Changed main accounting procedure time to: " + newTime + ".");
          }
        });
    managerTermFrame.getContentPane().add(overrideButton);

    JButton forceRunButton = new JButton("Force Run");
    forceRunButton.setBounds(450, 160, 100, 25);
    forceRunButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            ReportGenerator.runMainAccountingProcedure();
            infoArea.setText("Force running main accounting procedure.");
          }
        });
    managerTermFrame.getContentPane().add(forceRunButton);

    managerTermFrame.setVisible(true);
  }
}
