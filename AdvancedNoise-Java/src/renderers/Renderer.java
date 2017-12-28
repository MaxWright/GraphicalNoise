package renderers;

import java.awt.Color;
import java.awt.image.BufferedImage;

import coherentNoise1D.GradientVectorNoise1D;

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

	private Noise selected = Noise.PERLIN;

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

	public abstract BufferedImage getGraphic();
	public abstract void resetGraphic(Noise noiseType);
	
	protected Color getColor(double scale) throws IllegalArgumentException {
		if (scale > 1 || scale < -1) {
			throw new IllegalArgumentException(
					"The scale value must be in the range of [-1, 1].");
		}
		return new Color((int) (red + red * scale), (int) (green + green
				* scale), (int) (blue + blue * scale));
	}

	void setSelected(Noise toSet) {
		selected = toSet;
	}

	Noise getSelected() {
		return selected;
	}

	public void setRed(int val) throws IllegalArgumentException {
		if (val > 127 || val < 0) {
			throw new IllegalArgumentException(
					"The red value must be in the range of [0, 127].");
		}
		red = val;
	}

	public void setGreen(int val) throws IllegalArgumentException {
		if (val > 127 || val < 0) {
			throw new IllegalArgumentException(
					"The green value must be in the range of [0, 127].");
		}
		green = val;
	}

	public void setBlue(int val) throws IllegalArgumentException {
		if (val > 127 || val < 0) {
			throw new IllegalArgumentException(
					"The blue value must be in the range of [0, 127].");
		}
		blue = val;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getOctaves() {
		return octaves;
	}

	public void setOctaves(int octaves) {
		this.octaves = octaves;
	}

	public double getPersistence() {
		return persistence;
	}

	public void setPersistence(double persistence) {
		this.persistence = persistence;
	}
}
