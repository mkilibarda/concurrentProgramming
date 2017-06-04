
package snakeGame;

import java.util.Random;


public class SnakeAI extends Snake {
	
	public SnakeAI(GameWindow gameWindow, int snake_num, CellList gameBoard) {
		super(gameWindow, snake_num, gameBoard);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Will decide which way the AI snake will move
	 */
	public void run() {
		if (alive == true) {
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(7);
			
			// if key press is Up and currently no facing down then move snake up
			if (randomInt == 1) {
				if (direction != DOWN) {
					// Check if the next move to UP is hitting edge of gameBoard
					if (snakeBody.get(0)[0] - 1 == 0) {
						// Check if right is not free
						if (snakeBody.get(0)[1] + 1 == 99) {
							// If its not, then move left
							next_direction = LEFT;
							randomInt = 3;
						}
						// Else move right
						next_direction = RIGHT;
						randomInt = 4;
					} else {
						next_direction = UP;
					}
				}
				
				// if key press is Down and current direction is not up then move down
			} else if (randomInt == 2) {
				if (direction != UP) {
					if (snakeBody.get(0)[0] + 1 == 99) {
						if (snakeBody.get(0)[1] - 1 == 0) {
							next_direction = LEFT;
							randomInt = 3;
						}
						next_direction = RIGHT;
						randomInt = 4;
					} else {
						next_direction = DOWN;
					}
				}
				
				// If key press is left and snake not facing right then move left
			} else if (randomInt == 3) {
				if (direction != RIGHT) {
					if (snakeBody.get(0)[1] - 1 == 0) {
						if (snakeBody.get(0)[0] + 1 == 99) {
							next_direction = UP;
							randomInt = 1;
						}
						next_direction = DOWN;
						randomInt = 2;
					} else {
						next_direction = LEFT;
					}
				}
				
				// If key press is right and snake not facing left then move right
			} else if (randomInt == 4) {
				if (direction != LEFT) {
					// Check if the next move to the right is the wall
					if (snakeBody.get(0)[1] + 1 == 99) {
						// If above is border, go down
						if (snakeBody.get(0)[0] - 1 == 0) {
							next_direction = DOWN;
							randomInt = 2;
						}
						// Else go up
						next_direction = UP;
						randomInt = 1;
					} else {
						next_direction = RIGHT;
					}
				}
				
			} else {
				if (direction == UP) {
					// Check if the next move to UP is hitting edge of gameBoard
					if (snakeBody.get(0)[0] - 1 == 0) {
						// Check if right is not free
						if (snakeBody.get(0)[1] + 1 == 99) {
							// If its not, then move left
							next_direction = LEFT;
							randomInt = 3;
						}
						// Else move right
						next_direction = RIGHT;
						randomInt = 4;
					}
					
				} else if (direction == DOWN) {
					if (snakeBody.get(0)[0] + 1 == 99) {
						if (snakeBody.get(0)[1] - 1 == 0) {
							next_direction = LEFT;
							randomInt = 3;
						}
						next_direction = RIGHT;
						randomInt = 4;
					}
				} else if (direction == LEFT) {
					if (snakeBody.get(0)[1] - 1 == 0) {
						if (snakeBody.get(0)[0] + 1 == 99) {
							next_direction = UP;
							randomInt = 1;
						}
						next_direction = DOWN;
						randomInt = 2;
					}
				} else {
					// Check if the next move to the right is the wall
					if (snakeBody.get(0)[1] + 1 == 99) {
						// If above is border, go down
						if (snakeBody.get(0)[0] - 1 == 0) {
							next_direction = DOWN;
							randomInt = 2;
						}
						// Else go up
						next_direction = UP;
						randomInt = 1;
					}
				}
			}
			
			this.move(randomInt);
		}
	}
}
