package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import renderers.Noise;
import renderers.Renderer1D;
import renderers.Renderer2D;

/**
 * This class creates a JComponent that displays graphical noise.
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class RendererDisplay extends JComponent {
	/**
	 * An instance of an object that will hold and render 1D noise.
	 */
	private Renderer1D renderer1D = new Renderer1D(10, 1000, 1, 1);
	/**
	 * An instance of an object that will hold and render 2D noise.
	 */
	private Renderer2D renderer2D = new Renderer2D(10, 1000, 1, 1);
	private int dimension = 1;
	
	/**
	 * This function belongs to the JFrame. Do not touch it.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Set the background as black.
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Sets the image of the gradient to the middle of the JComponent
		BufferedImage temp = renderer1D.getGraphic();
		int width = getShift(getWidth(), temp.getWidth());
		int height = getShift(getHeight(), temp.getHeight());
		g.drawImage(temp, width, height, null);
	}
	
	public BufferedImage getGraphic() {
		return renderer1D.getGraphic();
	}
	
	public void resetGraphic(Noise noiseType) {
		renderer1D.resetGraphic(noiseType);
		renderer2D.resetGraphic(noiseType);
		repaint();
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
	
	public void setDimension(int val) throws IllegalArgumentException {
		if(val < 1 || val > 2) {
			throw new IllegalArgumentException("Dimensions must be between zero and one.");
		}
		dimension = val;
	}
	public void setRed(int val) {
		renderer1D.setRed(val);
		renderer2D.setRed(val);
	}
	
	public void setGreen(int val) {
		renderer1D.setGreen(val);
		renderer2D.setGreen(val);
	}
	
	public void setBlue(int val) {
		renderer1D.setBlue(val);
		renderer2D.setBlue(val);
	}
}
