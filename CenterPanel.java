import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class CenterPanel extends JPanel {
    private JPanel selectedPanel;
    private JPanel mainPanel;
    private JTextField session;
    private JTextField topic;
    private JTextField term;
    private ArrayList<JButton> jButtons = new ArrayList<>();

    public CenterPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // mainPanel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;

        session = new JTextField("Hello,");
        topic = new JTextField(20);
        term = new JTextField(String.valueOf(LocalDate.now()));

        session.setEditable(false);
        term.setEditable(false);

        topic.setBackground(null);
        term.setBackground(null);

        session.setBorder(BorderFactory.createEmptyBorder());
        topic.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
        term.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        session.setHorizontalAlignment(JTextField.CENTER);
        topic.setHorizontalAlignment(JTextField.CENTER);
        term.setHorizontalAlignment(JTextField.CENTER);

        session.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        topic.setFont(new Font("Times New Roman", Font.BOLD, 20));
        term.setFont(new Font("Times New Roman", Font.ITALIC, 12));

        gc.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(session, gc);

        gc.insets = new Insets(15, 0, 15, 0);
        gc.gridy++;
        mainPanel.add(topic, gc);

        gc.insets = new Insets(0, 0, 10, 0);
        gc.gridy++;
        mainPanel.add(term, gc);
        //

        add(mainPanel);

        CustomComponent.customize(this);

        loadPreferences();
    }

    public void savePreferences() {
        Preferences prefs = Preferences.userNodeForPackage(CenterPanel.class);

        // Save text of session, topic, and term
        prefs.put("topicText", topic.getText());

        // Save information about added panels
        int panelCount = getComponentCount();
        prefs.putInt("panelCount", panelCount);

        int validPanelCount = 0;  // Track the count of panels with text
        for (int i = 0; i < panelCount; i++) {
            Component comp = getComponent(i);
            if (comp instanceof JPanel && comp != mainPanel) {
                JPanel panel = (JPanel) comp;
                JTextArea textArea = (JTextArea) panel.getComponent(0);
                JButton doneBtn = (JButton) panel.getComponent(1);

                // Check if the text field has text before saving
                if (!textArea.getText().isEmpty()) {
                    prefs.put("panelText_" + validPanelCount, textArea.getText());
                    prefs.putBoolean("panelColor_" + validPanelCount, doneBtn.getBackground().equals(Color.decode("#BBC7A4")));
                    prefs.putBoolean("panelVisible_" + validPanelCount, panel.isVisible());
                    validPanelCount++;
                }
            }
        }
        prefs.putInt("validPanelCount", validPanelCount);  // Save the count of valid panels
    }

    private void loadPreferences() {
        Preferences prefs = Preferences.userNodeForPackage(CenterPanel.class);

        // Load text of session, topic, and term
        topic.setText(prefs.get("topicText", ""));

        // Load information about added panels
        int savedPanelCount = prefs.getInt("panelCount", 0);
        int validPanelCount = prefs.getInt("validPanelCount", 0);

        for (int i = 0; i < validPanelCount; i++) {
            String panelText = prefs.get("panelText_" + i, "");
            boolean isGreen = prefs.getBoolean("panelColor_" + i, false);
            boolean isVisible = prefs.getBoolean("panelVisible_" + i, true);

            // Create and configure the panel only if it has text
            if (!panelText.isEmpty()) {
                JPanel panel = new JPanel();
                JTextArea textArea = new JTextArea(2,28);
                textArea.setLineWrap(true);
                JButton doneBtn = new JButton("Done");
                doneBtn.setBackground(isGreen ? Color.decode("#BBC7A4") : Color.decode("#E75A7C"));
                doneBtn.setFocusPainted(false);

                doneBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (doneBtn.getBackground().equals(Color.decode("#E75A7C"))) {
                            doneBtn.setBackground(Color.decode("#BBC7A4"));
                        } else if (doneBtn.getBackground().equals(Color.decode("#BBC7A4"))) {
                            doneBtn.setBackground(Color.decode("#E75A7C"));
                        }
                    }
                });

                textArea.setText(panelText);
                doneBtn.setBackground(isGreen ? Color.decode("#BBC7A4") : Color.decode("#E75A7C"));
                panel.add(textArea);
                panel.add(doneBtn);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        panelClicked(panel);
                    }
                });

                panel.setVisible(isVisible);

                add(panel);

                CustomComponent.lowCustomize(panel);
            }
        }
    }

    public void addPanel() {
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea(2,28);
        textArea.setBackground(Color.decode("#F2F5EA"));
        textArea.setForeground(Color.decode("#2C363F"));
        textArea.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
        textArea.setLineWrap(true);

        JButton doneBtn = new JButton("Done");
        doneBtn.setFocusPainted(false);
        doneBtn.setBackground(Color.decode("#E75A7C"));
        doneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (doneBtn.getBackground().equals(Color.decode("#E75A7C"))) {
                    doneBtn.setBackground(Color.decode("#BBC7A4"));
                } else if (doneBtn.getBackground().equals(Color.decode("#BBC7A4"))) {
                    doneBtn.setBackground(Color.decode("#E75A7C"));
                }
            }
        });

        panel.add(textArea);
        panel.add(doneBtn);

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelClicked(panel);
            }
        });

        add(panel);
        revalidate();
        repaint();
    }

    public void removePanel() {
        if (selectedPanel != null) {
            remove(selectedPanel);
        }
        revalidate();
        repaint();
    }

    private void panelClicked(JPanel panel) {

        if (selectedPanel != null) {
            if (panel == selectedPanel) {
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                selectedPanel = null;
                return;
            }
            selectedPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        }

        panel.setBorder(BorderFactory.createLineBorder(Color.decode("#E75A7C"),3));
        selectedPanel = panel;
    }

    private void getAllBtns(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                getAllBtns((Container) component);
            }

            if (component instanceof JButton) {
                jButtons.add((JButton) component);
            }
        }
    }
}