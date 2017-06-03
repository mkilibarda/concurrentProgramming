package snakeGame;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class scoreWndow  {

	private JFrame frame;
	private JPanel panelCenter;

	//	private int playerOne, playerTwo, playerThree, playerFour, AIPlayers = 0;

	private JPanel panelMid1 = new JPanel(new GridLayout(0, 4));
	private JPanel panelMid2 = new JPanel(new GridLayout(0, 4));
	private JPanel panelMid3 = new JPanel(new GridLayout(0, 4));
	private JPanel panelMid4 = new JPanel(new GridLayout(0, 4));

	private JLabel P12Label = new JLabel("Player One: ");
	private JLabel P21Label = new JLabel("Player Two: ");
	private JLabel P31Label = new JLabel("Player Three: ");
	private JLabel P41Label = new JLabel("Player Four: ");

	JLabel score1 = new JLabel("Player OUT");
	JLabel score2 = new JLabel("Player OUT");
	JLabel score3 = new JLabel("Player OUT");
	JLabel score4 = new JLabel("Player OUT");

	// private int[][] keyschemas;
	private Server server;

	public scoreWndow(Server server) {
		// this.keyschemas = server;
		this.server = server;
		frame = new JFrame("Score	 Window");
		// frame.setName("Log In Window");
		frame.setBounds(450, 150, 350, 150);
		//frame.setLayout(new GridLayout(3, 0));

		centerFrameSettings();

		// Advises the window when 'x' is pressed then the window closes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// The Frame cannot be resized
		frame.setResizable(false);
		// Tells the frame that it is visible
		frame.setVisible(true);
	}


	public void centerFrameSettings() {
		// CENTER //
		panelCenter = new JPanel(new GridLayout(4, 0));




		// Panel - One
		panelMid1.add(P12Label);
		panelMid1.add(score1);

		// Panel - Two
		panelMid2.add(P21Label);
		panelMid2.add(score2);

		// Panel - Three
		panelMid3.add(P31Label);
		panelMid3.add(score3);

		// Panel - Four
		panelMid4.add(P41Label);
		panelMid4.add(score4);

		// Add the secondary panels to the main 'Center' panel
		panelCenter.add(panelMid1);
		panelCenter.add(panelMid2);
		panelCenter.add(panelMid3);
		panelCenter.add(panelMid4);

		frame.add(panelCenter, BorderLayout.CENTER);

	}
	public void returnScore(){	
		try{
			score1.setText(""+server.players.get(0).getScore());}
		catch(Exception s){}
		try{
			score2.setText(""+server.players.get(1).getScore());}
		catch(Exception s){}	
		try{
			score3.setText(""+server.players.get(2).getScore());}
		catch(Exception s){}		
		try{
			score4.setText(""+server.players.get(3).getScore());}
		catch(Exception s){}		
	}

}
