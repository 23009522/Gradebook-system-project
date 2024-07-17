import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ComputerLaboratoryFrame extends JFrame {
    // Define components
    private JPanel studentPanel;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField studentNameField;
    private JTextField studentNumberField;
    private JTextField test1Field;
    private JTextField test2Field;
    private JTextField assignmentField;
    private JTextField presentationField;
    private JTextField semesterMarkField;
    private JTextField examMarkField;
    private JTextField finalMarkField;
    private JButton addStudentButton;
    private JButton calculateSemesterButton;
    private JButton calculateFinalButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton logoutButton;
    private JButton downloadButton;
    private JButton clearButton;

    // Number format respecting the locale
    private NumberFormat numberFormat;

    public ComputerLaboratoryFrame() {
        setTitle("Computer Laboratory");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JLabel titleLabel = new JLabel("Welcome to Computer Laboratory");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String[] columnNames = {"Student Name", "Student Number", "Test 1", "Test 2", "Assignment", "Presentation", "Semester Mark", "Exam Mark", "Final Mark"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);

        studentPanel = new JPanel(new BorderLayout());
        studentPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel marksPanel = new JPanel(new GridLayout(15, 2));

        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameField = new JTextField();

        JLabel studentNumberLabel = new JLabel("Student Number:");
        studentNumberField = new JTextField();

        JLabel test1Label = new JLabel("Enter Test 1 Mark:");
        test1Field = new JTextField();

        JLabel test2Label = new JLabel("Enter Test 2 Mark:");
        test2Field = new JTextField();

        JLabel assignmentLabel = new JLabel("Enter Assignment Mark:");
        assignmentField = new JTextField();

        JLabel presentationLabel = new JLabel("Enter Presentation Mark:");
        presentationField = new JTextField();

        JLabel semesterLabel = new JLabel("Semester Mark:");
        semesterMarkField = new JTextField();
        semesterMarkField.setEditable(false);

        JLabel examLabel = new JLabel("Enter Exam Mark:");
        examMarkField = new JTextField();

        JLabel finalLabel = new JLabel("Final Mark:");
        finalMarkField = new JTextField();
        finalMarkField.setEditable(false);

        addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> addStudent());

        calculateSemesterButton = new JButton("Calculate Semester Mark");
        calculateSemesterButton.addActionListener(e -> calculateSemesterMark());

        calculateFinalButton = new JButton("Calculate Final Mark");
        calculateFinalButton.addActionListener(e -> calculateFinalMark());

        editButton = new JButton("Update");
        editButton.setBackground(Color.PINK);
        editButton.setForeground(Color.BLACK);
        editButton.setFont(new Font("Arial", Font.BOLD, 12));
        editButton.addActionListener(e -> editStudent());

        deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.addActionListener(e -> deleteStudent());

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.BLUE);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.addActionListener(e -> logout());

        downloadButton = new JButton("Download Report");
        downloadButton.setBackground(Color.GREEN);
        downloadButton.setForeground(Color.WHITE);
        downloadButton.setFont(new Font("Arial", Font.BOLD, 12));
        downloadButton.addActionListener(e -> downloadReport());

        clearButton = new JButton("Clear");
        clearButton.setBackground(Color.CYAN);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearButton.addActionListener(e -> clearInputFields());

        marksPanel.add(studentNameLabel);
        marksPanel.add(studentNameField);
        marksPanel.add(studentNumberLabel);
        marksPanel.add(studentNumberField);
        marksPanel.add(addStudentButton);
        marksPanel.add(new JLabel());
        marksPanel.add(test1Label);
        marksPanel.add(test1Field);
        marksPanel.add(test2Label);
        marksPanel.add(test2Field);
        marksPanel.add(assignmentLabel);
        marksPanel.add(assignmentField);
        marksPanel.add(presentationLabel);
        marksPanel.add(presentationField);
        marksPanel.add(calculateSemesterButton);
        marksPanel.add(semesterMarkField);
        marksPanel.add(examLabel);
        marksPanel.add(examMarkField);
        marksPanel.add(calculateFinalButton);
        marksPanel.add(finalMarkField);
        marksPanel.add(editButton);
        marksPanel.add(deleteButton);
        marksPanel.add(logoutButton);
        marksPanel.add(downloadButton);
        marksPanel.add(clearButton);

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(studentPanel, BorderLayout.CENTER);
        add(marksPanel, BorderLayout.EAST);

        pack();

        loadStudentData();

        // Initialize NumberFormat with default locale
        numberFormat = NumberFormat.getInstance();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveStudentData();
            }
        });
    }

    private void addStudent() {
        String studentName = studentNameField.getText();
        String studentNumber = studentNumberField.getText();

        if (studentName.isEmpty() || studentNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both student name and student number.");
        } else {
            tableModel.addRow(new Object[]{studentName, studentNumber, "", "", "", "", "", "", ""});
            clearInputFields();
        }
    }

    private void calculateSemesterMark() {
        try {
            double test1 = numberFormat.parse(test1Field.getText()).doubleValue();
            double test2 = numberFormat.parse(test2Field.getText()).doubleValue();
            double assignment = numberFormat.parse(assignmentField.getText()).doubleValue();
            double presentation = numberFormat.parse(presentationField.getText()).doubleValue();

            double semesterMark = (test1 + test2 + assignment + presentation) / 4;
            semesterMarkField.setText(String.format("%.2f", semesterMark));
        } catch (ParseException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric marks.");
            e.printStackTrace();
        }
    }

    private void calculateFinalMark() {
        try {
            double semesterMark = numberFormat.parse(semesterMarkField.getText()).doubleValue();
            double examMark = numberFormat.parse(examMarkField.getText()).doubleValue();

            double finalMark = 0.6 * semesterMark + 0.4 * examMark;
            finalMarkField.setText(String.format("%.2f", finalMark));
        } catch (ParseException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric marks.");
            e.printStackTrace();
        }
    }

    private void clearInputFields() {
        studentNameField.setText("");
        studentNumberField.setText("");
        test1Field.setText("");
        test2Field.setText("");
        assignmentField.setText("");
        presentationField.setText("");
        semesterMarkField.setText("");
        examMarkField.setText("");
        finalMarkField.setText("");
    }

    private void editStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to edit.");
            return;
        }

        String test1 = test1Field.getText();
        String test2 = test2Field.getText();
        String assignment = assignmentField.getText();
        String presentation = presentationField.getText();
        String semesterMark = semesterMarkField.getText();
        String examMark = examMarkField.getText();
        String finalMark = finalMarkField.getText();

        tableModel.setValueAt(test1, selectedRow, 2);
        tableModel.setValueAt(test2, selectedRow, 3);
        tableModel.setValueAt(assignment, selectedRow, 4);
        tableModel.setValueAt(presentation, selectedRow, 5);
        tableModel.setValueAt(semesterMark, selectedRow, 6);
        tableModel.setValueAt(examMark, selectedRow, 7);
        tableModel.setValueAt(finalMark, selectedRow, 8);

        clearInputFields();
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }
        tableModel.removeRow(selectedRow);
        clearInputFields();
    }

    private void logout() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
    // Method to download report
    private void downloadReport() {
        try (FileWriter writer = new FileWriter("StudentReport.csv")) {
            // Write header
            writer.write("Student Name,Student Number,Test 1,Test 2,Assignment,Presentation,Semester Mark,Exam Mark,Final Mark\n");

            // Write student data
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write(tableModel.getValueAt(i, j).toString() + ",");
                }
                writer.write("\n");
            }

            JOptionPane.showMessageDialog(this, "Report downloaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error downloading report: " + e.getMessage());
        }
    }

    // Method to load student data
    private void loadStudentData() {
        File file = new File("StudentData.csv");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    tableModel.addRow(data);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading student data: " + e.getMessage());
            }
        }
    }

    // Method to save student data
    private void saveStudentData() {
        try (FileWriter writer = new FileWriter("StudentData.csv")) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write(tableModel.getValueAt(i, j).toString() + ",");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving student data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create and display the frame
        SwingUtilities.invokeLater(() -> {
            ComputerLaboratoryFrame frame = new ComputerLaboratoryFrame();
            frame.setVisible(true);
        });
    }
}
