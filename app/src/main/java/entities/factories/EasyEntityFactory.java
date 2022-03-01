package entities.factories;

import entities.*;
import entities.enemies.EasyEnemy;
import game.Main;


/**
 * Implentation of the EntityFactory interface to create the entities required for easy mode.
 * @see EntityFactory
 * @see entities.Entity
 * @see entities.enemies.EasyEnemy
 */
public class EasyEntityFactory implements EntityFactory {

  /**
   * Factory method to create all the entities required for easy mode.
   * 
   * @return The entities in an array. The first element is the player, the second is the win tile,
   * and the rest are enemies.
   * @see entities.Player
   * @see entities.WinTile
   * @see entities.enemies.EasyEnemy
   */
  @Override
  public Entity[] getEntities() {

    // Initialize entity array, player entity and win tile entity
    Entity[] entities = new Entity[NUM_ENEMIES + 2];
    entities[0] = new Player(PLAYER_START_POINT, PLAYER_START_POINT, PLAYER_WIDTH, PLAYER_HEIGHT,
                             MOVE_SPEED);
    entities[1] = new WinTile(Main.FRAME_WIDTH - 3*TILE_SIZE, Main.FRAME_HEIGHT - 2*TILE_SIZE,
                              3*TILE_SIZE, 2*TILE_SIZE);

    // Create a grid of enemies with alternating movement directions per row
    for(int i=0; i<NUM_ENEMY_ROWS; i++) {
      for(int j=0; j<NUM_ENEMY_COLS; j++) {

        // Calculate positions and velocities
        int x = j * ENEMY_SPACING_X;
        int y = (i+1) * ENEMY_SPACING_y + ENEMY_TILE_OFFSET;
        int speed = MOVE_SPEED * (int)Math.pow(-1, i);
        if(i%2 != 0) {x += ENEMY_TRAVEL_DISTANCE;}

        // Create enemy
        entities[j+2 + i*NUM_ENEMY_COLS] = new EasyEnemy(x, y, ENEMY_DIAMETER, speed,
                                                         ENEMY_TRAVEL_DISTANCE);
      }
    }

    return entities;
  } 
}
