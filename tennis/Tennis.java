package tennis;

import javax.swing.JFrame;

public class Tennis {

	public Tennis(){
		createPP(); 
	}
	
		public void createPP() {
			JFrame frame = new JFrame("Tennis Pro 2.0"); 
			frame.setBounds(10, 10, 700, 600);
			GPpp gamepanel = new GPpp(); 
			frame.setVisible(true);
			frame.setResizable(false);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(gamepanel); 
		}
	}


