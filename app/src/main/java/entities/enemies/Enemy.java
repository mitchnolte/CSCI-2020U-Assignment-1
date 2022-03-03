package entities.enemies;

import java.awt.Graphics;
import java.awt.Color;
import entities.Entity;


/**
 * An enemy {@link Entity} is a blue circle that kills the player on contact.
 */
public abstract class Enemy extends Entity {
  
  protected final int XSTART, YSTART;
  protected int diameter;
  protected float velocityX;
  protected float velocityY;
  
  /**
   * Constructor to create an {@code Enemy} object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param diameter diameter of enemy.
   * @param velocityX initial horizontal velocity of enemy.
   * @param velocityY initial vertical velocity of enemy.
   * @param color color of enemy.
   */
  public Enemy(int x, int y, int diameter, float velocityX, float velocityY, Color color) {
    super(x, y, color);
    this.diameter = diameter;
    XSTART = x;
    YSTART = y;
    this.velocityX = velocityX;
    this.velocityY = velocityY;
  }

  /**
   * @return The x coordinate of the middle of the circle.
   */
  public int getMidPointX() {
    return getX()+diameter/2;
  }

  /**
   * @return The y coordinate of the middle of the circle.
   */
  public int getMidPointY() {
    return getY()+diameter/2;
  }

  /**
   * @return The radius of the circle.
   */
  public float getRadius() {
    return diameter/2;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public abstract void draw(Graphics g);

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract void update();

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract void reset();
}