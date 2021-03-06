package entities.players;

import entities.*;
import entities.enemies.*;


/**
 * Subclass of {@link Player} to be used in hard mode.
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
   * {@inheritDoc} Also checks if the player is colliding with the enemy's projectile.
   */
  @Override
  public boolean collidesWith(Entity entity) {
    
    // Win tile collision
    if(entity instanceof WinTile) {
      WinTile tile = (WinTile)entity;
      if(hitbox.intersects(tile.getHitbox())) {return true;}
    }

    // Enemy collision
    if(!(entity instanceof HardEnemy)) {return false;}

    HardEnemy enemy = (HardEnemy)entity;
    if(calculateCollision(enemy)) {return true;}

    // Projectile collision
    if(!enemy.getProjectile().isOnScreen()) {return false;}
    return super.calculateCollision(enemy.getProjectile());
  }
}
