package entities.factories;

import entities.*;
import entities.players.*;
import entities.enemies.*;


/**
 * Implentation of {@link EntityFactory} interface to create the {@link entities.Entity entities}
 * required for easy mode.
 */
public class EasyEntityFactory implements EntityFactory {

  private int tileSize;
  private int spacingX, spacingY;


  /**
   * {@inheritDoc}
   */
  @Override
  public void enemyGridInit(int frameWidth, int frameHeight, int tileSize, int numEnemyRows,
  int numEnemyCols)
  {
    this.tileSize = tileSize;
    spacingX = frameWidth / numEnemyCols;
    spacingY = frameHeight / (numEnemyRows + 1);
  }
  

  /**
   * {@inheritDoc}
   */
  @Override
  public Player getPlayer(int x, int y, int width, int height, int moveSpeed) {
    return new Player(x, y, width, height, moveSpeed);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Enemy getEnemy(int x, int y, int diameter, float velocity, float travelDistance) {
    return new EasyEnemy(x, y, diameter, velocity, travelDistance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Enemy getGridEnemy(int i, int j, int enemyDiameter, int enemyMoveSpeed) {
    int travelDistance = spacingX - enemyDiameter;
    int tileOffset = (tileSize - enemyDiameter) / 2;

    int x = j * spacingX;
    if(i%2 != 0) {x += travelDistance;}
    int y = (i+1) * spacingY + tileOffset;
    int velocity = i%2 == 1? -enemyMoveSpeed : enemyMoveSpeed;
  
    return getEnemy(x, y, enemyDiameter, velocity, travelDistance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WinTile getWinTile(int x, int y, int width, int height) {
    return new WinTile(x, y, width, height);
  }
}
