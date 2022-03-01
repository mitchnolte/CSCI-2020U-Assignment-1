package entities.factories;

import java.util.Random;
import entities.*;
import entities.enemies.*;
import game.Main;


/**
 * Implentation of the EntityFactory interface to create the entities required for hard mode.
 * @see EntityFactory
 * @see entities.Entity
 * @see entities.enemies.HardEnemy
 */
public class HardEntityFactory implements EntityFactory {

  private Random rand = new Random();


  /**
   * Factory method to create all the entities required for hard mode.
   * 
   * @return The entities in an array. The first element is the player, the second is the win tile.
   * Of the rest, the first half are enemies and the second half are their projectiles.
   * @see entities.Player
   * @see entities.WinTile
   * @see entities.enemies.EasyEnemy
   */
  @Override
  public Entity[] getEntities() {
    // Initialize entity array, player entity and win tile entity
    Entity[] entities = new Entity[2*NUM_ENEMIES + 2];
    entities[0] = new Player(PLAYER_START_POINT, PLAYER_START_POINT, PLAYER_WIDTH, PLAYER_HEIGHT,
                             MOVE_SPEED);
    entities[1] = new WinTile(Main.FRAME_WIDTH - 3*TILE_SIZE, Main.FRAME_HEIGHT - 2*TILE_SIZE,
                              3*TILE_SIZE, 2*TILE_SIZE);
    

    // Create a grid of enemies with alternating circular movement patterns and a projectile for
    // each enemy
    for(int i=0; i<NUM_ENEMY_ROWS; i++) {
      for(int j=0; j<NUM_ENEMY_COLS; j++) {
        
        // Calculate positions and velocities
        int x = j * ENEMY_SPACING_X + Main.FRAME_WIDTH / 32;
        int y = (i+1) * ENEMY_SPACING_y + ENEMY_TILE_OFFSET;
        int velocityX = MOVE_SPEED * (int)Math.pow(-1, i);
        int velocityY = velocityX;
        if(j%2 == 1) velocityY *= -1;
        if(i%2 != 0) {x += ENEMY_TRAVEL_DISTANCE/1.5f;}

        // Create enemy's projectile
        float projVelMultiplierX = getProjectileVelocityMultiplier();
        float projVelMultiplierY = getProjectileVelocityMultiplier();
        EnemyProjectile projectile = new EnemyProjectile(PROJECTILE_DIAMETER, projVelMultiplierX,
                                                         projVelMultiplierY);
        entities[NUM_ENEMIES+2 + j+i*NUM_ENEMY_COLS] = projectile;

        // Create enemy
        entities[j+2 + i*NUM_ENEMY_COLS] = new HardEnemy(x, y, ENEMY_DIAMETER, velocityX,
                                                         velocityY, projectile);
      }
    }

    return entities;
  }


  /**
   * Genereates a random float in the range [0.75, 2.25] to be used as a velocity multiplier for
   * a projectile.
   * @return The random float.
   */
  private float getProjectileVelocityMultiplier() {
    return (rand.nextFloat() + 0.5f) * 1.5f;
  }
}
