package snakeGame;

public class Worker implements Runnable {
	private Buffer buffer;
	public Worker(Buffer c) {
		buffer = c;
	}
	public void run() {
			int v = buffer.take();
		} 
}
