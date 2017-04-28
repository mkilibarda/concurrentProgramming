package snakeGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Server {
	CellList gameScreen = new CellList(100, 100);
	
	// the amount of real players
	int realPlayers;
	// The amount of AI Players
	int AIPlayers;
	List<SnakeAI> comp = new ArrayList<SnakeAI>();
	
	// ThreadPool for the AI snakes
	ThreadPool aiPool;
	// ThreadPool for the workers
	ThreadPool compPool;
	
	// movement schemes
	// in order of UP / DOWN / LEFT / RIGHT.
	private int[][] keyschemas = new int[][] { { 38, 40, 37, 39 }, { 87, 83, 65, 68 }, { 89, 72, 71, 74 },
			{ 80, 59, 76, 222 } };
	// private int[] arrowKeys = new int[] { 38, 40, 37, 39 };
	// private int[] wsad = new int[] { 87, 83, 65, 68 };
	// private int[] yhgj = new int[] { 89, 72, 71, 74 };
	// private int[] plAndSymbols = new int[] { 80, 59, 76, 222 };
	
	/*
	 * Constructor to create a sever for the game. Handle most of the games activities. Passes the number of AI needed.
	 */
	public Server(int real, int AI) throws Exception {
		// Set AI player count
		this.AIPlayers = AI;
		this.realPlayers = real;
		
		// Create gamescreen
		GameScreen ui = new GameScreen();
		
		compPool = new ThreadPool(4, AIPlayers);
		// create all players
		createSnakes(ui, AIPlayers);
	}
	
	/*
	 * Method to create all snakes and get them running.
	 */
	public void createSnakes(GameScreen ui, int noOfComps) {
		// creates real-player snakes (this will be changed later to be triggered by key-press on keyboard from UI)
		for (int i = 0; i < realPlayers; i++) {
			Snake player = new SnakePlayer(ui, i + 1, keyschemas[i]);
			Thread snakePlayer = new Thread(player);
			snakePlayer.start();
		}
		
		// creates and populates a thread pool for AI, as 100 running threads for AI would be too much overhead,
		// so simulating this with a pool that does 10 at a time is acceptable.
		aiPool = new ThreadPool(10, AIPlayers);
		// thread numbers start at 5 to accommodate for the (currently) 4 real-players
		for (int i = realPlayers; i < noOfComps + realPlayers; i++) {
			comp.add(new SnakeAI(ui, i + 1));
		}
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
			}
		}, 0, 1000);
	}
	
	/**
	 * Main method to implement and run the game
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Server server = new Server(4, 5);
	}
}
