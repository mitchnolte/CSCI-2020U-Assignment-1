package entities.enemies;

import java.awt.Graphics;
import java.awt.Color;


/**
 * A projectile {@link entities.Entity entity} shot from an {@link Enemy enemy} in hard mode.
 * @see HardEnemy
 */
public class EnemyProjectile extends Enemy {
  
  private final float VEL_MULTIPLIER_X, VEL_MULTIPLIER_Y;
  private float velocityX, velocityY;
  private int frameWidth, frameHeight;
  private boolean onScreen;


  /**
   * Constructor to create {@code EnemyProjectile} object.
   * @param diameter diameter of projectile.
   * @param velMultiplierX multiplier for horizontal shot velocity.
   * @param velMultiplierY multiplier for vertical shot velocity.
   * @param frameWidth width of the frame the game is drawn in.
   * @param frameHeight height of the frame the game is drawn in.
   */
  public EnemyProjectile(int diameter, float velMultiplierX, float velMultiplierY, int frameWidth,
                         int frameHeight)
  {
    super(0, 0, diameter, 0, 0, new Color(0, 0, 100));
    VEL_MULTIPLIER_X = velMultiplierX;
    VEL_MULTIPLIER_Y = velMultiplierY;
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    onScreen = false;
  }


  /**
   * @return Whether the projectile is on screen or not. If it is it cannot be fired.
   */
  public boolean isOnScreen() {
    return onScreen;
  }

  
  /**
   * Shoots the projectile from {@code (x, y)} with the specified velocity mutliplied by the
   * projectile's velocity multipliers.
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
    onScreen = true;
  }


  /**
   * {@inheritDoc} Only draws if the projectile should be shown, i.e. {@code onScreen == true}.
   */
  @Override
  public void draw(Graphics g) {
    if(onScreen) {
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
    if(onScreen) {
      x += velocityX;
      y += velocityY;

      // Stop updating if off screen
      if(x > frameWidth || x < -diameter || y > frameHeight || y < -diameter) {
        onScreen = false;
      }
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    onScreen = false;
  }
}
