package game;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import entities.*;
import entities.players.*;
import entities.enemies.*;
import entities.factories.*;


/**
 * {@link JPanel} for the screen the game is played on. Contians the game loop and uses an
 * {@link EntityFactory} to create the {@link entities.Entity entities}.
 */
public class GameScreen extends JPanel {

          // Game constants

    /**
   * Size of the tiles that make up the game board.
   */
  private static final int TILE_SIZE = Main.FRAME_WIDTH / 25;

  /**
   * Width of the win tile.
   */
  private static final int WIN_TILE_WIDTH = 3 * TILE_SIZE;

  /**
   * Height of the win tile.
   */
  private static final int WIN_TILE_HEIGHT = 2 * TILE_SIZE;



                  // Player information

  /**
   * Width of the player. 
   */
  private static final int PLAYER_WIDTH = Main.FRAME_WIDTH / 32;

  /**
   * Height of the player. Same as width, player is a square.
   */
  private static final int PLAYER_HEIGHT = PLAYER_WIDTH;

  /**
   * Initial x and y coordinate to start the player in the middle of the top left tile.
   */
  private static final int PLAYER_START_POINT = (TILE_SIZE - PLAYER_WIDTH) / 2;

  /**
   * Movement speed of the player.
   */
  private static final int PLAYER_MOVE_SPEED = Main.FRAME_WIDTH / 400;



                  // Enemy information

  /**
   * Number of enemies to add to the game.
   */
  private static final int NUM_ENEMIES = 20;

  /**
   * Number of columns to organize the enemies into.
   */
  private static final int NUM_ENEMY_COLS = 5;

  /**
   * Number of rows to organize the enemies into.
   */
  private static final int NUM_ENEMY_ROWS = NUM_ENEMIES / NUM_ENEMY_COLS;

  /**
   * Diamter of enemy.
   */
  private static final int ENEMY_DIAMETER = Main.FRAME_WIDTH / 40;
  
  /**
   * Movement speed of enemies.
   */
  private static final int ENEMY_MOVE_SPEED = Main.FRAME_HEIGHT / 320;




  // Member variables
  public final EntityFactory ENTITY_FACTORY;
  private Enemy[] enemies;
  private Player player;
  private WinTile winTile;
  private Timer timer;

  
  /**
   * Constructor to create the game screen.
   * @param entityFactory factory to create the entities required for the game.
   */
  public GameScreen(EntityFactory entityFactory) {
    setBackground(Color.WHITE);
    
    // Create entities
    ENTITY_FACTORY = entityFactory;
    ENTITY_FACTORY.enemyGridInit(Main.FRAME_WIDTH, Main.FRAME_HEIGHT, TILE_SIZE, NUM_ENEMY_ROWS,
                                 NUM_ENEMY_COLS);
    createEntities();

    // Initialize keyboard input and timer
    addKeyListener(new KeyInput(this, player));
    timer = new Timer(10, new ActionListener() {

      // Game loop
      @Override
      public void actionPerformed(ActionEvent e) {

        // Update each entity and check for collision
        player.update();
        if(player.collidesWith(winTile)) {winGame();}

        for(Enemy enemy: enemies) {
          enemy.update();
          if(player.collidesWith(enemy)) {
            resetGame();
            break;
          }
        }

        // Refresh screen
        repaint();
      }
    });
    
    timer.start();
  }


  /**
   * Uses {@code ENTITY_FACTORY} to create the entities reuqired for the game.
   */
  public void createEntities() {
    // Player
    player = ENTITY_FACTORY.getPlayer(PLAYER_START_POINT, PLAYER_START_POINT, PLAYER_WIDTH,
                                      PLAYER_HEIGHT, PLAYER_MOVE_SPEED);
    
    // Win tile
    winTile = ENTITY_FACTORY.getWinTile(Main.FRAME_WIDTH - WIN_TILE_WIDTH, Main.FRAME_HEIGHT -
                                        WIN_TILE_HEIGHT, WIN_TILE_WIDTH, WIN_TILE_HEIGHT);
           
    // Enemies
    enemies = new Enemy[NUM_ENEMIES];
    for(int i=0; i<NUM_ENEMY_ROWS; i++) {
      for(int j=0; j<NUM_ENEMY_COLS; j++) {
        enemies[j + i*NUM_ENEMY_COLS] = ENTITY_FACTORY.getGridEnemy(i, j, ENEMY_DIAMETER,
                                                                    ENEMY_MOVE_SPEED);
      }
    }
  }


  /**
   * Draws the game. JPanel implementation: {@inheritDoc}
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Draw background
    g.setColor(Color.LIGHT_GRAY);
    for(int i=0; i<Main.FRAME_HEIGHT/TILE_SIZE; i++) {
      for(int j=(i%2); j<Main.FRAME_WIDTH/TILE_SIZE; j+=2) {
        g.fillRect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
      }
    }

    // Draw entities
    player.draw(g);
    winTile.draw(g);

    for(Enemy enemy: enemies) {
      enemy.draw(g);
    }
  }


  /**
   * Resets the game to its initial state.
   */
  public void resetGame() {
    player.reset();

    for(Enemy enemy: enemies) {
      enemy.reset();
    }
  }


  /**
   * Ends the game and displays the {@link WinScreen win screen}.
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
