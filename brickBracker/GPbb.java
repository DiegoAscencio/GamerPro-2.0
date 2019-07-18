package brickBracker;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.IndexColorModel;

import javax.swing.*;

@SuppressWarnings("serial")
public class GPbb extends JPanel implements KeyListener, ActionListener{

	private boolean play = false; 
	public boolean pause = false;
	private boolean play2 = false;
	private boolean win = false; 
	private int score = 0; 
	
	private int totalBricks = 70; 
	
	private Timer time; 
	private int delay = 8; 
	
	private int playerX = 310; 
	
	private int bPosX = 120; 
	private int bPosY = 350; 
	private int bXdir = -1; 
	private int bYdir = -2; 
	
	private MapGenerator map; 
	
	public GPbb() {
		map = new MapGenerator(5, 14); 
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
		
		//MAPA
		map.draw((Graphics2D)g);
		
		//Scores 
		g.setColor(Color.WHITE);
		g.setFont(new Font("helvetica", Font.BOLD, 25));
		g.drawString("Score : " + score, 10, 30);
		
		//Barrita 
		g.setColor(Color.WHITE);
		g.fillRect(playerX, 550, 100, 8);
		
		//Pelota
		g.setColor(Color.YELLOW);
		g.fillOval(bPosX, bPosY, 20, 20);
		
		//Si ganas
		if(totalBricks <= 0){
			play = false; 
			win = true; 
			bXdir = 0; 
			bYdir = 0; 
			g.setColor(Color.WHITE);
			g.setFont(new Font("helvetica", Font.BOLD, 30));
			g.drawString("YOU WON!", 220, 300);
			g.drawString("Press ENTER to go to play again", 150, 350);
		}
		if(play2 == false){
			g.setColor(Color.WHITE);
			g.setFont(new Font("helvetica", Font.BOLD, 30));
			g.drawString("READY!",250,290);
		}
		//Si pierdes...
		if(bPosY > 570){
			play = false; 
			win = false; 
			bXdir = 0; 
			bYdir = 0; 
			g.setColor(Color.WHITE);
			g.setFont(new Font("helvetica", Font.BOLD, 30));
			g.drawString("Game Over!", 250, 290);
			g.setFont(new Font("helvetica", Font.BOLD, 20));
			g.drawString("Press ENTER to try again", 215, 320);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		time.start();
		if(play){
			if(new Rectangle(bPosX, bPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
				bYdir = -bYdir; 
			}
			
			A: for(int i = 0; i<map.map.length; i++){
				for(int j = 0; j<map.map[0].length; j++){
					if(map.map[i][j] > 0){
						int bX = j * map.bWidth  + 80; 
						int bY = i * map.bHeight + 50; 
						int bW = map.bWidth; 
						int bH = map.bHeight; 
						
						Rectangle rect = new Rectangle(bX, bY, bW, bH); 
						Rectangle ballRect = new Rectangle(bPosX, bPosY, 20, 20); 
						Rectangle bRect = rect;
						
						if(ballRect.intersects(bRect)){
							map.setBickValue(0, i, j);
							totalBricks--;
							score += 5; 
							if(bPosX + 19 <= bRect.x || bPosX + 1 >= bRect.x + bRect.width){
								bXdir = - bXdir; 
							}else{
								bYdir = -bYdir; 
							}
							break A; 
						}
						
					}
				}
			}
			
			bPosX += bXdir; 
			bPosY += bYdir;
			if(bPosX < 0){
				bXdir = -bXdir; 
			}
			if(bPosY < 0){
				bYdir = -bYdir; 
			}
			if(bPosX > 670){
				bXdir = -bXdir; 
			}
		}
		
		repaint(); 
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_P){
			play = false; 
			pause = true; 
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(playerX >= 600){
				playerX = 600; 
			}else{
				moveRight(); 
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(playerX < 10){
				playerX = 10; 
			}else {
				moveLeft(); 
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play){
				if(!win){
					play = true; 
					play2= false; 
					bPosX = 120; 
					bPosY = 350; 
					bXdir = -1; 
					bYdir = -2; 
					playerX = 310; 
					score = 0; 
					totalBricks = 32; 
					map = new MapGenerator(5,14); 
					repaint();
				}if(win){
					play = true; 
					play2= false; 
					bPosX = 120; 
					bPosY = 350; 
					bXdir = -1; 
					bYdir = -2; 
					playerX = 310; 
					score = 0; 
					totalBricks = 28; 
					map = new MapGenerator(5,14); 
					repaint(); 
				}
			
			}
		}
	}
	public void moveRight(){
		play = true;
		play2 = true; 
		playerX+=20; 
	}
	public void moveLeft(){
		play = true; 
		play2 = true; 
		playerX -= 20; 
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

}
