
package snakeGame;

import java.awt.Color;
import javax.swing.JPanel;


public class Cell {
	
	private boolean isEmpty = true;
	private boolean hasFood = false;
	private int x;
	private int y;
	
	private JPanel cellPanel;
	
	/*
	 * Constructor for the Cell class Creates a queue for all the thread if thread has multiple
	 * threads running in it
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		cellPanel = new JPanel();
		cellPanel.setBackground(Color.lightGray);
	}
	
	public JPanel getCellPanel() {
		return cellPanel;
	}
	
	public void setCellPanel(JPanel cellPanel) {
		this.cellPanel = cellPanel;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public boolean hasFood() {
		return hasFood;
	}
	
	public synchronized void placeFood() {
		hasFood = true;
		cellPanel.setBackground(Color.magenta);
	}
	
	/*
	 * breaks the thread out of queue to block
	 */
	public synchronized void beingUsed() {
		isEmpty = false;
		hasFood = false;
		cellPanel.setBackground(Color.darkGray);
	}
	
	/*
	 * sets the colour of the snake's HEAD to match the player number or AI.
	 */
	public void setColor(int playerNum) {
		cellPanel.setBackground(Color.black);
		if (playerNum == 1) {
			cellPanel.setBackground(Color.yellow);
		}
		if (playerNum == 2) {
			cellPanel.setBackground(Color.blue);
		}
		if (playerNum == 3) {
			cellPanel.setBackground(Color.red);
		}
		if (playerNum == 4) {
			cellPanel.setBackground(Color.green);
		}
	}
	
	/*
	 * stops the thread so it cannot be accessed
	 */
	public synchronized void isLeaving() {
		isEmpty = true;
		cellPanel.setBackground(Color.lightGray);
	}
	
}
