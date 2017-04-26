package snakeGame;

import java.util.ArrayList;
import java.util.List;


public class Server {
	
	// Implement a list for all the Snake AI players to be stored
	List<SnakeAI> comp = new ArrayList<SnakeAI>();
	
	// ThreadPool for the AI snakes
	ThreadPool compPool;
	
	// The amount of AI Players
	int AIPlayers;
	
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
		
		// setup Human players
		Snake player1 = new SnakePlayer(ui, 1, arrowKeys);
		Snake player2 = new SnakePlayer(ui, 2, wasd);
		
		// Create threadPool for players (possibly not needed)
		ThreadPool playerPool = new ThreadPool(2, 2);
		playerPool.execute(player1);
		playerPool.execute(player2);
		
		// create all AI players
		createAI(ui, AIPlayers);
		
		// ThreadPool which will run the AI players Tasks
		compPool = new ThreadPool(10, AIPlayers);
		executeAI();
		
		// Every second allow the AI to make a decision on the direction to move
		// Timer t = new Timer();
		// t.schedule(new TimerTask() {
		// public void run() {
		// try {
		// executeAI();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }, 0, 1000);
		
	}
	
	/*
	 * Method to create and stored all AI snake players into the List called comp
	 */
	public void createAI(GameScreen ui, int noOfComps) {
		for (int i = 3; i < noOfComps + 3; i++) {
			comp.add(new SnakeAI(ui, i));
		}
	}
	
	/**
	 * Executes all the AI movements
	 * 
	 * @throws Exception
	 */
	public void executeAI() throws Exception {
		for (int i = 0; i < comp.size(); i++) {
			compPool.execute(comp.get(i));
		}
	}
	
	/**
	 * Main method to implement and run the game
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Server server = new Server(3);
	}
}
