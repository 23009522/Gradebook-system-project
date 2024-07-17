import javax.swing.*;
import java.awt.*;

public class AlgorithmsDataStructuresFrame extends JFrame {
    public AlgorithmsDataStructuresFrame() {
        setTitle("Algorithms and Data Structures");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose frame when closed
        setLocationRelativeTo(null);
        
        JLabel titleLabel = new JLabel("Welcome to Algorithms and Data Structures");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(titleLabel, BorderLayout.CENTER);
    }
}
