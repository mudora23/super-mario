package org.wn.mario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
	
	public static List<BufferedImage> allMarioImage = new ArrayList<BufferedImage>();
	
	public static BufferedImage startImage = null;
	
	public static BufferedImage endImage = null;
	
	public static BufferedImage bgImage = null;
	
	public static List<BufferedImage> allFlowerImage = new ArrayList<BufferedImage>();
	
	public static List<BufferedImage> allTriangleImage = new ArrayList<BufferedImage>();
	
	public static List<BufferedImage> allTurtleImage = new ArrayList<BufferedImage>();
	
	public static List<BufferedImage> allObstructionImage = new ArrayList<BufferedImage>();
	
	public static BufferedImage mariDeadImage = null;
	
	public static String Path = System.getProperty("user.dir")+"/bin/";
	
	public static void init(){
		for(int i=1;i<=10;i++){
			try {
				allMarioImage.add(ImageIO.read(new File(Path+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			startImage = ImageIO.read(new File(Path+"初始界面_看图.jpg"));
			bgImage = ImageIO.read(new File(Path+"firststage.jpg"));
			endImage = ImageIO.read(new File(Path+"firststageend.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=1;i<=5;i++){
			try {
				if(i<=2){
					allFlowerImage.add(ImageIO.read(new File(Path+"flower"+i+".png")));
				}
				if(i<=3){
					allTriangleImage.add(ImageIO.read(new File(Path+"triangle"+i+".png")));
				}
				allTurtleImage.add(ImageIO.read(new File(Path+"Turtle"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=1;i<=12;i++){
			try {
				allObstructionImage.add(ImageIO.read(new File(Path+"ob"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			mariDeadImage = ImageIO.read(new File(Path+"over.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
