package entities.enemies;

import java.awt.Graphics;
import java.awt.Color;
import game.Main;


/**
 * A projectile shot from an enemy in hard mode.
 * @see Enemy
 * @see HardEnemy
 * @see entities.Entity
 */
public class EnemyProjectile extends Enemy {
  
  private final float VEL_MULTIPLIER_X, VEL_MULTIPLIER_Y;
  private float velocityX, velocityY;
  private boolean inPlay;


  /**
   * Constructor to create EnemyProjectile object.
   * @param diameter diameter of projectile.
   * @param velMultiplierX multiplier for horizontal shot velocity.
   * @param velMultiplierY multiplier for vertical shot velocity.
   */
  public EnemyProjectile(int diameter, float velMultiplierX, float velMultiplierY) {
    super(0, 0, diameter, new Color(0, 0, 100));
    VEL_MULTIPLIER_X = velMultiplierX;
    VEL_MULTIPLIER_Y = velMultiplierY;
    inPlay = false;
  }


  /**
   * @return Whether the projectile is in play or not. If it is in play it cannot be fired.
   */
  public boolean isInPlay() {return inPlay;}

  
  /**
   * Shoots the projectile from (x, y) with the specified velocity mutliplied by the projectile's
   * velocity multipliers.
   * @param x x coordinate to shoot from.
   * @param y x coordinate to shoot from.
   * @param velocityX x axis velocity.
   * @param velocityY y axis velocity.
   */
  public void shoot(int x, int y, float velocityX, float velocityY) {
    this.x = x;
    this.y = y;
    this.velocityX = velocityX * VEL_MULTIPLIER_X;
    this.velocityY = velocityY * VEL_MULTIPLIER_Y;
    inPlay = true;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(Graphics g) {
    if(inPlay) {
      g.setColor(color);
      g.fillOval(getX(), getY(), diameter, diameter);
    }
  }


  /**
   * Updates the position of the projectile if it is in play, and removes it when it leaves the
   * screen.
   */
  @Override
  public void update() {
    if(inPlay) {
      x += velocityX;
      y += velocityY;

      // Stop updating if off screen
      if(x >= Main.FRAME_WIDTH || x <= -diameter || y >= Main.FRAME_HEIGHT || y <= -diameter) {
        inPlay = false;
      }
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    inPlay = false;
  }
}
