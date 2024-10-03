package gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import common.Competitor;
import decathlon.*;
import excel.ExcelPrinter;
import excel.ExcelReader;
import heptathlon.*;


public class MainGUI {
    private JTextField nameField;
    private JTextField resultField;
    private JComboBox<String> disciplineBox;
    private JTextArea outputArea;
    private JLabel resultLabel;

    private JRadioButton radioButtonDeca;
    private JRadioButton radioButtonHepta;
    private String eventToCalculate = "Decathlon";
    private String minValue = "";
    private String maxValue = "";
    private int numberOfCompetitors = 0;

    private ArrayList<Competitor> competitors = new ArrayList<>();
    String[] disciplines = {
            "100m", "400m", "1500m", "110m Hurdles",
            "Long Jump", "High Jump", "Pole Vault",
            "Discus Throw", "Javelin Throw", "Shot Put"
    };

    public static void main(String[] args) {
        new MainGUI().createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Track and Field Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        JPanel panel = new JPanel(new GridLayout(7, 1)); // Updated from 6 to 7 rows

        // Radio buttons
        radioButtonDeca = new JRadioButton();
        radioButtonDeca.setText("Decathlon");
        radioButtonDeca.addActionListener(radioButtonListener);
        radioButtonHepta = new JRadioButton();
        radioButtonHepta.setText("Heptathlon");
        radioButtonHepta.addActionListener(radioButtonListener);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonDeca);
        buttonGroup.add(radioButtonHepta);

        panel.add(radioButtonDeca);
        panel.add(radioButtonHepta);
        radioButtonDeca.setSelected(true);


        // Input for competitor's name
        nameField = new JTextField(20);
        panel.add(new JLabel("Enter Competitor's Name:"));
        panel.add(nameField);

        // Dropdown for selecting discipline, adding disciplines
        disciplineBox = new JComboBox<>(disciplines);
        panel.add(new JLabel("Select Discipline:"));
        panel.add(disciplineBox);

        // Input for result
        resultField = new JTextField(10);
        Deca100M deca100M = new Deca100M();
        minValue = String.valueOf(deca100M.getMinBoundaryValue());
        maxValue = String.valueOf(deca100M.getMaxBoundaryValue());
        resultLabel = new JLabel("Enter Result (" + minValue + "-" + maxValue + " s):");
        panel.add(resultLabel);
        panel.add(resultField);

        // Button to calculate and display result
        JButton calculateButton = new JButton("Calculate Score");
        calculateButton.addActionListener(new CalculateButtonListener());
        panel.add(calculateButton);

        // Output area
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);

        // Added: Button to export results to Excel
        JButton exportButton = new JButton("Export to Excel");
        exportButton.addActionListener(new ExportButtonListener());  // New export button listener
        panel.add(exportButton);  // Add export button to the panel

        frame.add(panel);
        frame.setVisible(true);

        // Button to save to Excel
