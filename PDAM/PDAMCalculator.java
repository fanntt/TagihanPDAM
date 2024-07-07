package PDAM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDAMCalculator extends JFrame {
    private JTextField nameField, addressField, cityField, usageField;
    private JComboBox<String> golonganComboBox;
    private JTextArea resultArea;

    public PDAMCalculator() {
        // Set up the frame
        setTitle("PDAM Billing Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create and add components
        JLabel nameLabel = new JLabel("Nama:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 20, 200, 25);
        add(nameField);

        JLabel addressLabel = new JLabel("Alamat:");
        addressLabel.setBounds(20, 60, 80, 25);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(150, 60, 200, 25);
        add(addressField);

        JLabel cityLabel = new JLabel("Kota:");
        cityLabel.setBounds(20, 100, 80, 25);
        add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(150, 100, 200, 25);
        add(cityField);

        JLabel golonganLabel = new JLabel("Golongan:");
        golonganLabel.setBounds(20, 140, 80, 25);
        add(golonganLabel);

        String[] golonganOptions = {"A", "B", "C"};
        golonganComboBox = new JComboBox<>(golonganOptions);
        golonganComboBox.setBounds(150, 140, 200, 25);
        add(golonganComboBox);

        JLabel usageLabel = new JLabel("Pemakaian (m³):");
        usageLabel.setBounds(20, 180, 100, 25);
        add(usageLabel);

        usageField = new JTextField();
        usageField.setBounds(150, 180, 200, 25);
        add(usageField);

        JButton calculateButton = new JButton("Hitung");
        calculateButton.setBounds(150, 220, 100, 25);
        add(calculateButton);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 260, 340, 180);
        resultArea.setEditable(false);
        add(resultArea);

        // Add action listener to the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBill();
            }
        });

        setVisible(true);
    }

    private void calculateBill() {
        try {
            String name = nameField.getText();
            String address = addressField.getText();
            String city = cityField.getText();
            String golongan = (String) golonganComboBox.getSelectedItem();
            int usage = Integer.parseInt(usageField.getText());

            double beban = 0;
            switch (golongan) {
                case "A":
                    beban = 50000;
                    break;
                case "B":
                    beban = 70000;
                    break;
                case "C":
                    beban = 90000;
                    break;
            }

            double pemakaian = 0;
            if (usage <= 20) {
                pemakaian = usage * 2500;
            } else {
                pemakaian = (20 * 2500) + ((usage - 20) * 4000);
            }

            double totalBiaya = beban + pemakaian;
            double pajak = totalBiaya * 0.02;
            double totalPembayaran = totalBiaya + pajak;

            String result = String.format(
                "Nama: %s\nAlamat: %s\nKota: %s\nGolongan: %s\nPemakaian: %d m³\n\n" +
                "Biaya Beban: Rp%,.2f\nBiaya Pemakaian: Rp%,.2f\nPajak: Rp%,.2f\n\n" +
                "Total Pembayaran: Rp%,.2f",
                name, address, city, golongan, usage, beban, pemakaian, pajak, totalPembayaran
            );

            resultArea.setText(result);
        } catch (NumberFormatException e) {
            resultArea.setText("Error: Pastikan semua input valid dan jumlah pemakaian adalah angka.");
        }
    }

    public static void main(String[] args) {
        new PDAMCalculator();
    }
}