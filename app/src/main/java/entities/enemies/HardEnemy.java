package entities.enemies;

import java.awt.Graphics;
import java.awt.Color;


/**
 * Implementation of the abstract {@link Enemy} class for hard mode. Moves in a circle or oval
 * pattern and shoots projectiles.
 * @see entities.Entity
 */
public class HardEnemy extends Enemy {
  
  /**
   * If {@code MAX_VELOCITY_X} and {@code MAX_VELOCITY_Y} are equal, they represent the angular
   * velocity of the enemy.
   */
  private final float MAX_VELOCITY_X, MAX_VELOCITY_Y;
  private float velocityX, velocityY;

  /**
   * Determines size of circular path. Calculated based on maximum velocity, so for any given ratio
   * of {@link #MAX_VELOCITY_X}:{@link #MAX_VELOCITY_Y}, the radius of the path is (almost) constant.
   */
  private final float VEL_RAD_INCREMENT_X, VEL_RAD_INCREMENT_Y;
  private float velocityRadiansX, velocityRadiansY;

  private EnemyProjectile projectile;


  /**
   * Constructor to create a {@code HardEnemy} object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param diameter diameter of enemy.
   * @param maxVelocityX the maximum horizontal velocity.
   * @param maxVelocityY the maximum vertical velocity.
   */
  public HardEnemy(int x, int y, int diameter, float maxVelocityX, float maxVelocityY) {
    super(x, y, diameter, maxVelocityX, maxVelocityY, Color.BLUE);
    MAX_VELOCITY_X = maxVelocityX;
    MAX_VELOCITY_Y = maxVelocityY;
    velocityX = 0;
    velocityY = 0;
    velocityRadiansX = 0;
    velocityRadiansY = 0;
    VEL_RAD_INCREMENT_X = Math.abs(MAX_VELOCITY_X) / 45;
    VEL_RAD_INCREMENT_Y = Math.abs(MAX_VELOCITY_Y) / 45;
  }


  /**
   * Sets the enemy's projectile.
   * @param projectile the projectile to give to the enemy.
   */
  public void setProjectile(EnemyProjectile projectile) {
    this.projectile = projectile;
  }


  /**
   * @return The enemy's projectile.
   */
  public EnemyProjectile getProjectile() {
    return projectile;
  }


  /**
   * {@inheritDoc} Also calls {@code draw(g)} for the enemy's projectile.
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval(getX(), getY(), diameter, diameter);
    projectile.draw(g);
  }


  /**
   * Updates the position of the enemy and its projectile and attempts to shoot the projectile.
   */
  @Override
  public void update() {

    // Circular movement
    velocityRadiansX += VEL_RAD_INCREMENT_X;
    velocityRadiansY += VEL_RAD_INCREMENT_Y;
    velocityX = (float)(MAX_VELOCITY_X * Math.cos(velocityRadiansX));
    velocityY = (float)(MAX_VELOCITY_Y * Math.sin(velocityRadiansY));
    
    x += velocityX;
    y += velocityY;

    // Update and shoot projectile if ready
    projectile.update();
    if(!projectile.isOnScreen()) {
      projectile.shoot(getX(), getY(), velocityX, velocityY);
    }
  }


  /**
   * {@inheritDoc} Also resets the enemy's projectile.
   */
  @Override
  public void reset() {
    x = XSTART;
    y = YSTART;
    velocityX = MAX_VELOCITY_X;
    velocityY = MAX_VELOCITY_Y;
    velocityRadiansX = 0;
    velocityRadiansY = 0;
    projectile.reset();
  }
}
