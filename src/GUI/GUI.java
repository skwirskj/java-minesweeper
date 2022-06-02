/**
 * Implementation of the Minesweeper GUI.
 */
package GUI;

import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

class GUI {
    public GUI() {
        // Create the frame and panel for the GUI.
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel mainMenu = new JLabel("Select difficulty below:");
        JButton easyButton = new JButton("Easy");
        JButton medButton = new JButton("Medium");
        JButton expertButton = new JButton("Expert");

        panel.setBorder(BorderFactory.createEmptyBorder(250, 250, 250, 250));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(mainMenu, BorderLayout.CENTER);
        panel.add(easyButton, BorderLayout.CENTER);
        panel.add(medButton, BorderLayout.CENTER);
        panel.add(expertButton, BorderLayout.CENTER);

        // Add the panel to the GUI.
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Minesweeper");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }

}