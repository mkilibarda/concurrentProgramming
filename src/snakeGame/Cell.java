package snakeGame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Cell{
	
	private BlockingQueue taskQueue = null;
    private boolean       isEmpty = false;
    private int x;
    private int y;

    private JPanel cellPanel;
    /*
     * Constructor for the Cell class
     * Creates a queue for all the thread if thread has multiple threads running in it
     */
    public Cell(int x, int y){
       this.x = x;
       this.y = y;
       cellPanel = new JPanel();
       
       cellPanel.setBackground(Color.gray);
    }
    
    public JPanel getCellPanel() {
		return cellPanel;
	}


	public void setCellPanel(JPanel cellPanel) {
		this.cellPanel = cellPanel;
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
        cellPanel.setBackground(Color.darkGray);
    }
    public void setColor(int playerNum){
        cellPanel.setBackground(Color.black);
    	if(playerNum == 1){
    		cellPanel.setBackground(Color.yellow);
    	}
    	if(playerNum == 2){
    		cellPanel.setBackground(Color.blue);
    	}
    	if(playerNum == 3){
    		cellPanel.setBackground(Color.red);
    	}
    	if(playerNum == 4){
    		cellPanel.setBackground(Color.green);
    	}
    	
    }
    
    /*
     * stops the thread so it cannot be accessed
     */
    public synchronized void isLeaving(){
        isEmpty = false;
        cellPanel.setBackground(Color.gray);
    }
	
}
