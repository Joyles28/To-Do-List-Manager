import javax.swing.*;
import java.awt.*;

public class CustomComponent {
    public static void customize(Container container) {
        container.setBackground(Color.decode("#F2F5EA"));

        for (Component component : container.getComponents()) {

            component.setBackground(Color.decode("#F2F5EA"));
            component.setForeground(Color.decode("#2C363F"));

            if (component instanceof JButton) {
                component.setBackground(Color.decode("#F2F5EA"));
                ((JButton) component).setFocusPainted(false);
            } else if (component instanceof JTextArea) {
                ((JTextArea) component).setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
            } else if (component instanceof JPanel) {
                ((JPanel) component).setBorder(BorderFactory.createLineBorder(Color.black));
            }

            if (component instanceof Container) {
                customize((Container) component);
            }
        }
    }
    public static void lowCustomize(Container container) {
        container.setBackground(Color.decode("#F2F5EA"));

        for (Component component : container.getComponents()) {

            if (!(component instanceof JButton)) {
                component.setBackground(Color.decode("#F2F5EA"));
                component.setForeground(Color.decode("#2C363F"));

                if (component instanceof JTextArea) {
                    ((JTextArea) component).setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
                } else if (component instanceof JPanel) {
                    ((JPanel) component).setBorder(BorderFactory.createLineBorder(Color.black));
                }
            }

        }
    }
}
