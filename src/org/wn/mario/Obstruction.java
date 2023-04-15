package org.wn.mario;

import java.awt.image.BufferedImage;

public class Obstruction implements Runnable{
	private int x;
	private int y;
	private Thread t = new Thread(this);
	private int type;
	private int starttype;
	private BufferedImage showImage = null;
	private BackGround bg;
	public Obstruction(int x,int y,int type,BackGround bg){
		this.x = x;
		this.y = y;
		this.type = type;
		this.starttype = type;
		this.bg = bg;
		setImage();
		if(this.type == 11){
			t.start();
		}
	}
	public void reset(){
		this.type = starttype;
		this.setImage();
	}
	public void setImage(){
		showImage = StaticValue.allObstructionImage.get(type);
	}
	
	public BufferedImage getShowImage() {
		return showImage;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public void run() {
		while(true){
			if(this.bg.isOver()){
				if(this.y < 420){
					this.y += 5;
				}else{
					this.bg.setDown(true);
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
