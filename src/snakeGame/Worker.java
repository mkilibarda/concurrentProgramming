package snakeGame;

public class Worker implements Runnable {
	private Buffer buffer;
	private Snake player;
	public Worker(Buffer c,Snake aPlayer) {
		buffer = c;
		this.player = aPlayer;
	}
	public void run() {
		if(player.alive == true){
			int v = buffer.take();
			player.move(v);
		}
	} 
}
