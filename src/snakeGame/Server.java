
package snakeGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Server {
	// creates the gameBoard itself (100 x 100 cells)
	public CellList gameBoard = new CellList(100, 100);
	// keeps track of which of the 4 players have registered/logged on.
	int[] playerRegister = new int[] { 0, 0, 0, 0 };
	// The amount of AI Players
	int aiPlayerCount;
	
	// creates Lists to store and keep track of various objects throughout the game
	List<Buffer> playerBufferList = new ArrayList<Buffer>();
	List<Snake> aiPlayerList = new ArrayList<Snake>();
	List<Snake> realPlayerList = new ArrayList<Snake>();
	List<Worker> workerList = new ArrayList<Worker>();
	
	// ThreadPools for the AI snakes and workers
	ThreadPool aiPool;
	ThreadPool workerPool = new ThreadPool(4, 4);
	
	// movement schemes
	// in order of UP / DOWN / LEFT / RIGHT.
	private int[][] keyschemas = new int[][] { { 38, 40, 37, 39 }, { 87, 83, 65, 68 }, { 89, 72, 71, 74 },
			{ 80, 59, 76, 222 } };
	
	// declares the various windows used in the game.
	LoginWindow loginWindow;
	GameWindow gameWindow;
	ScoreWindow scoreWindow;
	
	// declares the Timer used as the primary Game loop.
	// Timer was used because a traditional game-loop varies in actual time
	// due to how long the code takes to complete.
	// Timer is set to 200ms, which is far longer than any cycle of the game code will ever need.
	// therefore it is safe.
	Timer t = new Timer();
	TimerTask tt;
	
	/*
	 * Constructor to create a sever for the game. Handle most of the games activities. Passes the number of AI needed.
	 */
	public Server() throws Exception {
		
		// Create gameWindow. loginWindow, and ScoreWindow
		gameWindow = new GameWindow(this);
		loginWindow = new LoginWindow(this);
		scoreWindow = new ScoreWindow(this);
	}
	
	// runs the code necessary to get the game started.
	public void startGame(int AIPlayers) {
		createSnakeAI(AIPlayers);
		// tell the gameBoard to place Food randomly on the board.
		// Food to Player ratio is 1:10, rounded up.
		gameBoard.fillInitialFood(realPlayerList.size() + aiPlayerList.size());
		scoreWindow.showScoreWindow();
		gameWindow.showGameWindow();
		
		// Every second allow the AI to make a decision on the direction to move
		// Then run the Worker thread pool to make the moves for real players.
		// Update player score screen.
		// Then check if there is either 1 snake alive, or all Players are dead.
		tt = new TimerTask() {
			public void run() {
				try {
					for (int i = 0; i < aiPlayerList.size(); i++) {
						aiPool.execute(aiPlayerList.get(i));
					}
					for (int i = 0; i < workerList.size(); i++) {
						workerPool.execute(workerList.get(i));
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				scoreWindow.returnScore();
				gameWindow.displayCellList();
				
				// Check if win conditions have been met.
				// if the game is ended, clear the timer.
				if (checkLastPlayer() != null) {
					System.out.println(checkLastPlayer().toString());
					// cancel the TimerTask itself,
					tt.cancel();
					// and then just in case, clear and purge the Timer.
					t.cancel();
					t.purge();
				}
			}
		};
		t.schedule(tt, 0, 200);
	}
	
	/*
	 * Creates a player snake for the corresponding set of controls.
	 */
	public void addSnakePlayer(int playerNumber) {
		// only create a snake if that player does not already exist.
		if (playerRegister[playerNumber - 1] == 0) {
			Buffer playerBuf = new Buffer(1);
			Snake player = new SnakePlayer(gameWindow, playerNumber, gameBoard, keyschemas[playerNumber - 1],
					playerBuf);
			Thread snakePlayer = new Thread(player);
			Worker worker = new Worker(playerBuf, player);
			
			playerBufferList.add(playerBuf);
			realPlayerList.add(player);
			workerList.add(worker);
			
			snakePlayer.start();
			playerRegister[playerNumber - 1] = 1;
		}
	}
	
	/*
	 * Method to create all AI snakes.
	 */
	public void createSnakeAI(int AIPlayers) {
		// creates and populates a thread pool for AI, as 100 running threads for AI would be too much overhead,
		// so simulating this with a pool that does 10 concurrently is acceptable.
		aiPool = new ThreadPool(10, AIPlayers);
		// thread numbers start at 5 to accommodate for the (currently) 4 real-players
		for (int i = 4; i < AIPlayers + 4; i++) {
			aiPlayerList.add(new SnakeAI(gameWindow, i + 1, gameBoard));
		}
	}
	
	/*
	 * Check the number of living AI and Player snakes. return the final snake if only 1 is alive. or return any AI snake if all players are
	 * dead. if there are 2+ players alive, or a player and AI, return null.
	 */
	Snake checkLastPlayer() {
		// see how many snakes are alive
		int aliveSnake = 0;
		int alivePlayers = 0;
		int aliveAI = 0;
		
		for (int i = 0; i < aiPlayerList.size(); i++) {
			if (aiPlayerList.get(i).alive) {
				aliveSnake++;
				aliveAI++;
			}
		}
		for (int j = 0; j < this.realPlayerList.size(); j++) {
			if (realPlayerList.get(j).alive) {
				aliveSnake++;
				alivePlayers++;
			}
		}
		// print the current info just for interest.
		System.out.println("total Snakes: " + aliveSnake);
		System.out.println("Player Snakes: " + alivePlayers);
		System.out.println("AI Snakes: " + aliveAI);
		System.out.println();
		System.out.println();
		
		if (alivePlayers > 1) {
			return null;
		}
		// if only 1 snake is alive, or if all players are dead, return (one of) the remaining snake(s)
		if (alivePlayers == 0 || aliveSnake == 1) {
			for (int j = 0; j < this.realPlayerList.size() - 1; j++) {
				if (realPlayerList.get(j).alive) {
					return realPlayerList.get(j);
				}
			}
			for (int i = 0; i < aiPlayerList.size(); i++) {
				if (aiPlayerList.get(i).alive) {
					return aiPlayerList.get(i);
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
