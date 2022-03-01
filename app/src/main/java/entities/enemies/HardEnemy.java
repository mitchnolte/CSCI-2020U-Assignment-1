package entities.enemies;

import java.awt.Color;


/**
 * Implementation of the abstract {@link Enemy} class for hard mode.
 * @see entities.Entity
 */
public class HardEnemy extends Enemy {
  
  private final int VELOCITY_X_MODIFIER, VELOCITY_Y_MODIFIER;
  private float velocityX, velocityY;
  private float velocityRadians;
  private EnemyProjectile projectile;


  /**
   * Constructor to create a {@code HardEnemy)} object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param diameter diameter of enemy.
   * @param maxVelocityX the maximum horizontal velocity.
   * @param maxVelocityY the maximum vertical velocity.
   * @param projectile the enemy's projectile.
   */
  public HardEnemy(int x, int y, int diameter, int maxVelocityX, int maxVelocityY,
                   EnemyProjectile projectile)
  {
    super(x, y, diameter, Color.BLUE);
    VELOCITY_X_MODIFIER = maxVelocityX;
    VELOCITY_Y_MODIFIER = maxVelocityY;
    this.velocityX = 0;
    this.velocityY = 0;
    velocityRadians = 0;
    this.projectile = projectile;
  }


  /**
   * Shoots the projectile in the direction the enemy is going if the projectile is ready to be
   * shot. Direction may be altered by the projectiles velocity multipliers.
   */
  public void shootProjectile() {
    if(!projectile.isInPlay()) {
      projectile.shoot(getX(), getY(), velocityX, velocityY);
    }
  }


  /**
   * Updates the enemy's position and attempts to shoot its projectile.
   */
  @Override
  public void update() {
    x += velocityX;
    y += velocityY;

    // Circular movement
    velocityRadians += 0.07;
    velocityY = (float)(VELOCITY_Y_MODIFIER * Math.sin(velocityRadians));
    velocityX = (float)(VELOCITY_X_MODIFIER * Math.cos(velocityRadians));

    shootProjectile();
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    x = XSTART;
    y = YSTART;
    velocityX = VELOCITY_X_MODIFIER;
    velocityY = VELOCITY_Y_MODIFIER;
    velocityRadians = 0;
  }
}
