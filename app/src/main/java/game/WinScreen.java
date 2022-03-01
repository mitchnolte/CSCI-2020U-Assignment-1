package game;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;


/**
 * {@link JPanel} for the screen displayed when the player wins.
 */
public class WinScreen extends JPanel {
  
  private JButton menu;
  private JButton quit;


  /**
   * Constructor to create the win screen.
   */
  public WinScreen() {
    setBackground(Color.DARK_GRAY);
    setLayout(new GridBagLayout());

    // Win message
    JLabel message = new JLabel("You won!");
    message.setFont(new Font(message.getFont().getName(), Font.BOLD, 50));
    message.setForeground(Color.WHITE);
    add(message, new GBConstraints(0, 0, 2, 1));

    // Menu button
    menu = new JButton("Return to Menu");
    menu.addActionListener(this::actionPerformed);
    menu.setFont(new Font(menu.getFont().getName(), Font.PLAIN, 24));
    menu.setPreferredSize(new Dimension(Main.FRAME_WIDTH/4, Main.FRAME_HEIGHT/5));
    add(menu, new GBConstraints(0, 1));

    // Quit button
    quit = new JButton("Quit Game");
    quit.addActionListener(this::actionPerformed);
    quit.setFont(new Font(quit.getFont().getName(), Font.PLAIN, 24));
    quit.setPreferredSize(new Dimension(Main.FRAME_WIDTH/4, Main.FRAME_HEIGHT/5));
    add(quit, new GBConstraints(1, 1));
  }


  /**
   * Action listener for buttons.
   * @param e action performed.
   */
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == menu) {
      Main.showPanel(Main.Panels.MENU);
    }
    else if(e.getSource() == quit) {
      System.exit(0);
    }
  }
}
