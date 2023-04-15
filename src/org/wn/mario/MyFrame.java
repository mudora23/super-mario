package org.wn.mario;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements KeyListener,Runnable{
	
	private List<BackGround> allBG = new ArrayList<BackGround>();
	
	private Mario mario = null;
	
	private BackGround nowBG = null;
	
	private Thread t = null;
	
	private boolean isStart = false;
	
	public static void main(String[] args){
		new MyFrame();
	}
	
	public MyFrame(){
		this.setTitle("RUN FAST");
		this.setSize(900, 600);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width-900)/2, (height-600)/2);
		this.setResizable(false);
		StaticValue.init();
		for(int i=1;i<=7;i++){
			this.allBG.add(new BackGround(i, i==7?true:false));
		}
		this.nowBG = this.allBG.get(0);
		this.mario = new Mario(0, 480);
		this.mario.setBg(nowBG);
		
		this.repaint();
		
		this.addKeyListener(this);
		
		this.t = new Thread(this);
		t.start();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		BufferedImage image = new BufferedImage(900, 600, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();
		
		if(this.isStart){
			g2.drawImage(this.nowBG.getBgImage(), 0, 0, this);
			g2.drawString("live:    "+this.mario.getLife(), 720, 50);
			
			g2.drawString("points:    "+this.mario.getScore(), 800, 50);
			
			Iterator<Enemy> iterEnemy = this.nowBG.getAllEnemy().iterator();
			while(iterEnemy.hasNext()){
				Enemy e = iterEnemy.next();
				g2.drawImage(e.getShowImage(), e.getX(), e.getY(), this);
			}
			
			Iterator<Obstruction> iter = this.nowBG.getAllObstruction().iterator();
			while(iter.hasNext()){
				Obstruction ob = iter.next();
				g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
			}

			g2.drawImage(this.mario.getShowImage(), this.mario.getX(), this.mario.getY(), this);
			
		}else{
			g2.drawImage(StaticValue.startImage, 0, 0, this);
		}

		g.drawImage(image, 0, 0, this);
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if(this.isStart){

			if(e.getKeyCode()==39){
				this.mario.rightMove();
			}
			if(e.getKeyCode()==37){
				this.mario.leftMove();
			}
			if(e.getKeyCode()==38){
				this.mario.jump();
			}
		}else if(e.getKeyCode()==32){
			this.isStart = true;
			this.nowBG.enemyStartMove();
			this.mario.setScore(0);
			this.mario.setLife(3);
		}
	}

	public void keyReleased(KeyEvent e) {
		if(this.isStart){
			if(e.getKeyCode()==39){
				this.mario.rightStop();;
			}
			if(e.getKeyCode()==37){
				this.mario.leftStop();;
			}
		}
	}

	public void run() {
		while(true){
			this.repaint();
			try {
				Thread.sleep(50);
				if(this.mario.getX() >= 840){
					this.nowBG = this.allBG.get(this.nowBG.getSort());
					this.mario.setBg(nowBG);
					this.nowBG.enemyStartMove();
					this.mario.setX(0);
				}
				if(this.mario.isDead()){
					JOptionPane.showMessageDialog(this, "game over");
					System.exit(0);
				}
				if(this.mario.isClear()){
					JOptionPane.showMessageDialog(this, "congratulation£¡");
					System.exit(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
