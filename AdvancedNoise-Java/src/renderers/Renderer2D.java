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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import coherentNoise1D.Noise1D;
import noise.PerlinNoiseMW;
import noise.ScalesMW;
import noise.SimplexNoiseMW;
import noise.SquaresMW;
import noise.TrianglesMW;
import noise.WoodMW;


public class Renderer2D extends Renderer {

	public Renderer2D(int frequency, int length, int octaves, double persistence)
			throws IllegalArgumentException {
		super(frequency, length, octaves, persistence);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BufferedImage getGraphic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetGraphic(Noise noiseType) {
		// TODO Auto-generated method stub

	}


}
