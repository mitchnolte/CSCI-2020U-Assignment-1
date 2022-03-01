package entities.factories;

import entities.Entity;
import game.Main;


/**
 * Factory interface for creating entities.
 * @see entities.Entity
 */
public interface EntityFactory {

  // General information
  public static final int TILE_SIZE = Main.FRAME_WIDTH / 25;

  // Player information
  public static final int PLAYER_WIDTH = Main.FRAME_WIDTH / 32;
  public static final int PLAYER_HEIGHT = PLAYER_WIDTH;
  public static final int PLAYER_START_POINT = (TILE_SIZE - PLAYER_WIDTH) / 2;
  public static final int PLAYER_MOVE_SPEED = Main.FRAME_WIDTH / 400;

  // Enemy information
  public static final int NUM_ENEMIES = 20;
  public static final int NUM_ENEMY_COLS = 5;
  public static final int NUM_ENEMY_ROWS = NUM_ENEMIES / NUM_ENEMY_COLS;
  public static final int ENEMY_DIAMETER = Main.FRAME_WIDTH / 40;
  public static final int PROJECTILE_DIAMETER = ENEMY_DIAMETER / 2;
  public static final int ENEMY_TRAVEL_DISTANCE = Main.FRAME_WIDTH / NUM_ENEMY_COLS -
                                                  ENEMY_DIAMETER;
  
  public static final int ENEMY_MOVE_SPEED = Main.FRAME_HEIGHT / 320;
  public static final int ENEMY_SPACING_X = ENEMY_TRAVEL_DISTANCE + ENEMY_DIAMETER;
  public static final int ENEMY_SPACING_y = Main.FRAME_HEIGHT / (NUM_ENEMY_ROWS + 1);
  public static final int ENEMY_TILE_OFFSET = (TILE_SIZE - ENEMY_DIAMETER) / 2;


  /**
   * Creates all the entities required for the game.
   * @return an array of entities.
   */
  public Entity[] getEntities();
}
