package snakeGame;

import java.util.ArrayList;

public class Snake implements Runnable {
	// Implementation of GameScreen, snake number and alive;
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
	protected ArrayList<int[]> snakeBody = new ArrayList<int[]>();

	/*
	 * Constructor to create Snake object
	 */
	public Snake(gameWindow gameW, int snake_num) {
		// this.server = server;
		this.gameW = gameW;
		this.snake_num = snake_num;
		alive = true;

		// allLocation.add(new int[2] = {100,100});
	}
	
	public int getLength() {
		return snakeBody.size();
	}

	public void grow() {
		// create 1more array behind same direction as last one
		snakeBody.add(new int[] { snakeBody.get(getLength() - 1)[0], snakeBody.get(getLength() - 1)[1] });
		// ArrayList<Integer > tem=new ArrayList< Integer>();
		// tem.add(allLocation.get(allLocation.size()-1).get(0));
		// tem.add(allLocation.get(allLocation.size()-1).get(1));
	}
 
	
	
	public void move(int x, int y) {
		// last node go to second last node spot
		for (int i = 0; i < this.snakeBody.size() - 1; i++) {
			// at last one berak;
			// if(){
			// break;
			// }
			snakeBody.set(snakeBody.size() - 1 - i, snakeBody.get(snakeBody.size() - i));
		}
		// first one become
		ArrayList<Integer> tem = new ArrayList<Integer>();
		tem.add(x);
		tem.add(y);
		//snakeBody.set(0, tem);
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " thread started");
	}

}