package entities.players;

import entities.*;
import entities.enemies.*;


/**
 * Subclass of {@link Player} to be used in hard mode. Overrides {@link #collidesWith} to check for
 * collision with the enemy's projectile.
 */
public class HardPlayer extends Player {

  /**
   * {Constructor to create a {@code HardPlayer} object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param width width of player.
   * @param height height of player.
   * @param moveSpeed movement speed of player.
   * @param frameWidth width of the frame the game is drawn in.
   * @param frameHeight height of the frame the game is drawn in.
   */
  public HardPlayer(int x, int y, int width, int height, int moveSpeed, int frameWidth,
                    int frameHeight) 
  {
    super(x, y, width, height, moveSpeed, frameWidth, frameHeight);
  }


  /**
   * {@inheritDoc} Overrides collision method from {@link Player} to check for collision with an
   * enemy's projectile as well as the enemy itself.
   */
  @Override
  public boolean collidesWith(Entity entity) {
    if(super.collidesWith(entity)) {return true;}

    // Projectile collision
    if(!(entity instanceof Enemy)) {return false;}

    HardEnemy enemy = (HardEnemy)entity;
    if(!enemy.getProjectile().isOnScreen()) {return false;}
    return super.calculateCollision(enemy.getProjectile());
  }
}
