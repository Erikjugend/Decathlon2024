package gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.awt.*;

import decathlon.*;
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

        JPanel panel = new JPanel(new GridLayout(6, 1));

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
        resultLabel = new JLabel("Enter Result:");
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

        frame.add(panel);
        frame.setVisible(true);

        disciplineBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(disciplineBox.getSelectedItem() == "1500m")
                    resultLabel.setText("Enter Result: (Minutes e.g. 4.1)");
                else {
                    if (!resultLabel.getText().equals("Enter Result:")) resultLabel.setText("Enter Result:");
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
                    break;
                case "Heptathlon":
                    disciplines = new String[] {"100m Hurdles", "200m", "800m", "High Jump", "Javelin Throw", "Long Jump", "Shot Put"};
                    disciplineBox.setModel(new DefaultComboBoxModel<>(disciplines));
                    eventToCalculate = "Heptathlon";
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

                outputArea.append("Competitor: " + name + "\n");
                outputArea.append("Discipline: " + discipline + "\n");
                outputArea.append("Result: " + result + "\n");
                outputArea.append("Score: " + score + "\n\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the result.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception ex)
            {
                System.out.println("ERROR");
            }
        }
    }
}
