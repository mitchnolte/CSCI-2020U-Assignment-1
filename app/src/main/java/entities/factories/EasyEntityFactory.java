package entities.factories;

import entities.*;
import entities.players.*;
import entities.enemies.*;


/**
 * Implentation of {@link EntityFactory} interface to create the {@link entities.Entity entities}
 * required for easy mode.
 */
public class EasyEntityFactory implements EntityFactory {

  private int frameWidth, frameHeight;
  private int tileSize;
  private int spacingX, spacingY;


  /**
   * {@inheritDoc}
   */
  @Override
  public void enemyGridInit(int frameWidth, int frameHeight, int tileSize, int numEnemyRows,
  int numEnemyCols)
  {
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.tileSize = tileSize;
    spacingX = frameWidth / numEnemyCols;
    spacingY = frameHeight / (numEnemyRows + 1);
  }
  

  /**
   * {@inheritDoc}
   */
  @Override
  public Player getPlayer(int x, int y, int width, int height, int moveSpeed) {
    return new EasyPlayer(x, y, width, height, moveSpeed, frameWidth, frameHeight);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Enemy getEnemy(int x, int y, int diameter, float velocityX, float velocityY) {
    return new EasyEnemy(x, y, diameter, velocityX, velocityY);
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
    int velocityX = i%2 == 1? -enemyMoveSpeed : enemyMoveSpeed;
  
    EasyEnemy enemy = (EasyEnemy)getEnemy(x, y, enemyDiameter, velocityX, 0);
    enemy.setTravelDistance(travelDistance);
    return enemy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WinTile getWinTile(int x, int y, int width, int height) {
    return new WinTile(x, y, width, height);
  }
}
