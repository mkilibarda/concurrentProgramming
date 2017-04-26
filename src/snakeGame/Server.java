package snakeGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Server {
	CellList gameScreen = new CellList(100, 100);
	
	// The amount of AI Players
	int AIPlayers;
	List<SnakeAI> comp = new ArrayList<SnakeAI>();
	
	// ThreadPool for the AI snakes
	ThreadPool aiPool;
	// ThreadPool for the workers
	ThreadPool compPool;
	
	// movement schemes
	// in order of UP / DOWN / LEFT / RIGHT.
	private int[] arrowKeys = new int[] { 38, 40, 37, 39 };
	private int[] wasd = new int[] { 87, 83, 65, 68 };
	
	/*
	 * Constructor to create a sever for the game. Handle most of the games activities. Passes the number of AI needed.
	 */
	public Server(int AI) throws Exception {
		// Set AI player count
		this.AIPlayers = AI;
		
		// Create gamescreen
		GameScreen ui = new GameScreen();
		
		compPool = new ThreadPool(4, AIPlayers);
		// create all players
		createSnakes(ui, AIPlayers);
	}
	
	/*
	 * Method to create and stored all AI snake players into the List called comp
	 */
	public void createSnakes(GameScreen ui, int noOfComps) {
		// creates real-player snakes (this will be changed later to be triggered by key-press on keyboard from UI)
		Snake player1 = new SnakePlayer(ui, 1, arrowKeys);
		Snake player2 = new SnakePlayer(ui, 2, wasd);
		Thread snakePlayer1 = new Thread(player1);
		Thread snakePlayer2 = new Thread(player2);
		snakePlayer1.start();
		snakePlayer2.start();
		
		// creates and populates a thread pool for AI, as 100 running threads for AI would be too much overhead,
		// so simulating this with a pool that does 10 at a time is acceptable.
		aiPool = new ThreadPool(10, AIPlayers);
		//thread numbers start at 3 to accommodate for the (currently) 2 real-players
		for (int i = 3; i < noOfComps + 3; i++) {
			comp.add(new SnakeAI(ui, i));
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
		Server server = new Server(5);
	}
}
