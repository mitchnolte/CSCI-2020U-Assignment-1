package game;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ButtonModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;


/**
 * JPanel for the main menu screen.
 */
public class Menu extends JPanel {

  private JButton easy;
  private JButton hard;


  /**
   * Default constructor to create the main menu.
   */
  public Menu() {
    setBackground(Color.DARK_GRAY);

    // Layout & component constraint values
    setLayout(new GridBagLayout());
    final int START_COL = 0;
    final int START_ROW = 0;

    // Title
    JLabel title = new JLabel("Not the World's Hardest Game");
    title.setFont(new Font(title.getFont().getName(), Font.BOLD, 50));
    title.setForeground(Color.WHITE);
    add(title, new GBConstraints(START_COL, START_ROW, 2, 1));

    // Easy mode description
    JLabel easyLabel = new JLabel("Simple enemy movement, no projectiles.");
    easyLabel.setForeground(Color.DARK_GRAY);
    add(easyLabel, new GBConstraints(START_COL, START_ROW+2));

    // Easy mode button
    easy = new JButton("Play Easy Mode");
    easy.addActionListener(this::actionPerformed);
    easy.setFont(new Font(easy.getFont().getName(), Font.PLAIN, 24));
    easy.setPreferredSize(new Dimension(Main.FRAME_WIDTH/4, Main.FRAME_HEIGHT/5));
    easy.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
          ButtonModel model = (ButtonModel) e.getSource();
          if (model.isRollover()) {
              easyLabel.setForeground(Color.WHITE);
          } else {
              easyLabel.setForeground(Color.DARK_GRAY);
          }
      }
      });

    add(easy, new GBConstraints(START_COL, START_ROW+1));

    // Hard mode description
    JLabel hardLabel = new JLabel("Complex enemy movement with projectiles.");
    hardLabel.setForeground(Color.DARK_GRAY);
    add(hardLabel, new GBConstraints(START_COL+1, START_ROW+2));

    // Hard mode button
    hard = new JButton("Play Hard Mode");
    hard.addActionListener(this::actionPerformed);
    hard.setFont(new Font(hard.getFont().getName(), Font.PLAIN, 24));
    hard.setPreferredSize(new Dimension(Main.FRAME_WIDTH/4, Main.FRAME_HEIGHT/5));
    hard.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
          ButtonModel model = (ButtonModel) e.getSource();
          if (model.isRollover()) {
              hardLabel.setForeground(Color.WHITE);
          } else {
              hardLabel.setForeground(Color.DARK_GRAY);
          }
      }
      });

    add(hard, new GBConstraints(START_COL+1, START_ROW+1));
  }


  /**
   * Action listener for buttons.
   * @param e action performed.
   */
  public void actionPerformed(ActionEvent e) {

    if(e.getSource() == easy) {
      Main.showPanel(Main.Panels.EASY_GAMESCREEN);
    }
    else if(e.getSource() == hard) {
      Main.showPanel(Main.Panels.HARD_GAMESCREEN);
    }
  }
}
