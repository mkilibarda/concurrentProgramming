
package snakeGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Server {
	public CellList gameScreen = new CellList(100, 100);
	List<Buffer> playerBuffer = new ArrayList<Buffer>();
	
	// the amount of real players
	int realPlayers = 4;
//	private Thread[] currentRealPlayers = new Thread[4];
	// The amount of AI Players
	int AIPlayers;
	List<SnakeAI> comp = new ArrayList<SnakeAI>();
	List<Worker> workers = new ArrayList<Worker>();
	
	// ThreadPool for the AI snakes
	ThreadPool aiPool;
	ThreadPool workerPool = new ThreadPool(4,4);
	
	// movement schemes
	// in order of UP / DOWN / LEFT / RIGHT.
	private int[][] keyschemas = new int[][] { { 38, 40, 37, 39 }, { 87, 83, 65, 68 }, { 89, 72, 71, 74 },
			{ 80, 59, 76, 222 } };
	// private int[] arrowKeys = new int[] { 38, 40, 37, 39 };
	// private int[] wsad = new int[] { 87, 83, 65, 68 };
	// private int[] yhgj = new int[] { 89, 72, 71, 74 };
	// private int[] plAndSymbols = new int[] { 80, 59, 76, 222 };
	
	gameWindow gameW;
	logInWindow logInW;
	
	/*
	 * Constructor to create a sever for the game. Handle most of the games activities. Passes the number of AI needed.
	 */
	public Server() throws Exception {
		
		// Create gameWindow
		gameW = new gameWindow(this);
		logInW = new logInWindow(this);
		
		// create all players
		// createSnakes(gameW, AIPlayers);
	}
	
	public void startGame(int AIPlayers) {
		createSnakeAI(AIPlayers);
		gameW.showGameWindow();
		
		// Every second allow the AI to make a decision on the direction to move
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				try {
					for (int i = 0; i < comp.size(); i++) {
						aiPool.execute(comp.get(i));
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				try {
					for (int i = 0; i < workers.size(); i++) {
						workerPool.execute(workers.get(i));
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 0, 1000);
	}
	
	public void addSnakePlayer(int playerNumber) {
		Buffer playerBuf = new Buffer(1);
		playerBuffer.add(playerBuf);
		Snake player = new SnakePlayer(gameW, playerNumber, keyschemas[playerNumber-1],playerBuf);
		Thread snakePlayer = new Thread(player);
		workers.add(new Worker(playerBuf));
//		currentRealPlayers[playerNumber-1] = snakePlayer;
		snakePlayer.start();
	}
	
	/*
	 * Method to create all snakes and get them running.
	 */
	public void createSnakeAI(int AIPlayers) {
		
		// creates and populates a thread pool for AI, as 100 running threads for AI would be too much overhead,
		// so simulating this with a pool that does 10 at a time is acceptable.
		aiPool = new ThreadPool(10, AIPlayers);
		// thread numbers start at 5 to accommodate for the (currently) 4 real-players
		for (int i = realPlayers; i < AIPlayers + realPlayers; i++) {
			comp.add(new SnakeAI(gameW, i + 1));
		}
		
		
	}
	
	/**
	 * Main method to implement and run the game
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Server server = new Server();
	}
}
