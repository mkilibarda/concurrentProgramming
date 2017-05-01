package snakeGame;


public class Snake implements Runnable {
	//Implementation of GameScreen, snake number and alive;
	gameWindow gameW;
	int snake_num;
	boolean alive;

	// KEYS MAP
	protected int direction = -1;
	protected int next_direction = -1;

	public final static int UP = 0;
	public final static int DOWN = 1;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;

	/*
	 * Constructor to create Snake object
	 */
	public Snake(gameWindow gameW2, int snake_num) {
		//		this.server = server;
		this.gameW = gameW;
		this.snake_num = snake_num;
		alive = true;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " thread started");
	}


}
