package snakeGame;

public class Player implements Runnable{
	
	//private BlockingQueue taskQueue = null;
	private boolean       isStopped = false;

	public Player(){
		
	}

	public void run(){
		while(!isStopped){
			try{
				//CODE TO MOVE SNAKE GOES HERE
				
			} catch(Exception e){
				//log or otherwise report exception,
				//but keep pool thread alive.
			}
		}
	}
}
