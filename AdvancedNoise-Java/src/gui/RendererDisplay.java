package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import renderers.Renderer;
import renderers.Noise;
import renderers.Renderer1D;
import renderers.Renderer2D;

/**
 * This class creates a JComponent that displays graphical noise. This class
 * manages the interaction between the GUI and the renderers, and also displays
 * which noise the user is requesting to be displayed.
 * 
 * @extends JComponent
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
	 * The serial version of this class as required by JComponent.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * An array of renderers where index 0 is the 1D renderer, index 1 is 2D
	 * renderer, etc.
	 */
	private Renderer[] renderers = { new Renderer1D(10, 1000, 1, 1),
			new Renderer2D(10, 1000, 1, 1) };
	/**
	 * This variable holds which dimension the screen is displaying as an
	 * integer. The integer value represents the number of dimensions.
	 */
	private int dimension = 1;

	/**
	 * Default constructor for the renderer display.
	 * 
	 * @extends JComponent
	 */
	public RendererDisplay() {

	}

	/**
	 * This function belongs to the JFrame and paints the graphic on the screen.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Set the background as black.
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Sets the image of the gradient to the middle of the JComponent
		BufferedImage temp;
		temp = renderers[dimension - 1].getGraphic();
		int width = getShift(getWidth(), temp.getWidth());
		int height = getShift(getHeight(), temp.getHeight());
		g.drawImage(temp, width, height, null);
	}

	/**
	 * This function returns the graphic noise being displayed.
	 * 
	 * @return The noise graphic being displayed as a buffered image.
	 */
	public BufferedImage getGraphic() {
		return renderers[dimension - 1].getGraphic();
	}

	/**
	 * This function causes the renderers to switch to the new specified noise
	 * or to reinitialize the noise they currently have saved.
	 * 
	 * @param noiseType
	 */
	public void resetGraphic(Noise noiseType) {
		renderers[0].resetGraphic(noiseType);
		renderers[1].resetGraphic(noiseType);
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

	/**
	 * This function sets the dimension being displayed.
	 * 
	 * @param val
	 *            The dimension desired to be displayed as an integer.
	 * @throws IllegalArgumentException
	 *             If the value is not within the bounds of [1, 2].
	 */
	public void setDimension(int val) throws IllegalArgumentException {
		if (val < 1 || val > 2) {
			throw new IllegalArgumentException(
					"Dimensions must be between zero and one.");
		}
		dimension = val;
	}

	/**
	 * Sets the value of the red being shown in the image.
	 * 
	 * @param val The new value of for the amount of red being displayed as an integer.
	 * @throws IllegalArgumentException
	 *             If the value is not acceptable for {@link renderers.Renderer#setRed(int)}.
	 */
	public void setRed(int val) throws IllegalArgumentException {
		renderers[0].setRed(val);
		renderers[1].setRed(val);
	}

	/**
	 * Sets the value of the green being shown in the image.
	 * 
	 * @param val The new value of for the amount of green being displayed as an integer.
	 * @throws IllegalArgumentException
	 *             If the value is not acceptable for {@link renderers.Renderer#setGreen(int)}.
	 */
	public void setGreen(int val) throws IllegalArgumentException {
		renderers[0].setGreen(val);
		renderers[1].setGreen(val);
	}

	/**
	 * Sets the value of the blue being shown in the image.
	 * 
	 * @param val The new value of for the amount of blue being displayed as an integer.
	 * @throws IllegalArgumentException
	 *             If the value is not acceptable for {@link renderers.Renderer#setBlue(int)}.
	 */
	public void setBlue(int val) throws IllegalArgumentException {
		renderers[0].setBlue(val);
		renderers[1].setBlue(val);
	}
}
