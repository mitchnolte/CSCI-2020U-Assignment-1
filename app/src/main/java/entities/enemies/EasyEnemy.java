package entities.enemies;

import java.awt.Color;


/**
 * Implementation of the abstract Enemy class for easy mode.
 * @see Enemy
 * @see entities.Entity
 */
public class EasyEnemy extends Enemy {

  private final int VELOCITY_START;
  private int velocity;
  private int travelDistance;


  /**
   * Constructor to create an EasyEnemy object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param diameter diameter of enemy.
   * @param velocity initial velocity of enemy.
   * @param travelDistance distance the enemy will travel before turning around.
   */
  public EasyEnemy(int x, int y, int diameter, int velocity, int travelDistance) {
    super(x, y, diameter, Color.BLUE);
    VELOCITY_START = velocity;
    this.velocity = velocity;
    this.travelDistance = travelDistance;
  }


  /**
   * Calculates and updates position of enemy.
   */
  @Override
  public void update() {
    x += velocity;
    if(VELOCITY_START > 0 && (x >= XSTART+travelDistance || x <= XSTART)) {velocity *= -1;}
    else if(VELOCITY_START < 0 && (x <= XSTART-travelDistance || x >= XSTART)) {velocity *= -1;}
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    x = XSTART;
    y = YSTART;
    velocity = VELOCITY_START;
  }
}