//        JButton saveToExcelButton = new JButton("Save to Excel");
//        panel.add(saveToExcelButton);
//        saveToExcelButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    // Split the text area content into lines
//                    String[] lines = outputArea.getText().split("\n");
//                    Object[][] data = new Object[lines.length][1];  // Assuming single column for simplicity
//
//                    // Fill data with lines
//                    for (int i = 0; i < lines.length; i++) {
//                        data[i][0] = lines[i];
//                    }
//
//                    ExcelPrinter printer = new ExcelPrinter("competition_results");
//                    printer.add(data, "Results");
//                    printer.write();
//                    JOptionPane.showMessageDialog(null, "Results saved to Excel!");
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(null, "Error writing to Excel file.");
//                }
//            }
//        });
//        panel.add(saveToExcelButton);


        // Button to load from Excel
        JButton loadFromExcelButton = new JButton("Load from Excel");
        panel.add(loadFromExcelButton);
        loadFromExcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ExcelReader reader = new ExcelReader();
                    StringBuilder loadedData = new StringBuilder();

                    // Read the first 10 rows and 1 column (adjust as needed)
                    for (int i = 0; i < 10; i++) {
                        String data = reader.getCellInfo("competition_results", 0, i, 0);
                        loadedData.append(data).append("\n");
                    }

                    outputArea.setText(loadedData.toString());
                    JOptionPane.showMessageDialog(null, "Results loaded from Excel!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error reading from Excel file.");
                }
            }
        });
        panel.add(loadFromExcelButton);


        disciplineBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String discipline = (String) disciplineBox.getSelectedItem();
                if (eventToCalculate.equals("Decathlon"))
                {
                    switch (discipline) {
                        case "100m":
                            Deca100M deca100M = new Deca100M();
                            minValue = String.valueOf(deca100M.getMinBoundaryValue());
                            maxValue = String.valueOf(deca100M.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " s):");
                            break;
                        case "400m":
                            Deca400M deca400M = new Deca400M();
                            minValue = String.valueOf(deca400M.getMinBoundaryValue());
                            maxValue = String.valueOf(deca400M.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " s):");
                            break;
                        case "1500m":
                            Deca1500M deca1500M = new Deca1500M();
                            minValue = String.valueOf(deca1500M.getMinBoundaryValue());
                            maxValue = String.valueOf(deca1500M.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " s):");
                            break;
                        case "110m Hurdles":
                            Deca110MHurdles deca110MHurdles = new Deca110MHurdles();
                            minValue = String.valueOf(deca110MHurdles.getMinBoundaryValue());
                            maxValue = String.valueOf(deca110MHurdles.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " s):");
                            break;
                        case "Long Jump":
                            DecaLongJump decaLongJump = new DecaLongJump();
                            minValue = String.valueOf(decaLongJump.getMinBoundaryValue());
                            maxValue = String.valueOf(decaLongJump.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " cm):");
                            break;
                        case "High Jump":
                            DecaHighJump decaHighJump = new DecaHighJump();
                            minValue = String.valueOf(decaHighJump.getMinBoundaryValue());
                            maxValue = String.valueOf(decaHighJump.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " cm):");
                            break;
                        case "Pole Vault":
                            DecaPoleVault decaPoleVault = new DecaPoleVault();
                            minValue = String.valueOf(decaPoleVault.getMinBoundaryValue());
                            maxValue = String.valueOf(decaPoleVault.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " cm):");
                            break;
                        case "Discus Throw":
                            DecaDiscusThrow decaDiscusThrow = new DecaDiscusThrow();
                            minValue = String.valueOf(decaDiscusThrow.getMinBoundaryValue());
                            maxValue = String.valueOf(decaDiscusThrow.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " m):");
                            break;
                        case "Javelin Throw":
                            DecaJavelinThrow decaJavelinThrow = new DecaJavelinThrow();
                            minValue = String.valueOf(decaJavelinThrow.getMinBoundaryValue());
                            maxValue = String.valueOf(decaJavelinThrow.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " m):");
                            break;
                        case "Shot Put":
                            DecaShotPut decaShotPut = new DecaShotPut();
                            minValue = String.valueOf(decaShotPut.getMinBoundaryValue());
                            maxValue = String.valueOf(decaShotPut.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " m):");
                            break;
                    }
                }
                else if (eventToCalculate.equals("Heptathlon")) {
                    switch (discipline)
                    {
                        case "100m Hurdles":
                            Hep100MHurdles hep100MHurdles = new Hep100MHurdles();
                            minValue = String.valueOf(hep100MHurdles.getMinBoundaryValue());
                            maxValue = String.valueOf(hep100MHurdles.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " s):");
                            break;
                        case "200m":
                            Hep200M hep200M = new Hep200M();
                            minValue = String.valueOf(hep200M.getMinBoundaryValue());
                            maxValue = String.valueOf(hep200M.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " s):");
                            break;
                        case "800m":
                            Hep800M hep800M = new Hep800M();
                            minValue = String.valueOf(hep800M.getMinBoundaryValue());
                            maxValue = String.valueOf(hep800M.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " s):");
                            break;
                        case "High Jump":
                            HeptHightJump heptHightJump = new HeptHightJump();
                            minValue = String.valueOf(heptHightJump.getMinBoundaryValue());
                            maxValue = String.valueOf(heptHightJump.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " cm):");
                            break;
                        case "Javelin Throw":
                            HeptJavelinThrow heptJavelinThrow = new HeptJavelinThrow();
                            minValue = String.valueOf(heptJavelinThrow.getMinBoundaryValue());
                            maxValue = String.valueOf(heptJavelinThrow.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " m):");
                            break;
                        case "Long Jump":
                            HeptLongJump heptLongJump = new HeptLongJump();
                            minValue = String.valueOf(heptLongJump.getMinBoundaryValue());
                            maxValue = String.valueOf(heptLongJump.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " cm):");
                            break;
                        case "Shot Put":
                            HeptShotPut heptShotPut = new HeptShotPut();
                            minValue = String.valueOf(heptShotPut.getMinBoundaryValue());
                            maxValue = String.valueOf(heptShotPut.getMaxBoundaryValue());
                            resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " m):");
                            break;
                    }
                }
            }
        });
    }

    ActionListener radioButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            switch (selectedButton.getText()) {
                case "Decathlon":
                    disciplines = new String[] {"100m", "400m", "1500m", "110m Hurdles", "Long Jump", "High Jump", "Pole Vault", "Discus Throw", "Javelin Throw", "Shot Put"};
                    disciplineBox.setModel(new DefaultComboBoxModel<>(disciplines));
                    eventToCalculate = "Decathlon";

                    Deca100M deca100M = new Deca100M();
                    minValue = String.valueOf(deca100M.getMinBoundaryValue());
                    maxValue = String.valueOf(deca100M.getMaxBoundaryValue());
                    resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " s):");
                    break;
                case "Heptathlon":
                    disciplines = new String[] {"100m Hurdles", "200m", "800m", "High Jump", "Javelin Throw", "Long Jump", "Shot Put"};
                    disciplineBox.setModel(new DefaultComboBoxModel<>(disciplines));
                    eventToCalculate = "Heptathlon";

                    Hep100MHurdles hep100MHurdles = new Hep100MHurdles();
                    minValue = String.valueOf(hep100MHurdles.getMinBoundaryValue());
                    maxValue = String.valueOf(hep100MHurdles.getMaxBoundaryValue());
                    resultLabel.setText("Enter Result (" + minValue + "-" + maxValue + " s):");
                    break;
            }
        }
    };

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String discipline = (String) disciplineBox.getSelectedItem();
            String resultText = resultField.getText();

            try {
                double result = Double.parseDouble(resultText);

                int score = 0;
                if(eventToCalculate.equals("Decathlon"))
                {
                    switch (discipline)
                    {
                        case "100m":
                            Deca100M deca100M = new Deca100M();
                            score = deca100M.calculateResult(result);
                            break;
                        case "400m":
                            Deca400M deca400M = new Deca400M();
                            score = deca400M.calculateResult(result);
                            break;
                        case "1500m":
                            Deca1500M deca1500M = new Deca1500M();
                            score = deca1500M.calculateResult(result);
                            break;
                        case "110m Hurdles":
                            Deca110MHurdles deca110MHurdles = new Deca110MHurdles();
                            score = deca110MHurdles.calculateResult(result);
                            break;
                        case "Long Jump":
                            DecaLongJump decaLongJump = new DecaLongJump();
                            score = decaLongJump.calculateResult(result);
                            break;
                        case "High Jump":
                            DecaHighJump decaHighJump = new DecaHighJump();
                            score = decaHighJump.calculateResult(result);
                            break;
                        case "Pole Vault":
                            DecaPoleVault decaPoleVault = new DecaPoleVault();
                            score = decaPoleVault.calculateResult(result);
                            break;
                        case "Discus Throw":
                            DecaDiscusThrow decaDiscusThrow = new DecaDiscusThrow();
                            score = decaDiscusThrow.calculateResult(result);
                            break;
                        case "Javelin Throw":
                            DecaJavelinThrow decaJavelinThrow = new DecaJavelinThrow();
                            score = decaJavelinThrow.calculateResult(result);
                            break;
                        case "Shot Put":
                            DecaShotPut decaShotPut = new DecaShotPut();
                            score = decaShotPut.calculateResult(result);
                            break;
                    }
                }
                else if (eventToCalculate.equals("Heptathlon")) {
                    switch (discipline)
                    {
                        case "100m Hurdles":
                            Hep100MHurdles hep100MHurdles = new Hep100MHurdles();
                            score = hep100MHurdles.calculateResult(result);
                            break;
                        case "200m":
                            Hep200M hep200M = new Hep200M();
                            score = hep200M.calculateResult(result);
                            break;
                        case "800m":
                            Hep800M hep800M = new Hep800M();
                            score = hep800M.calculateResult(result);
                            break;
                        case "High Jump":
                            HeptHightJump heptHightJump = new HeptHightJump();
                            score = heptHightJump.calculateResult(result);
                            break;
                        case "Javelin Throw":
                            HeptJavelinThrow heptJavelinThrow = new HeptJavelinThrow();
                            score = heptJavelinThrow.calculateResult(result);
                            break;
                        case "Long Jump":
                            HeptLongJump heptLongJump = new HeptLongJump();
                            score = heptLongJump.calculateResult(result);
                            break;
                        case "Shot Put":
                            HeptShotPut heptShotPut = new HeptShotPut();
                            score = heptShotPut.calculateResult(result);
                            break;
                    }
                }

                if (score == -1) {
                    JOptionPane.showMessageDialog(null, "Value too low", "Invalid entry", JOptionPane.ERROR_MESSAGE);
                }
                else if (score == -2) {
                    JOptionPane.showMessageDialog(null, "Value too high", "Invalid entry", JOptionPane.ERROR_MESSAGE);
                }
                else if(numberOfCompetitors >= 40) {
                    JOptionPane.showMessageDialog(null, "The number of competitors cannot exceed 40", "Too many competitors", JOptionPane.ERROR_MESSAGE);
                }
                else if(nameField.getText() == null || nameField.getText().isEmpty() || !nameField.getText().matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid name for competitor, letters only", "Invalid name", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Competitor competitor = findCompetitorByName(name);
                    if (competitor == null) {
                        competitor = new Competitor(name);  // Create a new competitor
                        competitors.add(competitor);        // Add to the list
                    }

                    // Update the competitor's score for the selected discipline
                    competitor.setScore(discipline, score);

                    // Print the number of competitors for debugging
                    System.out.println("Number of competitors: " + competitors.size());


                    outputArea.append("Competitor: " + name + "\n");
                    outputArea.append("Discipline: " + discipline + "\n");
                    outputArea.append("Result: " + result + "\n");
                    outputArea.append("Score: " + score + "\n\n");
                    numberOfCompetitors++;
                    System.out.println(numberOfCompetitors);
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the result.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception ex) {
                System.out.println("ERROR");
            }
        }
    }


    private class ExportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(competitors.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nothing to save, please add competitor results", "No competitors", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    exportToExcel();
                    JOptionPane.showMessageDialog(null, "Results exported successfully!", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to export results to Excel.", "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportToExcel() throws IOException {
        String[][] data = new String[competitors.size()][];
        int i = 0;
        for (Competitor competitor : competitors) {
            Object[] rowData = competitor.getRowData(); // Get the competitor's row data

            // Ensure the array size matches the number of columns in rowData
            data[i] = new String[rowData.length];

            // Safely copy rowData to data array
            for (int j = 0; j < rowData.length; j++) {
                data[i][j] = (rowData[j] != null) ? rowData[j].toString() : "";  // Handle null values
            }
            i++;
        }

        ExcelPrinter printer = new ExcelPrinter("TrackAndFieldResults");
        printer.add(data, "Results");
        printer.write();
    }

    // Method to find an existing competitor by name
    private Competitor findCompetitorByName(String name) {
        for (Competitor competitor : competitors) {
            if (competitor.getName().equalsIgnoreCase(name)) {
                return competitor;
            }
        }
        return null;  // If not found, return null
    }

}

