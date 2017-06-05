
package snakeGame;

import java.util.ArrayList;
import java.util.Random;


public class Snake implements Runnable {
	
	// Implementation of GameScreen, snake number and alive;
	GameWindow gameWindow;
	int snake_num;
	boolean alive;
	CellList gameBoard;
	
	// snake score
	int score = 0;
	
	// KEYS MAP
	protected int direction = -1;
	protected int next_direction = -1;
	
	public final static int UP = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int RIGHT = 4;
	// the snakes body is stored as X and Y int values, instead of direct Cell references.
	// this is to keep some degree of separation between classes.
	protected ArrayList<int[]> snakeBody = new ArrayList<int[]>();
	
	/*
	 * Constructor to create Snake object
	 */
	public Snake(GameWindow gameWindow, int snake_num, CellList gameBoard) {
		this.gameWindow = gameWindow;
		this.snake_num = snake_num;
		this.gameBoard = gameBoard;
		alive = true;
		
		Random randomGenerator = new Random();
		// set snake head position
		int[] pos = gameBoard.getRandomCellwithBoundariesXY();
		snakeBody.add(new int[] { pos[1], pos[0] });
		
		// Pick a random direction for the snake to spawn facing.
		// generate the snake body piece by piece in the trailing direction form the
		// direction the snake is facing.
		int randomDirection = randomGenerator.nextInt(3) + 1;
		if (randomDirection == 1) {
			snakeBody.add(new int[] { pos[1] + 1, pos[0] });
			snakeBody.add(new int[] { pos[1] + 2, pos[0] });
			this.direction = UP;
			this.next_direction = UP;
			
		} else if (randomDirection == 2) {
			snakeBody.add(new int[] { pos[1] - 1, pos[0] });
			snakeBody.add(new int[] { pos[1] - 2, pos[0] });
			this.direction = DOWN;
			this.next_direction = DOWN;
			
		} else if (randomDirection == 3) {
			snakeBody.add(new int[] { pos[1], pos[0] + 1 });
			snakeBody.add(new int[] { pos[1], pos[0] + 2 });
			this.direction = LEFT;
			this.next_direction = LEFT;
			
		} else if (randomDirection == 4) {
			snakeBody.add(new int[] { pos[1], pos[0] - 1 });
			snakeBody.add(new int[] { pos[1], pos[0] - 2 });
			this.direction = RIGHT;
			this.next_direction = RIGHT;
		}
		setSnake();
	}
	
	/*
	 * set the Cell under the new HEAD position to be 'in Use'. Set the colour of the new Head Cell.
	 */
	public void setSnake() {
		gameBoard.getCell(snakeBody.get(0)[0], snakeBody.get(0)[1]).beingUsed();
		gameBoard.getCell(snakeBody.get(0)[0], snakeBody.get(0)[1]).setColor(snake_num);
		for (int i = 1; i < snakeBody.size(); i++) {
			gameBoard.getCell(snakeBody.get(i)[0], snakeBody.get(i)[1]).beingUsed();
		}
	}
	
	/*
	 * remove all pieces from the snakes body.
	 */
	public void killSnake() {
		for (int i = 0; i < snakeBody.size(); i++) {
			gameBoard.getCell(snakeBody.get(i)[0], snakeBody.get(i)[1]).isLeaving();
		}
	}
	
	public int getLength() {
		return snakeBody.size();
	}
	
	/*
	 * Check if the snake's new head is Out Of Bounds.
	 */
	public void checkOOB() {
		if (snakeBody.get(0)[0] == 0 || snakeBody.get(0)[0] == 100 || snakeBody.get(0)[1] == 0
				|| snakeBody.get(0)[1] == 100) {
			alive = false;
		}
	}
	
	/*
	 * Check if the snake's new Head Cell is already being used.
	 */
	public void checkCollision() {
		if (gameBoard.getCell(snakeBody.get(0)[0], snakeBody.get(0)[1]).isEmpty() == false) {
			alive = false;
		}
	}
	
	public void move(int move) {
		// last node go to second last node spot
		boolean ateFood = false;
		int[] head = snakeBody.get(0);
		int[] newHead = new int[2];
		int[] tailTip = snakeBody.get(snakeBody.size() - 1);
		int tipIndex = snakeBody.size();
		
		if ((move == 1 && direction != DOWN) || next_direction == UP) {
			this.direction = UP;
			newHead = new int[] { head[0] - 1, head[1] };
		} else if ((move == 2 && direction != UP) || next_direction == DOWN) {
			this.direction = DOWN;
			newHead = new int[] { head[0] + 1, head[1] };
		} else if ((move == 3 && direction != RIGHT) || next_direction == LEFT) {
			this.direction = LEFT;
			newHead = new int[] { head[0], head[1] - 1 };
		} else if ((move == 4 && direction != LEFT) || next_direction == RIGHT) {
			this.direction = RIGHT;
			newHead = new int[] { head[0], head[1] + 1 };
		}
		
		// ====================== MOVE CODE ==========================
		snakeBody.add(0, new int[] { newHead[0], newHead[1] });
		checkOOB();
		checkCollision();
		if (alive == true) {
			if (!gameBoard.getCell(newHead[0], newHead[1]).hasFood()) {
				// set last cell to leaving
				gameBoard.getCell(tailTip).isLeaving();
				snakeBody.remove(tipIndex);
			} else {
				ateFood = true;
			}
		} else {
			snakeBody.remove(0);
		}
		// ===========================================================
		
		while (ateFood) {
			Cell cell = gameBoard.getRandomCellwithBoundaries();
			if (cell.isEmpty() && !(cell.hasFood())) {
				cell.placeFood();
				this.score += 50;
				ateFood = false;
			}
		}
		if (alive == false) {
			killSnake();
		} else {
			setSnake();
		}
		
	}
	
	@Override
	public void run() {
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		if (this.snake_num == 1) {
			return "Player 1 Wins";
		}
		if (this.snake_num == 2) {
			return "Player 2 Wins";
		}
		if (this.snake_num == 3) {
			return "Player 3 Wins";
		}
		if (this.snake_num == 4) {
			return "Player 4 Wins";
		}
		return "Snake AI Wins";
	}
}
