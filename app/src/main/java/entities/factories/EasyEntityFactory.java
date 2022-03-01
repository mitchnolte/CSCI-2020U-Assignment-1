package entities.factories;

import entities.*;
import entities.enemies.EasyEnemy;
import game.Main;


/**
 * Implentation of {@link EntityFactory} interface to create the {@link entities.Entity entities}
 * required for easy mode.
 */
public class EasyEntityFactory implements EntityFactory {

  /**
   * Factory method to create all the {@link entities.Entity entities} required for easy mode.
   * 
   * @return The entities in an array. The first element is the {@link entities.Player player}, the
   * second is the {@link entities.WinTile win tile}, and the rest are {@link entities.enemies.EasyEnemy enemies}.
   */
  @Override
  public Entity[] getEntities() {

    // Initialize entity array, player entity and win tile entity
    Entity[] entities = new Entity[NUM_ENEMIES + 2];
    entities[0] = new Player(PLAYER_START_POINT, PLAYER_START_POINT, PLAYER_WIDTH, PLAYER_HEIGHT,
                             PLAYER_MOVE_SPEED);
    entities[1] = new WinTile(Main.FRAME_WIDTH - 3*TILE_SIZE, Main.FRAME_HEIGHT - 2*TILE_SIZE,
                              3*TILE_SIZE, 2*TILE_SIZE);

    // Create a grid of enemies with alternating movement directions per row
    for(int i=0; i<NUM_ENEMY_ROWS; i++) {
      for(int j=0; j<NUM_ENEMY_COLS; j++) {

        // Calculate positions and velocities
        int x = j * ENEMY_SPACING_X;
        int y = (i+1) * ENEMY_SPACING_y + ENEMY_TILE_OFFSET;
        int speed = ENEMY_MOVE_SPEED * (int)Math.pow(-1, i);
        if(i%2 != 0) {x += ENEMY_TRAVEL_DISTANCE;}

        // Create enemy
        entities[j+2 + i*NUM_ENEMY_COLS] = new EasyEnemy(x, y, ENEMY_DIAMETER, speed,
                                                         ENEMY_TRAVEL_DISTANCE);
      }
    }

    return entities;
  } 
}
