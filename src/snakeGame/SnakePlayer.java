package snakeGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakePlayer extends Snake implements KeyListener {
	
	//button press array
	int[] keys;
	
	private Buffer buffer;
	
	
	/*
	 * Constructor for the Snake Player. Need to have the Gamescreen,
	 * the snake number(for testing Purpose) and the keys it will use
	 * to move the snake.
	 */
	public SnakePlayer(gameWindow gameW, int snake_num, int[] keys, Buffer playerBuffer) {
		super(gameW, snake_num);
		this.keys = keys;
		// TODO Auto-generated constructor stub
		buffer = playerBuffer;
	}
	
	/*
	 * (non-Javadoc)
	 * @see snakeGame.Snake#run()
	 */
	public void run() {
		System.out.println(Thread.currentThread().getName() + " thread started");
		gameW.getMainFrame().addKeyListener(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		//listen for the key press
		int code = e.getKeyCode();
		
		//if key press is Up and currently no facing down then move snake up
		if (code == keys[0]) {
			
			if (direction != DOWN) {
				buffer.append(code);
				next_direction = UP;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}
		
			//if key press is Down and current direction is not up then move down
		} else if (code == keys[1]) {
			if (direction != UP) {
				buffer.append(code);
				next_direction = DOWN;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}
			
			//If key press is left and snake not facing right then move left
		} else if (code == keys[2]) {
			if (direction != RIGHT) {
				buffer.append(code);
				next_direction = LEFT;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}
			
			//If key press is right and snake not facing left then move right
		} else if (code == keys[3]) {
			if (direction != LEFT) {
				buffer.append(code);
				next_direction = RIGHT;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}
			
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
