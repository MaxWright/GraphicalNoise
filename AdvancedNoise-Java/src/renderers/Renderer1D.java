/*
filename    Renderer.java
author      Max Wright
uploaded    12/20/2017

Brief Description:
  
  
  © 2017 Max Wright. All rights reserved. 
  Free for use. Use at your own risk, Max Wright will not be liable for any
  damages caused by this code.
 */
package renderers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import advNoise1D.AdvPerlin1D;
import coherentNoise1D.Noise1D;
import coherentNoise1D.Perlin1D;
import coherentNoise1D.Scales1D;
import coherentNoise1D.Simplex1D;
import coherentNoise1D.Squares1D;
import coherentNoise1D.Triangles1D;
import coherentNoise1D.Wood1D;

public class Renderer1D extends Renderer {
	/*
	 * private PerlinNoise1D perlin1D = new PerlinNoise1D(10,1000); private
	 * Squares1D squares1D = new Squares1D(3, 1000); private Simplex1D simplex1D
	 * = new Simplex1D(3, 1000); private Triangles1D triangles1D = new
	 * Triangles1D(3,1000); private Scales1D scales1D = new Scales1D(3, 1000);
	 * private Wood1D wood1D = new Wood1D(3, 1000);
	 * 
	 * private AdvPerlin1D advPer = new AdvPerlin1D(10, 1000, 3, .825);
	 */
	Noise1D toDraw = new Perlin1D(10, 1000);

	public Renderer1D(int frequency, int length, int octaves, double persistence)
			throws IllegalArgumentException {
		super(frequency, length, octaves, persistence);
	}

	@Override
	public BufferedImage getGraphic() {
		int size = toDraw.getSize();
		BufferedImage image = new BufferedImage(size, 400,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(getColor(1));
		for (int i = 0; i < size; ++i) {
			double temp = toDraw.getNoise(i);
			int length = (int)(200 * (temp + 1));
			g2.drawRect(i, 400 - length, 1, length);
		}
		return image;
	}

	@Override
	public void resetGraphic(Noise noiseType) {
		switch (noiseType) {
		case PERLIN:
			toDraw = new Perlin1D(super.getFrequency(), super.getLength());
			break;
		case SIMPLEX:
			toDraw = new Simplex1D(super.getFrequency(), super.getLength());
			break;
		case SCALES:
			toDraw = new Scales1D(super.getFrequency(), super.getLength());
			break;
		case SQUARES:
			toDraw = new Squares1D(super.getFrequency(), super.getLength());
			break;
		case TRIANGLES:
			toDraw = new Triangles1D(super.getFrequency(), super.getLength());
			break;
		case WOOD:
			toDraw = new Wood1D(super.getFrequency(), super.getLength());
			break;
		case ADV_PERLIN:
			toDraw = new AdvPerlin1D(super.getFrequency(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_SIMPLEX:
			break;
		case ADV_SCALES:
			break;
		case ADV_SQUARES:
			break;
		case ADV_TRIANGLES:
			break;
		case ADV_WOOD:
			break;
		}
	}
}
