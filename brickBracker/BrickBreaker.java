package brickBracker;

import javax.swing.JFrame;

public class BrickBreaker {

	public BrickBreaker(){
		CreateBB(); 
	}
	
	public void CreateBB(){
		JFrame frame = new JFrame("Brick Breaker Pro"); 
		frame.setBounds(10, 10, 700, 600);
		GPbb gamepanel = new GPbb(); 
		frame.setVisible(true);
		frame.setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamepanel); 
		
	}
}
