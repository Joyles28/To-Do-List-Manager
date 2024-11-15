import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CenterPanel centerPanel;
    private LowerPanel lowerPanel;
    public MainFrame() {
        super("Tasks Tracker");
        setLayout(new BorderLayout());

        centerPanel = new CenterPanel();
        lowerPanel = new LowerPanel(centerPanel);
        JScrollPane scrollPane = new JScrollPane(centerPanel);

        centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        add(scrollPane, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);

        setSize(400,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

//        CustomComponent.customize(this);

        Runtime.getRuntime().addShutdownHook(new Thread(centerPanel::savePreferences));
    }
}