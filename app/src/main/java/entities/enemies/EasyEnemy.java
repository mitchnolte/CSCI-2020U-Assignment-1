package entities.enemies;

import java.awt.Color;


/**
 * Implementation of the abstract {@link Enemy} class for easy mode. Has simple linear movement.
 * @see entities.Entity Entity
 */
public class EasyEnemy extends Enemy {

  private final float VELOCITY_START_X, VELOCITY_START_Y;
  private int travelDistance;


  /**
   * Constructor to create an {@code EasyEnemy} object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param diameter diameter of enemy.
   * @param velocityX initial horizontal velocity of enemy.
   * @param velocityY initial vertical velocity of enemy.
   */
  public EasyEnemy(int x, int y, int diameter, float velocityX, float velocityY) {
    super(x, y, diameter, velocityX, velocityY, Color.BLUE);
    VELOCITY_START_X = velocityX;
    VELOCITY_START_Y = velocityY;
  }


  /**
   * Sets the travel distance of the enemy.
   * @param travelDistance distance the enemy travels before turning around.
   */
  public void setTravelDistance(int travelDistance) {
    this.travelDistance = travelDistance;
  }


  /**
   * Calculates and updates position of enemy.
   */
  @Override
  public void update() {
    x += velocityX;
    y += velocityY;

    if(VELOCITY_START_X > 0 && (x >= XSTART+travelDistance || x <= XSTART)) {velocityX *= -1;}
    else if(VELOCITY_START_X < 0 && (x <= XSTART-travelDistance || x >= XSTART)) {velocityX *= -1;}

    if(VELOCITY_START_Y > 0 && (y >= YSTART+travelDistance || y <= YSTART)) {velocityY *= -1;}
    else if(VELOCITY_START_Y < 0 && (y <= YSTART-travelDistance || y >= YSTART)) {velocityY *= -1;}
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    x = XSTART;
    y = YSTART;
    velocityX = VELOCITY_START_X;
  }
}
