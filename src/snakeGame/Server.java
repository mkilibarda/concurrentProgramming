
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
	int[] playerRegister = new int[] { 0, 0, 0, 0 };
	// private Thread[] currentRealPlayers = new Thread[4];
	// The amount of AI Players
	int AIPlayers;
	List<SnakeAI> comp = new ArrayList<SnakeAI>();
	List<Snake> players = new ArrayList<Snake>();
	List<Worker> workers = new ArrayList<Worker>();
	
	// ThreadPool for the AI snakes
	ThreadPool aiPool;
	ThreadPool workerPool = new ThreadPool(4, 4);
	
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
	scoreWndow scoreScreen;
	
	/*
	 * Constructor to create a sever for the game. Handle most of the games activities. Passes the number of AI needed.
	 */
	public Server() throws Exception {
		
		// Create gameWindow
		gameW = new gameWindow(this);
		scoreScreen = new scoreWndow(this);
		logInW = new logInWindow(this);
		
		// create all players
		// createSnakes(gameW, AIPlayers);
	}
	
	public void startGame(int AIPlayers) {
		createSnakeAI(AIPlayers);
		scoreScreen.showScoreWindow();
		gameW.showGameWindow();
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (i == 0 || j == 0 || i == 99 || j == 99) gameScreen.getCell(i, j).beingUsed();;
				
			}
		}
		
		// Every second allow the AI to make a decision on the direction to move
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				try {
					for (int i = 0; i < comp.size(); i++) {
						aiPool.execute(comp.get(i));
					}
					for (int i = 0; i < workers.size(); i++) {
						workerPool.execute(workers.get(i));
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				scoreScreen.returnScore();
				gameW.displayCellList();
				
				// in when just have 1 snake
				if (checkLastPlayer() != null) {
					System.out.println(checkLastPlayer().toString());
					t.cancel();
					
				}
			}
		}, 0, 250);
	}
	
	public void addSnakePlayer(int playerNumber) {
		if (playerRegister[playerNumber - 1] == 0) {
			Buffer playerBuf = new Buffer(1);
			playerBuffer.add(playerBuf);
			Snake player = new SnakePlayer(gameW, playerNumber, gameScreen, keyschemas[playerNumber - 1], playerBuf);
			players.add(player);
			Thread snakePlayer = new Thread(player);
			workers.add(new Worker(playerBuf, player));
			// currentRealPlayers[playerNumber-1] = snakePlayer;
			snakePlayer.start();
			playerRegister[playerNumber - 1] = 1;
		}
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
			comp.add(new SnakeAI(gameW, i + 1, gameScreen));
		}
		
	}
	
	// return the last snake if have
	Snake checkLastPlayer() {
		// see how many snake
		int aliveSnake = 0;
		int alivePlayers = 0;
		int aliveAI = 0;
		
		for (int i = 0; i < comp.size(); i++) {
			if (comp.get(i).alive) {
				aliveSnake++;
				alivePlayers++;
			}
		}
		for (int j = 0; j < this.players.size(); j++) {
			if (players.get(j).alive) {
				aliveSnake++;
				aliveAI++;
			}
		}
		// more than 1 snake return null
		if (aliveSnake > 1) {
			return null;
		}
		// 1 snake return that snake
		if (aliveSnake == 1) {
			for (int j = 0; j < this.players.size() - 1; j++) {
				if (players.get(j).alive) {
					return players.get(j);
				}
			}
			for (int i = 0; i < comp.size(); i++) {
				if (comp.get(i).alive) {
					return comp.get(i);
				}
			}
		}
		
		return null;
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
