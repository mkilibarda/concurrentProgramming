package snakeGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Snake implements KeyListener, Runnable {
//	Server server;
	GameScreen ui;
	int[] keys;
	int snake_num;
	
	// KEYS MAP
	private int direction = -1;
	private int next_direction = -1;
	
	public final static int UP = 0;
	public final static int DOWN = 1;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;
	
	public Snake(GameScreen ui, int[] keys, int snake_num) {
//		this.server = server;
		this.ui = ui;
		this.keys = keys;
		this.snake_num = snake_num;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " thread started");
		ui.getMainFrame().addKeyListener(this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		// System.out.println(code);
		// Dimension dim;
		// System.out.println("Key pressed" + ke.toString());
		
		// paused = false;
		
		if (code == keys[0]) {
			if (direction != DOWN) {
				next_direction = UP;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}
			
		} else if (code == keys[1]) {
			if (direction != UP) {
				next_direction = DOWN;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}
		} else if (code == keys[2]) {
			if (direction != RIGHT) {
				next_direction = LEFT;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}
		} else if (code == keys[3]) {
			if (direction != LEFT) {
				next_direction = RIGHT;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
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
