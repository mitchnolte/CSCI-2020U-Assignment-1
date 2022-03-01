package entities;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import game.Main;
import entities.enemies.Enemy;
import entities.enemies.EnemyProjectile;


/**
 * A player {@link Entity}, which is a red square.
 */
public class Player extends Entity {

  private final int XSTART, YSTART;
  private int width, height;
  private Rectangle hitbox;
  private float velocityX, velocityY;
  private final int MOVE_SPEED;
  private final float DIAG_MOVE_SPEED;
  private boolean W=false, A=false, S=false, D=false;
  private boolean canMove=true;


  /**
   * Constructor to create a {@code Player} object.
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
    this.MOVE_SPEED = moveSpeed;
    DIAG_MOVE_SPEED = MOVE_SPEED*(float)Math.sin(Math.PI/4);
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
   * Calculates and updates player velocity and position. Also stops player from moving beyond the
   * screen edges.
   */
  @Override
  public void update() {

    // Determine velocity based on which movement keys are pressed
    if(canMove) {
      if(W && S) {velocityY = 0;}
      else if(W) {velocityY = -MOVE_SPEED;}
      else if(S) {velocityY = MOVE_SPEED;}
      else {velocityY = 0;}

      if(A && D) {velocityX = 0;}
      else if(A) {velocityX = -MOVE_SPEED;}
      else if(D) {velocityX = MOVE_SPEED;}
      else {velocityX = 0;}
    }

    // Diagonal velocity
    if(velocityX != 0 && velocityY != 0) {
      velocityX = DIAG_MOVE_SPEED;
      velocityY = DIAG_MOVE_SPEED;
      velocityX = D? velocityX : -velocityX;
      velocityY = S? velocityY : -velocityY;
    }

    // Update player and hitbox position
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
   * {@inheritDoc} Also sets flag to stop the player from moving until they release the movement
   * keys.
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
   * Determines whether the player is colliding with an {@link Entity} or not.
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
   * Sets the player to move in the desired direction when {@code update()} is called.
   * @param direction can be 'W', 'A', 'S', or 'D' to represent the direction by the key pressed to
   * move in that direction.
   */
  public void startMoving(char direction) {
    switch(direction) {
      case 'W':
        W = true;
        break;
      case 'A':
        A = true;
        break;
      case 'S':
        S = true;
        break;
      case 'D':
        D = true;
        break;
    }
  }

  /**
   * Stops the player from moving in a desired direciton.
   * @param direction can be 'W', 'A', 'S', or 'D' to represent the direction by the key pressed to
   * move in that direction.
   */
  public void stopMoving(char direction) {
    switch(direction) {
      case 'W':
        W = false;
        break;
      case 'A':
        A = false;
        break;
      case 'S':
        S = false;
        break;
      case 'D':
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
