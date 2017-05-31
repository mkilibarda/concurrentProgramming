
package snakeGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;


public class gameWindow implements KeyListener, WindowListener {

	// private int playerOne, playerTwo, playerThree, playerFour, AIPlayers = 0;
	private JFrame mainWindow;

	private Server server;


	public gameWindow(Server server) {
		this.server = server;
		this.
		mainWindow = new JFrame("Snake Game");
		mainWindow.setSize(1000, 1000);


		GridLayout gameBoard = new GridLayout(100, 100, 1, 1);


		for (int v = 0; v < 100; v++) {
			for (int h = 0; h < 100; h++){
				mainWindow.add(server.gameScreen.getCell(v, h).getCellPanel());
			}
		}

		mainWindow.setLayout(gameBoard);

		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void displayCellList(){
		mainWindow.revalidate();
		mainWindow.repaint();
		//mainWindow.setVisible(false);
		//mainWindow.setVisible(true);

	}

	public void showGameWindow() {
		mainWindow.setVisible(true);
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
		//
		// int c = e.getKeyCode();
		//
		// switch (c) {
		//
		// // ENTER PLAYERS
		// case KeyEvent.VK_UP:
		// System.out.println("Enter Player One: UP");
		// server.addSnakePlayer(1);
		//
		// // playerOne = 1;
		//
		// break;
		//
		// case KeyEvent.VK_W:
		// System.out.println("Enter Player Two: W");
		// server.addSnakePlayer(2);
		//
		// // playerTwo = 1;
		//
		// break;
		//
		// case KeyEvent.VK_Y:
		// System.out.println("Enter Player Three: Y");
		// server.addSnakePlayer(3);
		//
		// // playerThree = 1;
		//
		// break;
		//
		// case KeyEvent.VK_P:
		// System.out.println("Enter Player Four: P");
		// server.addSnakePlayer(4);
		//
		// // playerFour = 1;
		//
		// break;
		//
		// // // EXIT PLAYERS
		// // case KeyEvent.VK_1:
		// // System.out.println("Exit Player One: 1");
		// //
		// // playerOne = 0;
		// //
		// // break;
		// //
		// // case KeyEvent.VK_2:
		// // System.out.println("Exit Player Two: 2");
		// //
		// // playerTwo = 0;
		// //
		// // break;
		// //
		// // case KeyEvent.VK_3:
		// // System.out.println("Exit Player Three: 3");
		// //
		// // playerThree = 0;
		// //
		// // break;
		// //
		// // case KeyEvent.VK_4:
		// // System.out.println("Exit Player Four: 4");
		// //
		// // playerFour = 0;
		// //
		// // break;
		// //
		// // case KeyEvent.VK_ESCAPE:
		// // System.out.println("Exit of Game Window");
		// // System.exit(0);
		// // break;
		// //
		// // default:
		// // break;
		// }
		//
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
