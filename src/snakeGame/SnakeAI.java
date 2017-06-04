package snakeGame;

import java.util.Random;


public class SnakeAI extends Snake {
	private boolean running = true;

	/*
	 * Constructor for the Snake AI. Need to have the Gamescreen, the snake number(for testing Purpose).
	 */
	public SnakeAI(GameWindow gameW, int snake_num,CellList map) {
		super(gameW, snake_num, map);
		// TODO Auto-generated constructor stub
	}

	/*
	 * Will decide which way the AI snake will move
	 * 
	 * (non-Javadoc)
	 * 
	 * @see snakeGame.Snake#run()
	 */
	public void run() {
		// //System.out.println(Thread.currentThread().getName() + " thread started");
		//delay();
		if(alive == true){
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(7);

			// if key press is Up and currently no facing down then move snake up
			if (randomInt == 1) {
				if (direction != DOWN) {
					// Check if the next move to UP is hitting edge of gameBoard
					if(snakeBody.get(0)[0] - 1 == 0){
						// Check if right is not free
						//System.out.println("found wall");
						if(snakeBody.get(0)[1] + 1 == 99){
							// If its not, then move left
							next_direction = LEFT;
							randomInt = 3;
							//System.out.println("found wall");
						}
						// Else move right 
						next_direction = RIGHT;
						randomInt = 4;
					}else{
						next_direction = UP;
						//System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
					}
				}

				// if key press is Down and current direction is not up then move down
			} else if (randomInt == 2) {
				if (direction != UP) {
					if(snakeBody.get(0)[0] + 1 == 99){
						//System.out.println("found wall");
						if(snakeBody.get(0)[1] - 1 == 0){
							//System.out.println("found wall");
							next_direction = LEFT;
							randomInt = 3;
						}
						next_direction = RIGHT;
						randomInt = 4;
					}else{
						next_direction = DOWN;
						//System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
					}
				}

				// If key press is left and snake not facing right then move left
			} else if (randomInt == 3) {
				if (direction != RIGHT) {
					if(snakeBody.get(0)[1] - 1 == 0){
						//System.out.println("found wall");
						if(snakeBody.get(0)[0] + 1 == 99){
							//System.out.println("found wall");
							next_direction = UP;
							randomInt = 1;
						}
						next_direction = DOWN;
						randomInt = 2;
					}else{
						next_direction = LEFT;
						//System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
					}
				}

				// If key press is right and snake not facing left then move right
			} else if (randomInt == 4) {
				if (direction != LEFT) {
					// Check if the next move to the right is the wall
					if(snakeBody.get(0)[1] + 1 == 99){
						//System.out.println("found wall");
						// If above is border, go down
						if(snakeBody.get(0)[0] - 1 == 0){
							//System.out.println("found wall");
							next_direction = DOWN;
							randomInt = 2;
						}
						// Else go up 
						next_direction = UP;
						randomInt = 1;
					}else{
						next_direction = RIGHT;
						//System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
					}
				}

			} else {
				if(direction == UP){
					// Check if the next move to UP is hitting edge of gameBoard
					if(snakeBody.get(0)[0] - 1 == 0){
						// Check if right is not free
						//System.out.println("found wall");
						if(snakeBody.get(0)[1] + 1 == 99){
							// If its not, then move left
							next_direction = LEFT;
							randomInt = 3;
							//System.out.println("found wall");
						}
						// Else move right 
						next_direction = RIGHT;
						randomInt = 4;
					}

				}else if(direction == DOWN){
					if(snakeBody.get(0)[0] + 1 == 99){
						//System.out.println("found wall");
						if(snakeBody.get(0)[1] - 1 == 0){
							//System.out.println("found wall");
							next_direction = LEFT;
							randomInt = 3;
						}
						next_direction = RIGHT;
						randomInt = 4;
					}
				}else if(direction == LEFT){
					if(snakeBody.get(0)[1] - 1 == 0){
						//System.out.println("found wall");
						if(snakeBody.get(0)[0] + 1 == 99){
							//System.out.println("found wall");
							next_direction = UP;
							randomInt = 1;
						}
						next_direction = DOWN;
						randomInt = 2;
					}
				}else{
					// Check if the next move to the right is the wall
					if(snakeBody.get(0)[1] + 1 == 99){
						//System.out.println("found wall");
						// If above is border, go down
						if(snakeBody.get(0)[0] - 1 == 0){
							//System.out.println("found wall");
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

	/*
	 * Delay function to lengthen the decision-making time of each snake very slightly.
	 */
	private void delay() {
		int actualDelay;
		try {
			// thread to sleep for random milliseconds
			actualDelay = randomWithRange(100, 300);
			// //System.out.println("Actual delay is "+actualDelay);
			Thread.sleep(actualDelay);
		}
		catch (Exception e) {
			//System.out.println(e);
		}
	}

	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}
