
package snakeGame;

public class Worker implements Runnable {
	private Buffer buffer;
	private Snake player;
	
	/*
	 * Worker is run by the worker threadPool to retrieve player move data from the buffers, and
	 * then edit the game board with this move.
	 */
	public Worker(Buffer c, Snake aPlayer) {
		buffer = c;
		this.player = aPlayer;
	}
	
	// Pull the next move from the player's Buffer
	// call the Player's move() function with this next move.
	//
	// Work flow could potentially be improved here,
	// perhaps by having the move(int) code within Worker instead of Snake
	public void run() {
		if (player.alive == true) {
			int v = buffer.take();
			player.move(v);
		}
	}
}
