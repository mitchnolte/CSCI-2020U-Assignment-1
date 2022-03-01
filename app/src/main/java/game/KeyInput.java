package game;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import entities.Player;


/**
 * {@link KeyListener} object to control the game.
 */
public class KeyInput implements KeyListener {
  
  private GameScreen game;
  private Player player;


  /**
   * Constructor to create the {@code KeyInput} object.
   * @param game the {@link GameScreen} this object is controlling.
   * @param player the {@link entities.Player player} this object is controlling.
   */
  public KeyInput(GameScreen game, Player player) {
    this.game = game;
    this.player = player;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void keyPressed(KeyEvent e) {
    int k = e.getKeyCode();
    if(k == KeyEvent.VK_W) {
      player.startMoving('W');
    }
    else if(k == KeyEvent.VK_A) {
      player.startMoving('A');
    }
    else if(k == KeyEvent.VK_S) {
      player.startMoving('S');
    }
    else if(k == KeyEvent.VK_D) {
      player.startMoving('D');
    }
    else if(k == KeyEvent.VK_ESCAPE) {
      game.togglePause();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyReleased(KeyEvent e) {
    int k = e.getKeyCode();
    if(k == KeyEvent.VK_W) {
      player.stopMoving('W');
      player.canMove();
    }
    else if(k == KeyEvent.VK_A) {
      player.stopMoving('A');
      player.canMove();
    }
    else if(k == KeyEvent.VK_S) {
      player.stopMoving('S');
      player.canMove();
    }
    else if(k == KeyEvent.VK_D) {
      player.stopMoving('D');
      player.canMove();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyTyped(KeyEvent e) {}
}
