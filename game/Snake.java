package game;
import java.awt.Dimension;

import javax.swing.*;

public class Snake {

	public Snake(){
		createS(); 
	}
	
	public void createS (){
		JFrame frame = new JFrame("Snake SuperPro 2.0"); 
		frame.setContentPane(new GamePanel());
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		
		frame.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		
		frame.setVisible(true);
	}
}
