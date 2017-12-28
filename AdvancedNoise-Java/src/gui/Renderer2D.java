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
import noise.PerlinNoiseMW;
import noise.ScalesMW;
import noise.SimplexNoiseMW;
import noise.SquaresMW;
import noise.TrianglesMW;
import noise.WoodMW;

/**
 * 
 * This class holds an instance of a variety of different noises. As a
 * JComponent, it will paint the noise onto the JFrame.
 * 
 * @author Max Wright
 * @version 2.0
 */
public class Renderer2D extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This helps iterate through displaying different noise types.
	 */
	private int noiseType = 0;
	private SimplexNoiseMW simplex;
	private PerlinNoiseMW perlin;
	private SquaresMW squares;
	private ScalesMW scales;
	private WoodMW wood;
	private TrianglesMW triangles;

	/**
	 * The red value that is used in the noise. It provided both the base and
	 * the scale value. y = mx + b y = red * x + red where x is a value from 1
	 * to -1 given from the noise function being called.
	 */
	private int red = 127;
	/**
	 * The green value that is used in the noise. It provided both the base and
	 * the scale value. y = mx + b y = green * x + green where x is a value from
	 * 1 to -1 given from the noise function being called.
	 */
	private int green = 127;
	/**
	 * The blue value that is used in the noise. It provided both the base and
	 * the scale value. y = mx + b y = blue * x + blue where x is a value from 1
	 * to -1 given from the noise function being called.
	 */
	private int blue = 127;

	/**
	 * A constructor that takes in parameters to generate the initial noise
	 * classes.
	 * 
	 * @param gridSize
	 *            An integer value of larger squares in the noise on one side.
	 * @param squareSize
	 *            An integer value of one side of an independent square.
	 */
	public Renderer2D(int gridSize, int squareSize) {
		simplex = new SimplexNoiseMW(gridSize, squareSize);
		perlin = new PerlinNoiseMW(gridSize, gridSize, squareSize, squareSize);
		squares = new SquaresMW(gridSize, gridSize, squareSize, squareSize);
		scales = new ScalesMW(gridSize, squareSize);
		wood = new WoodMW(gridSize, squareSize);
		triangles = new TrianglesMW(gridSize, squareSize);
	}

	/**
	 * A function that recreated all noise classes with the given parameters.
	 * 
	 * @param gridSize
	 *            The number of larger squares in the noise on one side.
	 * @param squareSize
	 *            An integer value of one side of an independent square.
	 */
	public void reset(int gridSize, int squareSize) {
		simplex = new SimplexNoiseMW(gridSize, squareSize);
		perlin = new PerlinNoiseMW(gridSize, gridSize, squareSize, squareSize);
		squares = new SquaresMW(gridSize, gridSize, squareSize, squareSize);
		scales = new ScalesMW(gridSize, squareSize);
		wood = new WoodMW(gridSize, squareSize);
		triangles = new TrianglesMW(gridSize, squareSize);
		repaint();
	}

	/**
	 * This function changes which noise is being displayed when called to the
	 * next one.
	 */
	public void change(int changeTo) {
		if (changeTo > 5 || changeTo < 0) {
			noiseType = 0;
		} else {
			noiseType = changeTo;
		}
		repaint();
	}

	/**
	 * This function belongs to the JFrame. Do not touch it.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Set the background as black.
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Sets the image of the gradient to the middle of the JComponent
		BufferedImage temp = getGraphic();
		int width = getShift(getWidth(), temp.getWidth());
		int height = getShift(getHeight(), temp.getHeight());
		g.drawImage(temp, width, height, null);
	}

	/**
	 * This function takes in a dimension of the screen and a dimension of the
	 * image and returns how much of a shift on that dimension is necessary to
	 * center the image.
	 * 
	 * @param screenDim
	 *            represents the dimension of the screen as an int.
	 * @param imgDim
	 *            represents the dimension of the screen as an int.
	 * @return An integer representing how much of a shift is needed to center
	 *         something on the screen.
	 */
	private int getShift(int screenDim, int imgDim) {
		screenDim = (screenDim - imgDim) / 2;

		if (screenDim < 0) {
			screenDim = 0;
		}

		return screenDim;

	}

	/**
	 * 
	 * This function exists to help the getGraphic() function draw a pixel on an
	 * image.
	 * 
	 * @param g2
	 *            A Graphics2D object.
	 * @param scale
	 *            The double value from a noise function.
	 * @param y
	 *            An integer of the y coordinate of the point we are retrieving.
	 * @param x
	 *            An integer of the x coordinate of the point we are retrieving.
	 */
	private void paintPixel(Graphics2D g2, double scale, int y, int x) {
		Color someGray = new Color((int) (red + red * scale),
				(int) (green + green * scale), (int) (blue + blue * scale));
		g2.setColor(someGray);
		g2.fillRect(x, y, 1, 1);
	}

	/**
	 * This function represents the noise data in a graphical format.
	 * 
	 * @return A BufferedImage of the graphical representation of the noise
	 *         function being drawn.
	 */
	BufferedImage getGraphic() {

		/*
		 * Create an image as big as the component itself. This image will be
		 * painted to using the Graphics2D object and then this image will be
		 * painted to the fill the component size.
		 */
		BufferedImage image = new BufferedImage(1, 1,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2;

		switch (noiseType) {
		// Perlin
		case 0:
			int width = perlin.getWidth();
			int height = perlin.getHeight();
			image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			g2 = image.createGraphics();
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					double temp = perlin.getNoise(i, j);
					paintPixel(g2, temp, i, j);
				}
			}
			break;
		// Simplex
		case 1:
			height = simplex.getHeight();
			image = new BufferedImage(height, height,
					BufferedImage.TYPE_INT_ARGB);
			g2 = image.createGraphics();
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < height; ++j) {
					double temp = simplex.getNoise(i, j);
					paintPixel(g2, temp, i, j);
				}
			}
			break;
		// Squares
		case 2:
			width = squares.getWidth();
			height = squares.getHeight();
			image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			g2 = image.createGraphics();
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					double temp = squares.getNoise(i, j);
					paintPixel(g2, temp, i, j);
				}
			}
			break;
		// Scales
		case 3:
			height = scales.getHeight();
			image = new BufferedImage(height, height,
					BufferedImage.TYPE_INT_ARGB);
			g2 = image.createGraphics();
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < height; ++j) {
					double temp = scales.getNoise(i, j);
					paintPixel(g2, temp, i, j);
				}
			}
			break;
		// Wood
		case 4:
			height = wood.getHeight();
			image = new BufferedImage(height, height,
					BufferedImage.TYPE_INT_ARGB);
			g2 = image.createGraphics();
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < height; ++j) {
					double temp = wood.getNoise(i, j);
					paintPixel(g2, temp, i, j);
				}
			}
			break;
		// Triangles
		case 5:
			height = triangles.getHeight();
			image = new BufferedImage(height, height,
					BufferedImage.TYPE_INT_ARGB);
			g2 = image.createGraphics();
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < height; ++j) {
					double temp = triangles.getNoise(i, j);
					paintPixel(g2, temp, i, j);
				}
			}
			break;
		}
		return image;
	}

	void setRed(int val) {
		red = val;
		repaint();
	}

	void setGreen(int val) {
		green = val;
		repaint();
	}

	void setBlue(int val) {
		blue = val;
		repaint();
	}
}
