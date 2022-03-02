package entities.factories;

import java.util.Random;
import entities.*;
import entities.enemies.*;
import entities.players.*;


/**
 * Implentation of the {@link EntityFactory} interface to create the {@link entities.Entity entities}
 * required for hard mode.
 */
public class HardEntityFactory implements EntityFactory {

  private Random rand = new Random();
  private int frameWidth, frameHeight;
  private int numEnemyRows, numEnemyCols;
  private int tileSize;
  private int spacingX, spacingY;
  private int startX;


  /**
   * {@inheritDoc}
   */
  @Override
  public void enemyGridInit(int frameWidth, int frameHeight, int tileSize, int numEnemyRows,
  int numEnemyCols)
  {
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.numEnemyRows = numEnemyRows;
    this.numEnemyCols = numEnemyCols;
    this.tileSize = tileSize;
    spacingX = frameWidth / numEnemyCols;
    spacingY = frameHeight / (numEnemyRows + 1);
    startX = frameWidth / 30;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public Player getPlayer(int x, int y, int width, int height, int moveSpeed) {
    return new HardPlayer(x, y, width, height, moveSpeed, frameWidth, frameHeight);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public Enemy getEnemy(int x, int y, int diameter, float maxVelocityX, float maxVelocityY) {
    HardEnemy enemy = new HardEnemy(x, y, diameter, maxVelocityX, maxVelocityY);
    enemy.setProjectile(getProjectile(diameter/2, getProjectileVelocityMultiplier(),
                                      getProjectileVelocityMultiplier()));
    return enemy;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public Enemy getGridEnemy(int i, int j, int enemyDiameter, int enemyMoveSpeed) {
    float rowOffset = (frameWidth / numEnemyCols - enemyDiameter) / 1.6f;
    int tileOffset = (tileSize - enemyDiameter) / 2;

    int x = startX + j * spacingX;
    int y = (i+1) * spacingY + tileOffset;
    int maxVelocityX = i%2 == 1? -enemyMoveSpeed : enemyMoveSpeed;
    int maxVelocityY = j%2 == 1? -maxVelocityX : maxVelocityX;
    if(i%2 != 0) {x += rowOffset;}
  
    return getEnemy(x, y, enemyDiameter, maxVelocityX, maxVelocityY);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public WinTile getWinTile(int x, int y, int width, int height) {
    return new WinTile(x, y, width, height);
  }


  /**
   * Creates an {@link EnemyProjectile} {@link Entity}.
   * @param diameter diameter of the projectile.
   * @param velMultiplierX multiplier for horizontal shot velocity.
   * @param velMultiplierY multiplier for vertical shot velocity.
   * @return The created projectile.
   */
  public EnemyProjectile getProjectile(int diameter, float velMultiplierX, float velMultiplierY) {
    return new EnemyProjectile(diameter, velMultiplierX, velMultiplierY, frameWidth, frameHeight);
  }


  /**
   * Genereates a random float in the range [-2.25, -0.75] U [0.75, 2.25] to be used as a velocity
   * multiplier for a {@link entities.enemies.EnemyProjectile projectile}.
   * @return The random float.
   */
  private float getProjectileVelocityMultiplier() {
    float multiplier = (rand.nextFloat() + 0.5f) * 1.5f;

    // 50% chance to reverse the direction.
    if(rand.nextBoolean()) {return multiplier;}
    else {return -multiplier;}
  }
}
