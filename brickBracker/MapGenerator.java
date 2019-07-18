package brickBracker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {

	public int map[][]; 
	public int bWidth; 
	public int bHeight; 
	
	public MapGenerator(int row, int col){
		map = new int [row][col]; 
		for (int i = 0; i < map.length; i++){
			for(int j = 0; j< map[0].length; j++){
				map[i][j] = 1;
			}	
		}
		 bWidth = 540/col; 
		 bHeight = 150/row; 
	}
	
	public void draw(Graphics2D g2d){
		for(int i = 0; i< map.length; i++){
			for(int j = 0; j<map[0].length; j++){
				if(map[i][j]> 0){
					g2d.setColor(Color.RED); 
					g2d.fillOval(j * bWidth + 80, i * bHeight + 50, bWidth, bHeight);
				
					g2d.setStroke(new BasicStroke(3));
					g2d.setColor(Color.BLACK);
					g2d.drawOval(j * bWidth + 80, i * bHeight + 50, bWidth, bHeight);
				}
			}
		}
	}
	public void setBickValue(int value, int row, int col){
		map[row][col] = value; 
	}
}
