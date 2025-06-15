import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

public class ResultManagementSystem extends JFrame {
    private JTextField nameField, rollField, subject1Field, subject2Field, subject3Field;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private final String FILE_NAME = "results.txt";

    public ResultManagementSystem() {
        setTitle("Result Management System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        Color primaryColor = new Color(60, 120, 180);
        Color bgColor = new Color(240, 248, 255);

        getContentPane().setBackground(bgColor);

        JLabel header = new JLabel("Result Management System");
        header.setFont(new Font("Arial", Font.BOLD, 26));
        header.setBounds(280, 20, 400, 30);
        header.setForeground(primaryColor);
        add(header);

        JLabel nameLabel = new JLabel("Student Name:");
        JLabel rollLabel = new JLabel("Roll Number:");
        JLabel subject1Label = new JLabel("Subject 1 Marks:");
        JLabel subject2Label = new JLabel("Subject 2 Marks:");
        JLabel subject3Label = new JLabel("Subject 3 Marks:");

        nameLabel.setBounds(50, 80, 150, 25);
        rollLabel.setBounds(50, 120, 150, 25);
        subject1Label.setBounds(50, 160, 150, 25);
        subject2Label.setBounds(50, 200, 150, 25);
        subject3Label.setBounds(50, 240, 150, 25);

        nameField = new JTextField();
        rollField = new JTextField();
        subject1Field = new JTextField();
        subject2Field = new JTextField();
        subject3Field = new JTextField();

        nameField.setBounds(200, 80, 200, 25);
        rollField.setBounds(200, 120, 200, 25);
        subject1Field.setBounds(200, 160, 200, 25);
        subject2Field.setBounds(200, 200, 200, 25);
        subject3Field.setBounds(200, 240, 200, 25);

        add(nameLabel); add(nameField);
        add(rollLabel); add(rollField);
        add(subject1Label); add(subject1Field);
        add(subject2Label); add(subject2Field);
        add(subject3Label); add(subject3Field);

        JButton addResultBtn = new JButton("Add Result");
        addResultBtn.setBounds(200, 290, 200, 30);
        addResultBtn.setBackground(primaryColor);
        addResultBtn.setForeground(Color.WHITE);
        add(addResultBtn);

        String[] columns = {"Name", "Roll No", "Sub1", "Sub2", "Sub3", "Average", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBounds(420, 80, 440, 300);
        add(scrollPane);

        JLabel footer = new JLabel("Made by Azan Mehdi");
        footer.setBounds(350, 520, 300, 20);
        footer.setFont(new Font("SansSerif", Font.ITALIC, 14));
        footer.setForeground(Color.GRAY);
        add(footer);

        // Load data on startup
        loadFromFile();

        // Button logic
        addResultBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            try {
                int s1 = Integer.parseInt(subject1Field.getText().trim());
                int s2 = Integer.parseInt(subject2Field.getText().trim());
                int s3 = Integer.parseInt(subject3Field.getText().trim());

                double average = (s1 + s2 + s3) / 3.0;
                String status = (s1 >= 33 && s2 >= 33 && s3 >= 33) ? "Pass" : "Fail";

                Object[] row = {name, roll, s1, s2, s3, String.format("%.2f", average), status};
                tableModel.addRow(row);

                saveToFile(name, roll, s1, s2, s3, average, status);
                clearFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric marks (0-100)");
            }
        });
    }

    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        subject1Field.setText("");
        subject2Field.setText("");
        subject3Field.setText("");
    }

    // Save single entry to file
    private void saveToFile(String name, String roll, int s1, int s2, int s3, double avg, String status) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(name + "," + roll + "," + s1 + "," + s2 + "," + s3 + "," + String.format("%.2f", avg) + "," + status + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file!");
        }
    }

    // Load all entries from file
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String[] row = new String[7];
                int i = 0;
                while (st.hasMoreTokens()) {
                    row[i++] = st.nextToken();
                }
                tableModel.addRow(row);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResultManagementSystem().setVisible(true));
    }
}
