package game;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import entities.*;
import entities.factories.*;


/**
 * JPanel for the screen the game is played on.
 */
public class GameScreen extends JPanel {

  private Entity[] entities;
  private Player player;
  private Timer timer;
  private boolean hardMode;

  
  /**
   * Constructor to create the game screen.
   * @param entityFactory object to create the entities required for the game.
   */
  public GameScreen(EntityFactory entityFactory) {
    setBackground(Color.WHITE);
    if(entityFactory instanceof HardEntityFactory) {hardMode = true;}
    else {hardMode = false;}

    // Create entities
    entities = entityFactory.getEntities();
    player = (Player)entities[0];

    // Initialize keyboard input and timer
    addKeyListener(new KeyInput(this, player));

    timer = new Timer(10, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        // Update each entity and check for collision
        for(Entity entity: entities) {
          entity.update();
          if(player.collidesWith(entity)) {
            if(entity instanceof WinTile) {
              winGame();
              break;
            }
            else {
              resetGame();
              break;
            }
          }
        }

        // Refresh screen
        repaint();
      }
    });

    timer.start();
  }


  /**
   * @return Whether game is in hard mode or not.
   */
  public boolean isHardMode() {return hardMode;}


  /**
   * Draws the game. JPanel implementation: {@inheritDoc}
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Draw background
    int tileSize = EntityFactory.TILE_SIZE;
    g.setColor(Color.LIGHT_GRAY);
    for(int i=0; i<Main.FRAME_HEIGHT/tileSize; i++) {
      for(int j=0+(i%2); j<Main.FRAME_WIDTH/tileSize; j+=2) {
        g.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
      }
    }

    // Draw entities
    for(Entity entity: entities) {
      entity.draw(g);
    }
  }


  /**
   * Resets the game to its initial state.
   */
  public void resetGame() {
    for(Entity entity: entities) {
      entity.reset();
    }
  }


  /**
   * Ends the game and displays the win screen.
   */
  public void winGame() {
    resetGame();
    Main.showPanel(Main.Panels.WINSCREEN);
  }


  /**
   * Pauses or unpauses the game.
   */
  public void togglePause() {
    if(timer.isRunning()) {timer.stop();}
    else {timer.start();}
  }
}
