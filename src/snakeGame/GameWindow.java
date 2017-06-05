
package snakeGame;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameWindow implements KeyListener, WindowListener {
	
	private JFrame mainWindow;
	private CellList gameBoard;
	
	/*
	 * Acts as the primary game screen that the players see and play the game from. Key events for
	 * player snake movement are actually handled by keyeventListeneres in the snakes themselves
	 */ public GameWindow(Server server) {
		this.gameBoard = server.gameBoard;
		this.mainWindow = new JFrame("Snake Game");
		
		// set the window position to top left and size to 1000x1000
		mainWindow.setBounds(0, 0, 1000, 1000);
		// set the grid to 100x100
		mainWindow.setLayout(new GridLayout(100, 100, 1, 1));
		
		// fill the grid with the JPanels stored within each Cell object
		// within the CellList gameBoard.
		// these JPanels also store the colors for the cells.
		for (int v = 0; v < 100; v++) {
			for (int h = 0; h < 100; h++) {
				mainWindow.add(gameBoard.getCell(v, h).getCellPanel());
			}
		}
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * re-check the JPanels in each Cell object and update the screen with the correct colors
	 */
	public void displayCellList() {
		mainWindow.revalidate();
		mainWindow.repaint();
		
	}
	
	public void showGameWindow() {
		mainWindow.setVisible(true);
	}
	
	public void EndDialog(String winner) {
		JPanel panelBot = new JPanel(new GridLayout(3, 0));
		int input = JOptionPane.showOptionDialog(panelBot, winner, "Game Over", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (input == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
		}
	}
	
	public JFrame getMainFrame() {
		return mainWindow;
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
