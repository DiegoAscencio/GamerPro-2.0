package game; 
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel; 


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{

	public boolean pause = false;
	protected boolean play = false; 
	public static final int WIDTH = 700; 
	public static final int HEIGHT = 600; 
	private final int SIZE = 20; 
	private int speed=500;
	private final int aDiference=5;
	
	
	
	//render
	private Graphics2D g2d; 
	private BufferedImage image; 
	
	//Game Loop
	private Thread thread; 
	private boolean running; 
	private long targetTime; 
	
	//Game Stuff
	private Entity head, apple; 
	private ArrayList<Entity> snake; 
	private int score; 
	private int level;
	private boolean gameover; 
	//movement
	private int dx , dy; 
	
	//Key input 
	private boolean up, down, right, left, start; 
	
	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT)); 
		setFocusable(true); 
		requestFocus(); 
		addKeyListener(this); 
	}
	public void paint(Graphics2D g2d){
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		g2d.setColor(Color.GREEN);// Color de la serpeinte
		for(Entity e : snake){
			e.render(g2d);
		}
		g2d.setColor(Color.WHITE);
		apple.render(g2d);
		if(gameover){
			g2d.setFont(new Font("helvetica", Font.BOLD, 30));
			g2d.drawString("Game Over!", 250, 290);
			g2d.setFont(new Font("helvetica", Font.BOLD, 20));
			g2d.drawString("Press ENTER to try again", 215, 320);

		}
		if(dx == 0 && dy == 0){
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("helvetica", Font.BOLD, 50));
			g2d.drawString("READY!",(HEIGHT/2),(WIDTH/2)-100);
		}
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("helvetica", Font.BOLD, 25));
		g2d.drawString("Score : "+ score + " Level : " + level,10,30);
		
		}
	@Override
	public void addNotify() {
		
		super.addNotify();
		thread = new Thread(this); 
		thread.start();
	}
	private void setFPS(int fps){
		targetTime = speed / fps;
	}
	@Override
	public void run() {
		if(running) return; 
		init(); 
		long startTime; 
		long elapsed; 
		long wait; 
		while(!pause){
			play = true; 
			startTime = System.nanoTime(); 
			
			update(); 
			requestRender(); 
			
			elapsed = System.nanoTime() - startTime; 
			wait = targetTime - elapsed / 1000000; 
			if(wait > 0){
				try{
					Thread.sleep(wait);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}
	}

	private void requestRender() {
		paint(g2d); 
		Graphics g = getGraphics(); 
		g.drawImage(image, 0, 0, null); 
		g.dispose();
	}
	private void update() {
		if(gameover){
			if(start){
				setUplevel(); 
				play = true;
			}
			return; 
		}
		
		if(up && dy == 0){
			dy = -SIZE; 
			dx = 0; 
		}
		if(down && dy == 0){
			dy = SIZE; 
			dx = 0; 
		}
		if(left && dx == 0){
			dy = 0; 
			dx = -SIZE; 
		}
		if(right && dx == 0 && dy != 0){
			dy = 0; 
			dx = SIZE; 
		}
		if(dx != 0 || dy != 0){
			for (int i = snake.size() - 1; i > 0; i--) {
				snake.get(i).setPosition(snake.get(i - 1).getX(), snake.get(i - 1).getY());
			}
			head.move(dx, dy);
		}
		
		
		for(Entity e : snake){
			if(e.isCollsion(head)){
				play = false; 
				gameover = true; 
				break; 
			}
		}
		
		if(apple.isCollsion(head)){
			score++; 
			setApple(); 
			
			Entity e = new Entity(SIZE); 
			e.setPosition(-100, -100);
			snake.add(e);
			if(score % 10 == 0){
				level++; 
				if(level > 10 ) level = 10; 
				setFPS(level * 4); 
			}
		}
		
		if(head.getX()< 0) head.setX(WIDTH);
		if(head.getY()< 0) head.setY(HEIGHT);
		if(head.getX() > WIDTH) head.setX(0);
		if(head.getY() > HEIGHT) head.setY(0);
	}

	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g2d = image.createGraphics();
		running = true; 
		setUplevel(); 
	}
	private void setUplevel(){
		snake = new ArrayList<Entity>(); 
		head = new Entity(SIZE); 
		head.setPosition(WIDTH / 2, HEIGHT /2);
		snake.add(head); 
		for(int i = 1; i < 4; i ++){
			Entity e = new Entity(SIZE); 
			e.setPosition(head.getX() + (i * SIZE), head.getY());
			snake.add(e); 
			
		}
		apple = new Entity(SIZE-aDiference); 
		setApple(); 
		score = 0;
		gameover = false; 
		level = 1; 
		dx = dy = 0; 
		setFPS(level * 4);
		
	}
	public void setApple(){
		int x = (int)(Math.random() * (WIDTH - SIZE)); 
		int y = (int)(Math.random() * (HEIGHT - SIZE)); 
		x = x - (x % SIZE); 
		y = y - (y % SIZE); 
		
		apple.setPosition(x, y);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*if(e.getKeyCode() == KeyEvent.VK_P){
				if(pause){
					pause=false;
					play=false;
				}
				else{
					pause=true;
					play=true;
				}

		}*/
		int k = e.getKeyCode(); 
		if(k == KeyEvent.VK_UP) 
			up = true;
		if(k == KeyEvent.VK_DOWN) 
			down = true;
		if(k == KeyEvent.VK_LEFT) 
			left = true;
		if(k == KeyEvent.VK_RIGHT) 
			right = true;
		if(k == KeyEvent.VK_ENTER) 
			start = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int k = e.getKeyCode(); 
		if(k == KeyEvent.VK_UP) up = false;
		if(k == KeyEvent.VK_DOWN) down = false;
		if(k == KeyEvent.VK_LEFT) left = false;
		if(k == KeyEvent.VK_RIGHT) right = false;
		if(k == KeyEvent.VK_ENTER) start = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

}
