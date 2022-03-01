package entities.factories;

import entities.Entity;
import game.Main;


/**
 * Factory interface for creating {@link Entity entities}. Contains constants used for entity
 * initialization.
 */
public interface EntityFactory {

  /**
   * Size of the tiles that make up the game board.
   */
  public static final int TILE_SIZE = Main.FRAME_WIDTH / 25;


            // Player information
  /**
   * Width of the player. 
   */
  public static final int PLAYER_WIDTH = Main.FRAME_WIDTH / 32;

  /**
   * Height of the player.
   */
  public static final int PLAYER_HEIGHT = PLAYER_WIDTH;

  /**
   * Initial x and y coordinate to start the player in the middle of the top left tile.
   */
  public static final int PLAYER_START_POINT = (TILE_SIZE - PLAYER_WIDTH) / 2;

  /**
   * Movement speed of the player.
   */
  public static final int PLAYER_MOVE_SPEED = Main.FRAME_WIDTH / 400;


            // Enemy information
  /**
   * Number of enemies to add to the game.
   */
  public static final int NUM_ENEMIES = 20;

  /**
   * Number of columns to organize the enemies into.
   */
  public static final int NUM_ENEMY_COLS = 5;

  /**
   * Number of rows to organize the enemies into.
   */
  public static final int NUM_ENEMY_ROWS = NUM_ENEMIES / NUM_ENEMY_COLS;

  /**
   * Diamter of enemy.
   */
  public static final int ENEMY_DIAMETER = Main.FRAME_WIDTH / 40;

  /**
   * Diameter of enemy projectile.
   */
  public static final int PROJECTILE_DIAMETER = ENEMY_DIAMETER / 2;

  /**
   * Horizontal distance travelled by enemies in easy mode.
   */
  public static final int ENEMY_TRAVEL_DISTANCE = Main.FRAME_WIDTH / NUM_ENEMY_COLS -
                                                  ENEMY_DIAMETER;
  
  /**
   * Movement speed of enemies.
   */
  public static final int ENEMY_MOVE_SPEED = Main.FRAME_HEIGHT / 320;

  /**
   * Initial horizontal distance between enemies
   */
  public static final int ENEMY_SPACING_X = ENEMY_TRAVEL_DISTANCE + ENEMY_DIAMETER;

  /**
   * Initil vertical distance between enemies
   */
  public static final int ENEMY_SPACING_y = Main.FRAME_HEIGHT / (NUM_ENEMY_ROWS + 1);

  /**
   * Similar to {@link #PLAYER_START_POINT}, distance added to an enemy's start coordinate (x or y)
   * to ensure it starts in the middle of a tile.
   */
  public static final int ENEMY_TILE_OFFSET = (TILE_SIZE - ENEMY_DIAMETER) / 2;




  /**
   * Creates all the {@link entities.Entity entities} required for the game.
   * @return an array of entities.
   */
  public Entity[] getEntities();
}
