
package snakeGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Server {
	public CellList gameBoard = new CellList(100, 100);
	List<Buffer> playerBuffer = new ArrayList<Buffer>();
	
	//
	int[] playerRegister = new int[] { 0, 0, 0, 0 };
	// The amount of AI Players
	int AIPlayerCount;
	List<Snake> AIPlayerList = new ArrayList<Snake>();
	List<Snake> realPlayerList = new ArrayList<Snake>();
	List<Worker> workerList = new ArrayList<Worker>();
	
	// ThreadPool for the AI snakes
	ThreadPool aiPool;
	ThreadPool workerPool = new ThreadPool(4, 4);
	
	// movement schemes
	// in order of UP / DOWN / LEFT / RIGHT.
	private int[][] keyschemas = new int[][] { { 38, 40, 37, 39 }, { 87, 83, 65, 68 }, { 89, 72, 71, 74 },
			{ 80, 59, 76, 222 } };
	
	GameWindow gameWindow;
	LoginWindow loginWindow;
	ScoreWindow scoreWindow;
	
	Timer t = new Timer();
	TimerTask tt;
	
	/*
	 * Constructor to create a sever for the game. Handle most of the games activities. Passes the number of AI needed.
	 */
	public Server() throws Exception {
		
		// Create gameWindow
		gameWindow = new GameWindow(this);
		scoreWindow = new ScoreWindow(this);
		loginWindow = new LoginWindow(this);
		
		// create all players
		// createSnakes(gameW, AIPlayers);
	}
	
	public void startGame(int AIPlayers) {
		
		createSnakeAI(AIPlayers);
		
		//
		gameBoard.fillInitialFood(realPlayerList.size()+AIPlayerList.size());
		
		scoreWindow.showScoreWindow();
		gameWindow.showGameWindow();
		
		// Every second allow the AI to make a decision on the direction to move
		// Then run the Worker thread pool to make the moves for real players.
		// Update player score screen.
		// Then check if there is either 1 snake alive, or all Players are dead.
		tt = new TimerTask(){
			public void run() {
				try {
					for (int i = 0; i < AIPlayerList.size(); i++) {
						aiPool.execute(AIPlayerList.get(i));
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
				
				// in when just have 1 snake
				if (checkLastPlayer() != null) {
					System.out.println(checkLastPlayer().toString());
					endGameLoop();
				}
			}
		};
		t.schedule(tt, 0, 200);
	}
	
	private void endGameLoop(){
		tt.cancel();
		//and then just in case:
		t.cancel();
		t.purge();
		
	}
	
	public void addSnakePlayer(int playerNumber) {
		if (playerRegister[playerNumber - 1] == 0) {
			Buffer playerBuf = new Buffer(1);
			playerBuffer.add(playerBuf);
			Snake player = new SnakePlayer(gameWindow, playerNumber, gameBoard, keyschemas[playerNumber - 1], playerBuf);
			realPlayerList.add(player);
			Thread snakePlayer = new Thread(player);
			workerList.add(new Worker(playerBuf, player));
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
		for (int i = 4; i < AIPlayers + 4; i++) {
			AIPlayerList.add(new SnakeAI(gameWindow, i + 1, gameBoard));
		}
		
	}
	
	// return the last snake if have
	Snake checkLastPlayer() {
		// see how many snake
		int aliveSnake = 0;
		int alivePlayers = 0;
		int aliveAI = 0;
		
		for (int i = 0; i < AIPlayerList.size(); i++) {
			if (AIPlayerList.get(i).alive) {
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
		 System.out.println("total Snakes: " + aliveSnake);
		 System.out.println("Player Snakes: " + alivePlayers);
		 System.out.println("AI Snakes: " + aliveAI);
		 System.out.println();
		 System.out.println();
		// more than 1 snake return null
		if (alivePlayers > 1) {
			return null;
		}
		// 1 snake return that snake
		if (alivePlayers == 0 || aliveSnake == 1) {
			for (int j = 0; j < this.realPlayerList.size() - 1; j++) {
				if (realPlayerList.get(j).alive) {
					return realPlayerList.get(j);
				}
			}
			for (int i = 0; i < AIPlayerList.size(); i++) {
				if (AIPlayerList.get(i).alive) {
					return AIPlayerList.get(i);
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
