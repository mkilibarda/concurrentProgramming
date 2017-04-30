package snakeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class gameWindow extends logInWindow{
	
	int playerOne, playerTwo, playerThree, playerFour, AIPlayers = 0;

	public gameWindow(){

		JFrame mainWindow = new JFrame("Snake Game");
		mainWindow.setBounds(450, 150, 600, 800);
		mainWindow.setLayout(new GridLayout(2, 0));

		playerOne = getPlayerOne();
		playerTwo = getPlayerTwo();
		playerThree = getPlayerThree();
		playerFour = getPlayerFour();
		AIPlayers = getAIPlayers();

		JPanel topPanel = new JPanel(new GridLayout(0, 3));
		JPanel botPanel = new JPanel();


		JButton buttonPause = new JButton("Pause");
		JButton buttonExit = new JButton("Exit");
		JButton buttonNewPlayer = new JButton("Change Players");
		

		topPanel.add(buttonPause);
		topPanel.add(buttonExit);
		topPanel.add(buttonNewPlayer);
		
		
		buttonExit.addActionListener(new ActionListener() {

			@Override 
			public void actionPerformed(ActionEvent e) {

				mainWindow.dispose();
			}
		});
		
		buttonNewPlayer.addActionListener(new ActionListener() {

			@Override 
			public void actionPerformed(ActionEvent e) {

				mainWindow.hide();
				
				logInWindow first = new logInWindow();
				
			}
		});


		mainWindow.add(topPanel);
		mainWindow.add(botPanel);


		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
