package snakeGame;

public class Server {
	//TODO: THREADPOOL FOR AI
	//TODO: THREADPOOL FOR PLAYERS
	//TODO: HANDLE GAME SCREEN
	

	// movement schemes
	// in order of UP / DOWN / LEFT / RIGHT
	private int[] arrowKeys = new int[] { 38, 40, 37, 39 };
	private int[] wasd = new int[] { 87, 83, 65, 68 };
	
	public Server() {
		GameScreen ui = new GameScreen();
		Thread thread1 = new Thread(new Snake( ui, arrowKeys, 1));
		Thread thread2 = new Thread(new Snake(ui, wasd, 2));
		thread1.start();
		thread2.start();
	}

	public static void main(String[] args) {
		Server server = new Server();
	}
}