package snakeGame;

public class Cell{
	
	private BlockingQueue taskQueue = null;
    private boolean       isEmpty = false;
    private int x;
    private int y;
    
    /*
     * Constructor for the Cell class
     * Creates a queue for all the thread if thread has multiple threads running in it
     */
    public Cell(BlockingQueue queue){
        taskQueue = queue;
    }
    
    /*
     * Set location for the cells to interact with the snake game.
     */
    public void setLocation(int xLo, int yLo){
    	x = xLo;
    	y = yLo;
    }
    
    /*
     * Set the location of the x coordinates
     * returns x
     */
    public int getX(){
    	return x;
    }
    
    /*
     * Set the location of the y coordinates
     * returns y
     */
    public int getY(){
    	return y;
    }
    
    
    /*
     * breaks the thread out of queue to block
     */
    public synchronized void beingUsed(){
        isEmpty = true;
       
    }
    
    /*
     * stops the thread so it cannot be accessed
     */
    public synchronized void isLeaving(){
        isEmpty = false;
    }
	
}
