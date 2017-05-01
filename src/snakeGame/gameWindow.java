package snakeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class gameWindow implements KeyListener{
	
	private int playerOne, playerTwo, playerThree, playerFour, AIPlayers = 0;
	private JFrame mainWindow;
	
	public gameWindow(){
		
		mainWindow = new JFrame("Snake Game");
		mainWindow.setBounds(450, 150, 600, 800);
		mainWindow.setLayout(new GridLayout(2, 0));
		
//		playerOne = getPlayerOne();
//		playerTwo = getPlayerTwo();
//		playerThree = getPlayerThree();
//		playerFour = getPlayerFour();
//		AIPlayers = getAIPlayers();

		JPanel topPanel = new JPanel(new GridLayout(0, 2));
		JPanel botPanel = new JPanel();

		JButton buttonPause = new JButton("Pause");
		JButton buttonExit = new JButton("Exit");

		topPanel.add(buttonPause);
		topPanel.add(buttonExit);
		
		
		buttonExit.addActionListener(new ActionListener() {

			@Override 
			public void actionPerformed(ActionEvent e) {

				mainWindow.dispose();
			}
		});

		mainWindow.add(topPanel);
		mainWindow.add(botPanel);

		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		int c = e.getKeyCode();

		switch(c){
		
		// ENTER PLAYERS
		case KeyEvent.VK_UP:
			System.out.println("Enter Player One: UP");

			playerOne = 1;

			break;

		case KeyEvent.VK_W:
			System.out.println("Enter Player Two: W");

			playerTwo = 1;

			break;

		case KeyEvent.VK_Y:
			System.out.println("Enter Player Three: Y");

			playerThree = 1;

			break;

		case KeyEvent.VK_P:
			System.out.println("Enter Player Four: P");

			playerFour = 1;

			break;
			
		// EXIT PLAYERS
		case KeyEvent.VK_1:
			System.out.println("Exit Player One: 1");
			
			playerOne = 0;
			
			break;
			
		case KeyEvent.VK_2:
			System.out.println("Exit Player Two: 2");
			
			playerTwo = 0;
			
			break;
			
		case KeyEvent.VK_3:
			System.out.println("Exit Player Three: 3");
			
			playerThree = 0;
			
			break;
			
		case KeyEvent.VK_4:
			System.out.println("Exit Player Four: 4");
			
			playerFour = 0;
			
			break;
			
		case KeyEvent.VK_ESCAPE:
			System.out.println("Exit of Game Window");
			System.exit(0);
			break;

		default:
			break;
		}

	}

	public JFrame getMainFrame() {
		return mainWindow;
	}


	
}
