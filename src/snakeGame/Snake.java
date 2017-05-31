package snakeGame;

import java.util.ArrayList;

public class Snake implements Runnable {
	// Implementation of GameScreen, snake number and alive;
	gameWindow gameW;
	int snake_num;
	boolean alive;
	CellList screen;
	// KEYS MAP
	protected int direction = -1;
	protected int next_direction = -1;

	public final static int UP = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int RIGHT = 4;
	protected ArrayList<int[]> snakeBody = new ArrayList<int[]>();

	/*
	 * Constructor to create Snake object
	 */
	public Snake(gameWindow gameW, int snake_num, CellList map) {
		// this.server = server;
		this.gameW = gameW;
		this.snake_num = snake_num;
		alive = true;
		screen = map;
		int randomNum = 0 + (int)(Math.random() * 99); 
		snakeBody.add(new int[]{randomNum,3});
		snakeBody.add(new int[]{randomNum,2});
		snakeBody.add(new int[]{randomNum,1});
		this.direction = RIGHT;
		this.next_direction = RIGHT;
		setSnake();

		// allLocation.add(new int[2] = {100,100});
	}
	public void setSnake(){
		screen.getCell(snakeBody.get(0)[0],snakeBody.get(0)[1]).beingUsed();
		screen.getCell(snakeBody.get(0)[0],snakeBody.get(0)[1]).setColor(snake_num);
		for(int i = 1; i <snakeBody.size();i++){
			screen.getCell(snakeBody.get(i)[0],snakeBody.get(i)[1]).beingUsed();
		}
	}
	public void killSnake(){
		for(int i = 0; i <snakeBody.size();i++){
			screen.getCell(snakeBody.get(i)[0],snakeBody.get(i)[1]).isLeaving();
		}
	}

	public int getLength() {
		return snakeBody.size();
	}

	public void grow() {
		// create 1more array behind same direction as last one
		//snakeBody.add(new int[] { snakeBody.get(getLength() - 1)[0], snakeBody.get(getLength() - 1)[1] });
		// ArrayList<Integer > tem=new ArrayList< Integer>();
		// tem.add(allLocation.get(allLocation.size()-1).get(0));
		// tem.add(allLocation.get(allLocation.size()-1).get(1));
	}
	public void checkOOB(){
		if(snakeBody.get(0)[0] == 0 || snakeBody.get(0)[0] == 100 || snakeBody.get(0)[1] == 0 || snakeBody.get(0)[1] == 100 ){
			alive = false;
		}
	}
	public void checkCollision(){
		if(screen.getCell(snakeBody.get(0)[0],snakeBody.get(0)[1]).isEmpty() == false){
			alive = false;
		}
	}



	public void move(int move) {
		System.out.println(snakeBody.get(0)[0] + "," + snakeBody.get(0)[1]);
		// last node go to second last node spot
		System.out.println(move);
		if(move == 1 && direction != DOWN){
			//set direction of snake
			this.direction = UP;
			//move head to next cell
			snakeBody.add(0, new int[]{ snakeBody.get(0)[0] - 1,snakeBody.get(0)[1]});
			checkOOB();
			checkCollision();
			if(alive == true){
				//set last cell to leaving
				screen.getCell(snakeBody.get(snakeBody.size()-1)[0], snakeBody.get(snakeBody.size()-1)[1]).isLeaving();
				snakeBody.remove(snakeBody.size()-1);
			}else{
				snakeBody.remove(0);
			}
		}else if(move == 2 && direction != UP){
			this.direction = DOWN;
			snakeBody.add(0, new int[]{ snakeBody.get(0)[0] + 1,snakeBody.get(0)[1]});
			checkOOB();
			checkCollision();
			if(alive == true){
				screen.getCell(snakeBody.get(snakeBody.size()-1)[0], snakeBody.get(snakeBody.size()-1)[1]).isLeaving();
				snakeBody.remove(snakeBody.size()-1);
			}else{
				snakeBody.remove(0);
			}
		}else if(move == 3 && direction != RIGHT){
			this.direction = LEFT;

			snakeBody.add(0, new int[]{ snakeBody.get(0)[0],snakeBody.get(0)[1] - 1});
			checkOOB();
			checkCollision();
			if(alive == true){
				screen.getCell(snakeBody.get(snakeBody.size()-1)[0], snakeBody.get(snakeBody.size()-1)[1]).isLeaving();
				snakeBody.remove(snakeBody.size()-1);
			}else{
				snakeBody.remove(0);
			}
		}else if(move == 4 && direction != LEFT){
			this.direction = RIGHT;
			snakeBody.add(0, new int[]{ snakeBody.get(0)[0],snakeBody.get(0)[1] + 1});
			checkOOB();
			checkCollision();
			if(alive == true){
				screen.getCell(snakeBody.get(snakeBody.size()-1)[0], snakeBody.get(snakeBody.size()-1)[1]).isLeaving();
				snakeBody.remove(snakeBody.size()-1);
			}else{
				snakeBody.remove(0);
			}
		}else{
			if(next_direction == UP){
				snakeBody.add(0, new int[]{ snakeBody.get(0)[0] - 1,snakeBody.get(0)[1]});
				checkOOB();
				checkCollision();
				if(alive == true){
					screen.getCell(snakeBody.get(snakeBody.size()-1)[0], snakeBody.get(snakeBody.size()-1)[1]).isLeaving();
					snakeBody.remove(snakeBody.size()-1);
				}else{
					snakeBody.remove(0);
				}
			}else if(next_direction == DOWN){
				snakeBody.add(0, new int[]{ snakeBody.get(0)[0] + 1,snakeBody.get(0)[1]});
				checkOOB();
				checkCollision();
				if(alive == true){
					screen.getCell(snakeBody.get(snakeBody.size()-1)[0], snakeBody.get(snakeBody.size()-1)[1]).isLeaving();
					snakeBody.remove(snakeBody.size()-1);
				}else{
					snakeBody.remove(0);
				}
			}else if(next_direction == LEFT){
				snakeBody.add(0, new int[]{ snakeBody.get(0)[0],snakeBody.get(0)[1] - 1});
				checkOOB();
				checkCollision();
				if(alive == true){
					screen.getCell(snakeBody.get(snakeBody.size()-1)[0], snakeBody.get(snakeBody.size()-1)[1]).isLeaving();
					snakeBody.remove(snakeBody.size()-1);
				}else{
					snakeBody.remove(0);
				}
			}else if(next_direction == RIGHT){
				snakeBody.add(0, new int[]{ snakeBody.get(0)[0],snakeBody.get(0)[1] + 1});
				checkOOB();
				checkCollision();
				if(alive == true){
					screen.getCell(snakeBody.get(snakeBody.size()-1)[0], snakeBody.get(snakeBody.size()-1)[1]).isLeaving();
					snakeBody.remove(snakeBody.size()-1);
				}else{
					snakeBody.remove(0);
				}
			}
		}
		if(alive == false){
			killSnake();
		}else{
			setSnake();
		}
		//testing puropose
		//		for(int i = 0; i < snakeBody.size();i++){
		//			System.out.println(snakeBody.get(i)[0] + ", " + snakeBody.get(i)[1]);
		//		}

	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " thread started");
	}

}