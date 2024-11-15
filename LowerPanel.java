import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LowerPanel extends JPanel implements ActionListener {
    private JButton addTask, removeTask, filterTask;
    private CenterPanel centerPanel;

    public LowerPanel(CenterPanel centerPanel) {
        setName("LowerPanel");
        this.centerPanel = centerPanel;

        setLayout(new FlowLayout());

        addTask = new JButton("Add Task");
        removeTask = new JButton("Remove Task");
        filterTask = new JButton("Filter Task");

         addTask.setFocusPainted(false);
        removeTask.setFocusPainted(false);
        filterTask.setFocusPainted(false);

        addTask.addActionListener(this);
        removeTask.addActionListener(this);
        filterTask.addActionListener(this);

        add(addTask);
        add(removeTask);
        add(filterTask);

        CustomComponent.customize(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add Task":
                centerPanel.addPanel();
                break;
            case "Remove Task":
                centerPanel.removePanel();
                break;
            case "Filter Task":

                revalidate();
                repaint();

                break;
        }
    }
}