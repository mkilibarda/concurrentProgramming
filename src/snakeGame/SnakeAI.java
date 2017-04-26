package snakeGame;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeAI extends Snake {

	/*
	 * Constructor for the Snake AI. Need to have the Gamescreen,
	 * the snake number(for testing Purpose).
	 */
	public SnakeAI(GameScreen ui, int snake_num) {
		super(ui, snake_num);
		// TODO Auto-generated constructor stub
	}

	/*Will decide which way the AI snake will move
	 * 
	 * (non-Javadoc)
	 * @see snakeGame.Snake#run()
	 */
	public void run(){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(4);

		//if key press is Up and currently no facing down then move snake up
		if (randomInt == 0) {
			if (direction != DOWN) {
				next_direction = UP;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}

			//if key press is Down and current direction is not up then move down
		} else if (randomInt == 1) {
			if (direction != UP) {
				next_direction = DOWN;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}
			
			//If key press is left and snake not facing right then move left
		} else if (randomInt == 2) {
			if (direction != RIGHT) {
				next_direction = LEFT;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}
			
			//If key press is right and snake not facing left then move right
		} else if (randomInt == 3) {
			if (direction != LEFT) {
				next_direction = RIGHT;
				System.out.printf("Thread: snake_" + this.snake_num + " === " + next_direction + "\n");
			}

		}
	}
}
