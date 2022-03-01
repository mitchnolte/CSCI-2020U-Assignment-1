package entities.enemies;

import java.awt.Graphics;
import java.awt.Color;
import entities.Entity;


/**
 * An enemy entity, which is a blue circle.
 * @see entities.Entity
 */
public abstract class Enemy extends Entity {
  
  protected final int XSTART, YSTART;
  protected int diameter;
  
  /**
   * Constructor to create an enemy object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param diameter diameter of enemy.
   * @param color color of enemy.
   */
  public Enemy(int x, int y, int diameter, Color color) {
    super(x, y, color);
    this.diameter = diameter;
    XSTART = x;
    YSTART = y;
  }

  /**
   * @return The x coordinate of the middle of the circle.
   */
  public int getMidPointX() {return getX()+diameter/2;}

  /**
   * @return The y coordinate of the middle of the circle.
   */
  public int getMidPointY() {return getY()+diameter/2;}

  /**
   * @return The radius of the circle.
   */
  public float getRadius() {return diameter/2;}


  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval(getX(), getY(), diameter, diameter);
  }

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