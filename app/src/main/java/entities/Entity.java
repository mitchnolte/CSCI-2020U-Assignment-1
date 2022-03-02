package entities;

import java.awt.Color;
import java.awt.Graphics;


/**
 * An {@code Entity} is anything in the game that can be interacted with in some way, such as a
 * {@link Player player} or an {@link entities.enemies.Enemy enemy}. Call {@code update()} and
 * {@code draw(Graphics g)} once per frame.
 */
public abstract class Entity {

  protected float x, y;
  protected Color color;


  /**
   * Constructor to create an {@code Entity} object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param color color of entity.
   */
  public Entity(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }
  
  /**
   * @return X coordinate as an integer.
   */
  public int getX() {
    return (int)x;
  }

  /**
   * @return Y coordinate as an integer.
   */
  public int getY() {
    return (int)y;
  }

  /**
   * Updates the entity. Usually updates position, sometimes other things as well.
   */
  public abstract void update();

  /**
   * Resets the entity to its initial state.
   */
  public abstract void reset();

  /**
   * Draws the entity to the screen.
   * @param g Graphics object to draw to the panel.
   */
  public abstract void draw(Graphics g);
}
