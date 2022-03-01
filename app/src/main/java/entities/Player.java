package entities;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import game.Main;
import entities.enemies.Enemy;
import entities.enemies.EnemyProjectile;


/**
 * A player entity, which is a red square.
 * @see entities.Entity
 */
public class Player extends Entity {

  private final int XSTART, YSTART;
  private int width, height;
  private int velocityX, velocityY;
  private int moveSpeed;
  private boolean W=false, A=false, S=false, D=false;
  private boolean canMove=true;
  private Rectangle hitbox;


  /**
   * Constructor to create a player object.
   * @param x initial x coordinate.
   * @param y initial y coordinate.
   * @param width width of player.
   * @param height height of player.
   * @param moveSpeed movement speed of player.
   */
  public Player(int x, int y, int width, int height, int moveSpeed) {
    super(x, y, Color.RED);
    XSTART = x;
    YSTART = y;
    this.width = width;
    this.height = height;
    this.moveSpeed = moveSpeed;
    velocityX = 0;
    velocityY = 0;
    hitbox = new Rectangle(x, y, width, height);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(Color.BLACK);
    g.drawRect(getX(), getY(), width, height);

    g.setColor(color);
    g.fillRect(getX()+1, getY()+1, width-1, height-1);
  }

  
  /**
   * Updates player position and stops it from moving beyond the screen.
   */
  @Override
  public void update() {
    x += velocityX;
    y += velocityY;
    hitbox.setLocation(getX(), getY());

    // Screen border collision
    if(x < 0) {x = 0;}
    if(y < 0) {y = 0;}
    if(x + width > Main.FRAME_WIDTH) {x = Main.FRAME_WIDTH - width;}
    if(y + height > Main.FRAME_HEIGHT) {y = Main.FRAME_HEIGHT - height;}
  }


  /**
   * {@inheritDoc} Also stops the player from moving until they release the movement keys.
   */
  @Override
  public void reset() {
    x = XSTART;
    y = YSTART;
    
    // Stop movement until movement keys released
    canMove = false;
    velocityX = 0;
    velocityY = 0;
  }


  /**
   * Determines whether the player is colliding with an entity or not.
   * @param entity the entity to check for collision with the player.
   * @return Whether entity collides with the player or not.
   */
  public boolean collidesWith(Entity entity) {
    // Win tile collision
    if(entity instanceof WinTile) {
      WinTile tile = (WinTile)entity;
      if(hitbox.intersects(tile.getHitbox())) {return true;}
    }

    // Enemy collision
    if(!(entity instanceof Enemy)) {return false;}
    if(entity instanceof EnemyProjectile && !((EnemyProjectile)entity).isInPlay()) {return false;}

    Enemy enemy = (Enemy)entity;
    int enemyX = enemy.getMidPointX();
    int enemyY = enemy.getMidPointY();
    float enemyRadius = enemy.getRadius();

    // Calculate horizontal distance from center of circle to closest vertical edge
    float distanceX;
    if(enemyX > x && enemyX < x+width) {distanceX = 0;}
    else {distanceX = Math.min(Math.abs(x-enemyX), Math.abs(x+width - enemyX));}

    // Calculate vertical distance from center of circle to closest horizontal edge
    float distanceY;
    if(enemyY > y && enemyY < y+height) {distanceY = 0;}
    else {distanceY = Math.min(Math.abs(y-enemyY), Math.abs(y+height - enemyY));}

    // Determine if distance is less than the raduis of circle
    if(distanceX*distanceX + distanceY*distanceY < enemyRadius*enemyRadius) {return true;}
    else {return false;}
  }


  /**
   * Sets the player's velocity to move in the desired direction.
   * @param direction char which can be 'W', 'A', 'S', or 'D' to represent the direction by the key
   * pressed to move in that direction.
   */
  public void startMoving(char direction) {
    if(canMove) {
      switch(direction) {
        case 'W':
          if(!W) {
            if(!S) {velocityY = -moveSpeed;}
            else {velocityY = 0;}
            W = true;
          }
          break;
        case 'A':
          if(!A) {
            if(!D) {velocityX = -moveSpeed;}
            else {velocityX = 0;}
            A = true;
          }
          break;
        case 'S':
          if(!S) {
            if(!W) {velocityY = moveSpeed;}
            else {velocityY = 0;}
            S = true;
          }
          break;
        case 'D':
          if(!D) {
            if(!A) {velocityX = moveSpeed;}
            else {velocityX = 0;}
            D = true;
          }
          break;
      }
    }
  }

  /**
   * Stops the player from moving in a desired direciton.
   * @param direction char which can be 'W', 'A', 'S', or 'D' to represent the direction by the key
   * pressed to move in that direction.
   */
  public void stopMoving(char direction) {
    switch(direction) {
      case 'W':
        if(canMove) {
          if(velocityY == 0) {velocityY = moveSpeed;}
          else {velocityY = 0;}
        }
        W = false;
        break;
      case 'A':
        if(canMove) {
          if(velocityX == 0) {velocityX = moveSpeed;}
          else {velocityX = 0;}
        }
        A = false;
        break;
      case 'S':
        if(canMove) {
          if(velocityY == 0) {velocityY = -moveSpeed;}
          else {velocityY = 0;}
        }
        S = false;
        break;
      case 'D':
        if(canMove) {
          if(velocityX == 0) {velocityX = -moveSpeed;}
          else {velocityX = 0;}
        }
        D = false;
        break;
    }
  }

  /**
   * When the palyer is not allowed to move, this method allows the player to move once all
   * movement keys are released.
   */
  public void canMove() {
    if(!W && !A && !S && !D) {canMove = true;}
  }
}
