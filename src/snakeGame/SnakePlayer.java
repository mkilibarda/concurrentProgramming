
package snakeGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakePlayer extends Snake implements KeyListener {
	
	// button press array
	int[] keys;
	private Buffer buffer;
	
	/*
	 * Constructor for the Snake Player.
	 */
	public SnakePlayer(GameWindow gameWindow, int snake_num, CellList gameBoard, int[] keys, Buffer playerBuffer) {
		super(gameWindow, snake_num, gameBoard);
		this.keys = keys;
		buffer = playerBuffer;
	}
	
	/*
	 * adds a key listener to the gameWindow, to receive player input.
	 */
	public void run() {
		if (alive == true) {
			gameWindow.getMainFrame().addKeyListener(this);
		}
	}
	
	/*
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// listen for the key press
		if (alive == true) {
			int code = e.getKeyCode();
			
			// if key press is Up and currently no facing down then move snake up
			if (code == keys[0]) {
				
				if (direction != DOWN) {
					buffer.append(1);
					next_direction = UP;
				}
				
				// if key press is Down and current direction is not up then move down
			} else if (code == keys[1]) {
				if (direction != UP) {
					buffer.append(2);
					next_direction = DOWN;
				}
				
				// If key press is left and snake not facing right then move left
			} else if (code == keys[2]) {
				if (direction != RIGHT) {
					buffer.append(3);
					next_direction = LEFT;
				}
				
				// If key press is right and snake not facing left then move right
			} else if (code == keys[3]) {
				if (direction != LEFT) {
					buffer.append(4);
					next_direction = RIGHT;
				}
				
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
