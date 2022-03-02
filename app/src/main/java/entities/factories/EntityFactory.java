package entities.factories;

import entities.*;
import entities.players.Player;
import entities.enemies.Enemy;


/**
 * Abstract Factory for creating {@link Entity entities}.
 */
public interface EntityFactory {

  /**
   * Initializes the parameters required to create enemies for a grid.
   * @param frameWidth width of the frame the game is drawn in.
   * @param frameHeight height of the frame the game is drawn in.
   * @param tileSize size of the tiles that make up the background.
   * @param numEnemyRows number of rows to organize the enemies into.
   * @param numEnemyCols number of columns to organize the enemies into.
   * @see #getGridEnemy
   */
  public void enemyGridInit(int frameWidth, int frameHeight, int tileSize, int numEnemyRows,
                            int numEnemyCols);


  /**
   * Factory method to create a {@link Player} {@link Entity}.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param width width of player.
   * @param height height of player.
   * @param moveSpeed movement speed of player.
   * @return The created player.
   */
  public Player getPlayer(int x, int y, int width, int height, int moveSpeed);
  
  /**
   * Factory method to create a {@link WinTile} {@link Entity}.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param width width of tile.
   * @param height height of tile.
   * @return The created tile.
   */
  public WinTile getWinTile(int x, int y, int width, int height);

  /**
   * Factory method to create an {@link Enemy player} {@link Entity entity}.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param diameter diameter of enemy.
   * @param velocityX initial horizontal velocity of enemy.
   * @param velocityY initial vertical velocity of enemy.
   * @return The created enemy.
   */
  public Enemy getEnemy(int x, int y, int diameter, float velocityX, float velocityY);

  /**
   * Factory method to create an {@link Enemy} with the parameters required to go within a grid of enemies.
   * @param i row of grid.
   * @param j column of grid.
   * @param enemyDiameter diameter of enemy.
   * @param enemyMoveSpeed movement speed of enemy.
   * @return The enemy initialized for the grid.
   * @see #enemyGridInit
   */
  public Enemy getGridEnemy(int i, int j, int enemyDiameter, int enemyMoveSpeed);
}
