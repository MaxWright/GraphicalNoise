package renderers;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * This class defines some initial functionality the each renderer must have.
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public abstract class Renderer {
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
	 * The frequency of the first octave as an integer.
	 */
	private int frequency;
	/**
	 * The length of the noise as an integer.
	 */
	private int length;
	/**
	 * The number of octaves as an integer
	 */
	private int octaves;
	/**
	 * The persistence that alters the scale of the octaves.
	 */
	private double persistence;

	/**
	 * The default constructor for the Renderer class.
	 * 
	 * @param frequency
	 *            The number of independent sections in the noise as an integer.
	 * @param length
	 *            The length of the noise to be made as an integer.
	 * @param octaves
	 *            The number of octaves in the noise as an integer.
	 * @param persistence
	 *            How quickly the amplitude of each octave descends as a double.
	 * @throws IllegalArgumentException
	 *             <p>
	 *             If the length is less than or equal to zero.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             If the number of frequency is less than or equal to zero.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             If the amount of octaves are so high the the frequency will
	 *             exceed the length.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             If the persistence equals zero.
	 */
	public Renderer(int frequency, int length, int octaves, double persistence)
			throws IllegalArgumentException {
		/*
		 * Check and make sure the user input is appropriate for the
		 * functionality of this class.
		 */
		if (length <= 0) {
			throw new IllegalArgumentException(
					"The length must be greater than zero.");
		}
		if (frequency <= 0) {
			throw new IllegalArgumentException(
					"The frequency must be greater than zero");
		}
		if (octaves <= 0) {
			throw new IllegalArgumentException(
					"The number of octaves must be greater than zero.");
		}
		/*
		 * Find the power value of base two to reach the frequency, add one
		 * because 2/2 = 1, which is an acceptable value. Each octave determines
		 * its frequency by dividing the frequency of the last octave by two,
		 * omitting the first octave.
		 */
		if (frequency * Math.pow(2, octaves) > length) {
			throw new IllegalArgumentException(
					"The number of octaves is too large for the frequency given.");
		}
		if (persistence == 0) {
			throw new IllegalArgumentException(
					"The persistance cannot be zero.");
		}
		this.setPersistence(persistence);
		this.setLength(length);
		this.setFrequency(frequency);
		this.setOctaves(octaves);
	}

	/**
	 * Returns an image of the noise the renderer is set to.
	 * 
	 * @return An image of the noise the renderer is set to as a BufferedImage.
	 * @throws ArithmeticException
	 *             If the noise being rendered has a noise value that is not in
	 *             the range of [-1, 1].
	 */
	public abstract BufferedImage getGraphic() throws ArithmeticException;

	/**
	 * This function causes the noise to be re-rendered.
	 * 
	 * @param noiseType
	 *            The noise to be re-rendered.
	 */
	public abstract void resetGraphic(Noise noiseType);

	/**
	 * Gets the color based on the scale entered and the internal settings of
	 * this class.
	 * 
	 * @param scale
	 * @return A new Color who color is dependent on the scale and the intensity
	 *         of each color set in this class.
	 * @throws IllegalArgumentException
	 *             If the noise being rendered has a noise value that is not in
	 *             the range of [-1, 1].
	 */
	protected Color getColor(double scale) throws IllegalArgumentException {
		if (scale > 1 || scale < -1) {
			throw new IllegalArgumentException(
					"The scale value must be in the range of [-1, 1].");
		}
		/*
		return new Color((int) (red + red * scale), (int) (green + green
				* scale), (int) (blue + blue * scale));
		*/
		return new Color((int) (red + red * scale), (int) (green + green
				* scale), (int) (blue + blue * scale));
	}

	/**
	 * Sets the value of the red being shown in the image.
	 * 
	 * @param val
	 *            The new value of for the amount of red being displayed as an
	 *            integer.
	 * @throws IllegalArgumentException
	 *             If the value entered is not in the range of [0, 127].
	 */
	public void setRed(int val) throws IllegalArgumentException {
		if (val > 127 || val < 0) {
			throw new IllegalArgumentException(
					"The red value must be in the range of [0, 127].");
		}
		red = val;
	}

	/**
	 * Sets the value of the green being shown in the image.
	 * 
	 * @param val
	 *            The new value of for the amount of green being displayed as an
	 *            integer.
	 * @throws IllegalArgumentException
	 *             If the value entered is not in the range of [0, 127].
	 */
	public void setGreen(int val) throws IllegalArgumentException {
		if (val > 127 || val < 0) {
			throw new IllegalArgumentException(
					"The green value must be in the range of [0, 127].");
		}
		green = val;
	}

	/**
	 * Sets the value of the blue being shown in the image.
	 * 
	 * @param val
	 *            The new value of for the amount of blue being displayed as an
	 *            integer.
	 * @throws IllegalArgumentException
	 *             If the value entered is not in the range of [0, 127].
	 */
	public void setBlue(int val) throws IllegalArgumentException {
		if (val > 127 || val < 0) {
			throw new IllegalArgumentException(
					"The blue value must be in the range of [0, 127].");
		}
		blue = val;
	}

	/**
	 * 
	 * @return The frequency being used in this renderer as an integer.
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Sets the frequency of the noise to the given value.
	 * 
	 * @param frequency
	 *            The new frequency of the noise as an integer.
	 * @throws IllegalArgumentException
	 *             If the value entered is less than or equal to zero.
	 *             <p>
	 *             -OR-
	 *             <P>
	 *             If the value entered is too great when applied to the amount
	 *             of octaves in respect to the length of the noise.
	 */
	public void setFrequency(int frequency) throws IllegalArgumentException {
		if (frequency <= 0) {
			throw new IllegalArgumentException(
					"The frequency must be greater than zero");
		}
		/*
		 * Find the power value of base two to reach the frequency, add one
		 * because 2/2 = 1, which is an acceptable value. Each octave determines
		 * its frequency by dividing the frequency of the last octave by two,
		 * omitting the first octave.
		 */
		if (frequency * Math.pow(2, octaves) > length) {
			throw new IllegalArgumentException(
					"The number of octaves is too large for the frequency given.");
		}
		this.frequency = frequency;
	}

	/**
	 * 
	 * @return The length being used in this renderer as an integer.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the length of the noise to the new value.
	 * 
	 * @param length
	 *            The new length of the noise as an integer.
	 * @throws IllegalArgumentException
	 *             If length is less than or equal to zero.
	 */
	public void setLength(int length) throws IllegalArgumentException {
		if (length <= 0) {
			throw new IllegalArgumentException(
					"The length must be greater than zero.");
		}
		this.length = length;
	}

	/**
	 * 
	 * @return The amount of octaves being used in this renderer as an integer.
	 */
	public int getOctaves() {
		return octaves;
	}

	/**
	 * Sets the new number of octaves to the given value.
	 * 
	 * @param octaves
	 *            The new number of octaves as an integer.
	 * @throws IllegalArgumentException
	 *             If the number of octaves is less than or equal to zero.
	 *             <p>
	 *             -OR-
	 *             <P>
	 *             If the amount of octaves is too great for the frequency and
	 *             length.
	 */
	public void setOctaves(int octaves) throws IllegalArgumentException {
		if (octaves <= 0) {
			throw new IllegalArgumentException(
					"The number of octaves must be greater than zero.");
		}
		/*
		 * Find the power value of base two to reach the frequency, add one
		 * because 2/2 = 1, which is an acceptable value. Each octave determines
		 * its frequency by dividing the frequency of the last octave by two,
		 * omitting the first octave.
		 */
		if (frequency * Math.pow(2, octaves) > length) {
			throw new IllegalArgumentException(
					"The number of octaves is too large for the frequency given.");
		}
		this.octaves = octaves;
	}

	/**
	 * 
	 * @return The persistence being used in this renderer as a double.
	 */
	public double getPersistence() {
		return persistence;
	}

	/**
	 * Sets the persistence to the value given.
	 * 
	 * @param persistence
	 *            The new persistence as a double.
	 * @throws IllegalArgumentException
	 *             if the new persistence is equal to zero.
	 */
	public void setPersistence(double persistence)
			throws IllegalArgumentException {
		if (persistence == 0) {
			throw new IllegalArgumentException(
					"The persistance cannot be zero.");
		}
		this.persistence = persistence;
	}

	/**
	 * Calculates the maximum number of octaves possible for the set frequency
	 * and length.
	 * 
	 * @return The maximum number of octaves possible for the frequency set as
	 *         an integer.
	 */
	public int getMaxOctaves() {
		return (int) (Math.log(length / frequency) / Math.log(2));
	}

	/**
	 * Calculates the maximum frequency possible for the set frequency and
	 * length.
	 * 
	 * @return The maximum frequency possible for the frequency set as
	 *         an integer.
	 */
	public int getMaxFrequency() {
		return length / (int)Math.pow(2, octaves);
	}
}
