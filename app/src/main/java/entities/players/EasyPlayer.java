package entities.players;

import entities.*;
import entities.enemies.*;


/**
 * Subclass of {@link Player} to be used in easy mode.
 */
public class EasyPlayer extends Player {

  /**
   * {Constructor to create an {@code EasyPlayer} object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param width width of player.
   * @param height height of player.
   * @param moveSpeed movement speed of player.
   * @param frameWidth width of the frame the game is drawn in.
   * @param frameHeight height of the frame the game is drawn in.
   */
  public EasyPlayer(int x, int y, int width, int height, int moveSpeed, int frameWidth,
                    int frameHeight) 
  {
    super(x, y, width, height, moveSpeed, frameWidth, frameHeight);
  }

  
  @Override
  public boolean collidesWith(Entity entity) {

    // Win tile collision
    if(entity instanceof WinTile) {
      WinTile tile = (WinTile)entity;
      if(hitbox.intersects(tile.getHitbox())) {return true;}
    }

    // Enemy collision
    if(!(entity instanceof Enemy)) {return false;}

    Enemy enemy = (Enemy)entity;
    return calculateCollision(enemy);
  }
}
