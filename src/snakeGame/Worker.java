package snakeGame;

public class Worker implements Runnable {
	private Buffer buffer;
	private Snake player;
	public Worker(Buffer c,Snake aPlayer) {
		buffer = c;
		this.player = aPlayer;
	}
	public void run() {
			int v = buffer.take();
			player.move(v);
		} 
}
