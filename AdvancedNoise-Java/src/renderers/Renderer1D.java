/*
filename    Renderer.java
author      Max Wright
uploaded    12/20/2017

Brief Description:
  
  
  © 2017 Max Wright. All rights reserved. 
  Free for use. Use at your own risk, Max Wright will not be liable for any
  damages caused by this code.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import advNoise1D.AdvPerlin1D;
import coherentNoise1D.PerlinNoise1D;
import coherentNoise1D.Scales1D;
import coherentNoise1D.Simplex1D;
import coherentNoise1D.Squares1D;
import coherentNoise1D.Triangles1D;
import coherentNoise1D.Wood1D;


public class Renderer1D extends JComponent {
	private PerlinNoise1D perlin1D = new PerlinNoise1D(10,1000);
	private Squares1D squares1D = new Squares1D(3, 1000);
	private Simplex1D simplex1D = new Simplex1D(3, 1000);
	private Triangles1D triangles1D = new Triangles1D(3,1000);
	private Scales1D scales1D = new Scales1D(3, 1000);
	private Wood1D wood1D = new Wood1D(3, 1000);
	
	private PerlinNoise1D perlin1Da = new PerlinNoise1D(10,1000);
	private PerlinNoise1D perlin1Db = new PerlinNoise1D(20,1000);
	private PerlinNoise1D perlin1Dc = new PerlinNoise1D(40,1000);
	private PerlinNoise1D perlin1Dd = new PerlinNoise1D(80,1000);
	
	private AdvPerlin1D advPer = new AdvPerlin1D(10, 1000, 3, .825);
	
	public Renderer1D() {
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Set the background as black.
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Sets the image of the gradient to the middle of the JComponent
		BufferedImage temp = getGraphic();

		g.drawImage(temp, 0, 0, null);
	}

	private BufferedImage getGraphic() {
		BufferedImage image = new BufferedImage(1000, 1000,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(Color.BLACK);
		for(int i = 0; i < advPer.getSize(); ++i) {
			double temp = advPer.getNoise(i);
			//System.out.println(temp);
			g2.drawRect(i * 1, 0, 1, (int)(200 * (temp + 1)));
		}
		g2.setColor(Color.RED);
		g2.drawLine(0, 200, 1000, 200);
		g2.drawLine(0, 400, 1000, 400);
		/*
		for(int i = 0; i < perlin1D.getSize(); ++i) {
			g2.fillRect(1, (int)(10 * (perlin1D.getNoise(i) + 1)), 1, 1);
		}
		*/
		return image;
	}
}
