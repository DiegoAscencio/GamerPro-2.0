package tennis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;



@SuppressWarnings("serial")
public class GPpp extends JPanel implements KeyListener, ActionListener {
	
	private boolean play = false;
	public boolean pause = false; 
	public boolean quit = false; 
	private boolean play2 = false; 
	
	private int scoreP1 = 0, scoreP2 = 0; 
 

	private Timer time; 
	private int delay = 8; 
	
	
	private int playerY1 = 240; 
	private int playerY2 = 240;
	
	private int bPosX = 100; 
	private int bPosY = 430; 
	private int bXdir = -2; 
	private int bYdir = -1; 
	
	public GPpp() {
		addKeyListener(this); 
		setFocusable(true); 
		setFocusTraversalKeysEnabled(false); 
		time = new Timer(delay, this); 
		time.start(); 
	}
	public void paint(Graphics g){
		//Fondo
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 695, 592);
		
		//Scores 
		g.setColor(Color.WHITE);
		g.setFont(new Font("helvetica", Font.BOLD, 25));
		g.drawString("Score : " + scoreP1, 10, 30);
		g.drawString("Score : " + scoreP2, 570, 30);
		
		if(play2 == false){
			g.setColor(Color.WHITE);
			g.setFont(new Font("helvetica", Font.BOLD, 30));
			g.drawString("READY!",250,290);
		}
		
		//Barrita Jugador 2
		g.setColor(Color.BLUE);
		g.fillRect(5, playerY2, 8, 100);
		//Barrita Jugador 1
		g.setColor(Color.RED);
		g.fillRect(682, playerY1, 8, 100);
		
		//Linea central
		g.setColor(Color.GRAY);
		g.fillOval((695/4)+43,592/4 , 260, 260);
		g.setColor(Color.BLACK);
		g.fillOval((695/4)+ 48,(592/4)+5 , 250, 250);
		g.setColor(Color.GRAY);
		g.fillRect((695/2)-2, 0, 5, 600);
		
		//Pelota
		g.setColor(Color.YELLOW);
		g.fillOval(bPosX, bPosY, 20, 20);
		
		//Si ganas
		if(scoreP1 >= 10){
			play = false; 
			bXdir = 0; 
			bYdir = 0; 
			g.setColor(Color.WHITE);
			g.setFont(new Font("helvetica", Font.BOLD, 30));
			g.drawString("YOU WON!", 110, 300);
			g.setFont(new Font("helvetica", Font.BOLD, 20));
			g.drawString("Press ENTER to play again", 210, 350);
			g.setFont(new Font("helvetica", Font.BOLD, 30));
			g.drawString("YOU LOSE :(", 410, 300);
		}
		if(scoreP2 >= 10){
			play = false; 
			bXdir = 0; 
			bYdir = 0; 
			g.setColor(Color.WHITE);
			g.setFont(new Font("helvetica", Font.BOLD, 30));
			g.drawString("YOU LOSE :(", 410, 300);
			g.setFont(new Font("helvetica", Font.BOLD, 20));
			g.drawString("Press ENTER to play again", 210, 350);
			g.setFont(new Font("helvetica", Font.BOLD, 30));
			g.drawString("YOU WON!", 110, 300);
		}
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if(play){
			if((new Rectangle(bPosX, bPosY, 20, 20).intersects(new Rectangle(682, playerY1, 8, 100))) ||(new Rectangle(bPosX, bPosY, 20, 20).intersects(new Rectangle(5, playerY2, 8, 100))) ){// new Rectangle(bPosX, bPosY, 20, 20).intersects(new Rectangle(682, playerY2, 8, 100))){
				bXdir = -bXdir; 
			}
		
			if(new Rectangle(bPosX, bPosY, 20, 20).intersects(new Rectangle(1, 0, 2, 700))){
				scoreP2 ++;
			}
			if(new Rectangle(bPosX, bPosY, 20, 20).intersects(new Rectangle(690, 0, 2, 700))){
				scoreP1 ++;  
			}
				
			bPosX += bXdir; 
			bPosY += bYdir;
			if(bPosX < 4){
				bXdir = -bXdir; 
			}
			if(bPosY < 0){
				bYdir = -bYdir; 
			}
			if(bPosX > 670){
				bXdir = -bXdir; 
			}
			if(bPosY > 550){
				bYdir = -bYdir; 
			}
		}
		
	repaint(); 
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_P){
			play = false; 
			pause = true; 
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){
			quit = true; 
			time.stop();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			if(playerY1 < 0){
				playerY1 = 0; 
			}else{
				moveUp(); 
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(playerY1 >= 480){
				playerY1 = 480; 
			}else {
				moveDown(); 
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			if(playerY2 < 0){
				playerY2 = 0; 
			}else{
				moveUp1(); 
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			if(playerY2 > 480){
				playerY2 = 480; 
			}else {
				moveDown1(); 
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play){
					play = true; 
					play2 = false; 
					bPosX = 100; 
					bPosY = 430; 
					bXdir = -2; 
					bYdir = -1; 
					playerY1 = 240; 
					playerY2 = 240;
					scoreP1 = 0; 
					scoreP2 = 0; 
			 
					repaint();
			
				}}
			
		}
	private void moveDown() {
		play = true; play2 = true; 
		playerY1 += 30; 	
	}

	private void moveUp() {
		play = true; play2 = true; 
		playerY1 -= 30;
		
		
	}
	private void moveDown1() {
		play = true; play2 = true; 
		playerY2 += 30; 
	}

	private void moveUp1() {
		play = true; play2 = true; 
		playerY2 -= 30; 	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
