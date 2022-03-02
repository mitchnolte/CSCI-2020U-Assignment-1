package game;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Dimension;
import entities.factories.*;


/**
 * Main class to setup the game. Implements the {@link Runnable} interface.
 */
public class Main implements Runnable {

  // FRAMESIZE should have a 5:3 aspect ratio
  public static final int FRAME_WIDTH = 1600;
  public static final int FRAME_HEIGHT = 960;
  public static final Dimension FRAMESIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

  private static JFrame frame;
  private static JPanel parentPanel;
  private static CardLayout layout;
  private static Menu menu;
  private static GameScreen gameScreen;
  private static WinScreen winScreen;


  /**
   * Main function, starts the program.
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Main());
  }

  /**
   * Implementation of the {@link Runnable} interface method {@code run()} that runs the program.
   * {@inheritDoc}
   */
  @Override
  public void run() {
    frame = new JFrame();
    layout = new CardLayout();
    parentPanel = new JPanel(layout);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(FRAMESIZE);
    frame.getContentPane().setPreferredSize(FRAMESIZE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

    frame.add(parentPanel);
    showPanel(Panels.MENU);
    frame.setVisible(true);
  }


  /**
   * Available {@link JPanel panels} to be displayed.
   */
  public static enum Panels {
    MENU, EASY_GAMESCREEN, HARD_GAMESCREEN, WINSCREEN
  }

  /**
   * Switches the {@link JPanel panel} to the desired screen.
   * @param screen the screen to be displayed.
   */
  public static void showPanel(Panels screen) {
    JPanel panel = null;

    if(screen == Panels.MENU) {
      if(menu == null) {
        menu = new Menu();
        parentPanel.add(menu, screen.name());
      }
      panel = menu;
    }
    else if(screen == Panels.EASY_GAMESCREEN) {
      if(gameScreen == null || gameScreen.ENTITY_FACTORY instanceof HardEntityFactory) {
        gameScreen = new GameScreen(new EasyEntityFactory());
        parentPanel.add(gameScreen, screen.name());
      }
      panel = gameScreen;
    }
    else if(screen == Panels.HARD_GAMESCREEN) {
      if(gameScreen == null || gameScreen.ENTITY_FACTORY instanceof EasyEntityFactory) {
        gameScreen = new GameScreen(new HardEntityFactory());
        parentPanel.add(gameScreen, screen.name());
      }
      panel = gameScreen;
    }
    else if(screen == Panels.WINSCREEN) {
      if(winScreen == null) {
        winScreen = new WinScreen();
        parentPanel.add(winScreen, screen.name());
      }
      panel = winScreen;
    }
    
    layout.show(parentPanel, screen.name());
    panel.grabFocus();
    frame.pack();
  }
}
