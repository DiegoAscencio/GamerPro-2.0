import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import game.GamePanel;
import game.Snake;
import tennis.GPpp;
import tennis.Tennis;
import brickBracker.*; 
public class Index {

	int width;
	int height;

	Frame ventana;
	Button btnS = new Button();
	Button btnBB= new Button();
	Button btnPP = new Button();
	Label titulo = new Label();
	Button exit = new Button();
	
	public Index() {
		initializeFrame();
		btnS.setBounds((700/4)-20, 180, 400, 80);
		btnS.setFont(new Font("helvetica", Font.BOLD, 30));
		btnS.setLabel("Snake");
		btnS.setBackground(Color.GREEN); 
		
		btnBB.setBounds((700/4)-20, 330, 400, 80);
		btnBB.setFont(new Font("helvetica", Font.BOLD, 30));
		btnBB.setLabel("Brick Breaker");
		btnBB.setBackground(Color.ORANGE); 
		
		btnPP.setBounds((700/4)-20, 480, 400, 80);
		btnPP.setFont(new Font("helvetica", Font.BOLD, 30));
		btnPP.setLabel("Ping Pong");
		btnPP.setBackground(Color.CYAN);
		
		titulo.setFont(new Font("helvetica", Font.BOLD, 40));
		titulo.setBounds((700/4)+30,50, 400, 80);
		titulo.setText("GAME PRO 2.0");
		titulo.setForeground(Color.BLUE);
		
		exit.setBounds(15, 540, 80, 40);
		exit.setFont(new Font("helvetica", Font.PLAIN, 15));
		exit.setLabel("Salir");
		exit.setBackground(Color.RED);
		

	
		
		btnS.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//ventana.dispose();
				new Snake(); 
				//s.createS();		
			}
		});
		
		btnBB.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//ventana.dispose();
				new BrickBreaker(); 
				//bb.CreateBB();			
			}
			
		});
		btnPP.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//ventana.dispose();
				new Tennis(); 
			}
			 
		});
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();
				//new Index(); 
				
			}
		});
		
	}


	private void initializeFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		width = 700;
		height = 600;
		
		ventana = new Frame("GamePro2.0");
		ventana.setSize(width, height);
		ventana.setBackground(Color.BLACK);
		//ventana.setLocation(((d.width - ventana.getWidth())/2),((d.height - ventana.getHeight())/2));
		ventana.setVisible(true);
		ventana.add(btnBB); 
		ventana.add(btnS); 
		ventana.add(btnPP);
		ventana.add(titulo);
		ventana.add(exit);
		ventana.setResizable(false);

		
		
	
		ventana.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				ventana.dispose();
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		ventana.setLayout(null);
	}


	public static void main(String[] args) {
		new Index();
	}

}
