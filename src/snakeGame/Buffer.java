
package snakeGame;

public class Buffer {
	
	private int next_direction = -1;
	
	public Buffer() {
		
	}
	
	public synchronized void setMove(int move) {
		next_direction = move;
	}
	
	int getcurrentDirection() {
		return next_direction;
		
	}
	
}
