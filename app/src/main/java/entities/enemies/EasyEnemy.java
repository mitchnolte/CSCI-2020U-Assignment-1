package entities.enemies;

import java.awt.Color;


/**
 * Implementation of the abstract {@link Enemy} class for easy mode. Has simple linear movement.
 * @see entities.Entity Entity
 */
public class EasyEnemy extends Enemy {

  private final float VELOCITY_START;
  private float travelDistance;


  /**
   * Constructor to create an {@code EasyEnemy} object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param diameter diameter of enemy.
   * @param velocity initial velocity of enemy.
   * @param travelDistance distance the enemy will travel before turning around.
   */
  public EasyEnemy(int x, int y, int diameter, float velocity, float travelDistance) {
    super(x, y, diameter, velocity, 0, Color.BLUE);
    VELOCITY_START = velocity;
    this.travelDistance = travelDistance;
  }


  /**
   * Calculates and updates position of enemy.
   */
  @Override
  public void update() {
    x += velocityX;
    if(VELOCITY_START > 0 && (x >= XSTART+travelDistance || x <= XSTART)) {velocityX *= -1;}
    else if(VELOCITY_START < 0 && (x <= XSTART-travelDistance || x >= XSTART)) {velocityX *= -1;}
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    x = XSTART;
    y = YSTART;
    velocityX = VELOCITY_START;
  }
}
