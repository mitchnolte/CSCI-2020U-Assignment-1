package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 * An {@link Entity} representing the space on the board where the {@link Player} needs to go to
 * win.
 */
public class WinTile extends Entity {
  
  private int width;
  private int height;
  private Rectangle hitbox;
  

  /**
   * Constructor to create a {@code WinTile} object.
   * @param x x coordinate.
   * @param y y coordinate.
   * @param width width of tile.
   * @param height height of tile.
   */
  public WinTile(int x, int y, int width, int height) {
    super(x, y, new Color(0, 200, 0, 100));
    this.width = width;
    this.height = height;
    hitbox = new Rectangle(x, y, width, height);
  }


  /**
   * @return The hitbox of the tile as a {@link Rectangle}.
   */
  public Rectangle getHitbox() {return hitbox;}

  
  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect(getX(), getY(), width, height);
  }

  /**
   * {@inheritDoc} For a {@code WinTile}, there is nothing to update.
   */
  @Override
  public void update() {}

  /**
   * {@inheritDoc} For a {@code WinTile}, there is nothing to reset.
   */
  @Override
  public void reset() {}
}
