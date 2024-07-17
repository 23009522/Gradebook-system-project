import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuleSelectionFrame extends JFrame {
    private JCheckBox algorithmsDataStructuresCheckBox;
    private JCheckBox computerLaboratoryCheckBox;

    public ModuleSelectionFrame() {
        setTitle("Module Selection");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = createPanel();
        add(panel, BorderLayout.CENTER);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        algorithmsDataStructuresCheckBox = new JCheckBox("C0M2224 Algorithms and Data Structures");
        computerLaboratoryCheckBox = new JCheckBox("COM2301 Computer Laboratory");

        panel.add(algorithmsDataStructuresCheckBox);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(computerLaboratoryCheckBox);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton continueButton = new JButton("Continue");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.addActionListener(new ContinueButtonActionListener());
        panel.add(continueButton);

        return panel;
    }

    private class ContinueButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleContinueButtonClick();
        }
    }

    private void handleContinueButtonClick() {
        if (algorithmsDataStructuresCheckBox.isSelected()) {
            openAlgorithmsDataStructuresFrame();
        } else if (computerLaboratoryCheckBox.isSelected()) {
            openComputerLaboratoryFrame();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a module.");
        }
    }

    private void openAlgorithmsDataStructuresFrame() {
        SwingUtilities.invokeLater(() -> new AlgorithmsDataStructuresFrame().setVisible(true));
    }

    private void openComputerLaboratoryFrame() {
        SwingUtilities.invokeLater(() -> new ComputerLaboratoryFrame().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModuleSelectionFrame().setVisible(true));
    }
}
