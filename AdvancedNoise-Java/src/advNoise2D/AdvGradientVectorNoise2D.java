package advNoise2D;

import utilities.NoiseChecks;
import coherentNoise2D.GradientVectorNoise2D;
import coherentNoise2D.Noise2D;

/**
 * This class defines a bulk of functionality for 2D noise styles that
 * implements the concepts of frequency, octaves, and persistence.
 * 
 * @extends Noise2D
 * 
 * @abstract
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public abstract class AdvGradientVectorNoise2D extends Noise2D {
	/**
	 * An array the holds the octaves of which style of gradient vector noise we
	 * are using.
	 */
	private GradientVectorNoise2D[] noiseOctaves;
	/**
	 * This integer will keep track of which is the last a new octave has been
	 * saved to.
	 */
	private int currentIndex = 0;
	/**
	 * The frequency of the first octave on the width as an integer.
	 */
	private int frequencyWidth;
	/**
	 * The frequency of the first octave on the height as an integer.
	 */
	private int frequencyHeight;
	/**
	 * The length of the noise as an integer.
	 */
	private int width;
	/**
	 * The length of the noise as an integer.
	 */
	private int height;
	/**
	 * The number of octaves as an integer
	 */
	private int octaves;
	/**
	 * The persistence that alters the scale of the octaves.
	 */
	private double persistence;
	/**
	 * The sum of all the maximum amplitudes possible from each octave. This
	 * helps the getSum function assure that the value will be in the range of
	 * [-1, 1].
	 */
	private double amplitudeSum;

	/**
	 * 
	 * @param frequencyWidth
	 *            The number of sections on the first octave's x-axis as an
	 *            integer.
	 * @param frequencyHeight
	 *            The number of sections on the first octave's y-axis as an
	 *            integer.
	 * @param width
	 *            The width of the noise as an integer.
	 * @param height
	 *            The height of the noise as an integer.
	 * @param octaves
	 *            The number of octaves in the noise as an integer.
	 * @param persistence
	 *            How quickly the amplitude of each octave descends as a double.
	 * @throws IllegalArgumentException
	 *             <p>
	 *             If the width or height is less than or equal to zero.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             If either of the frequencies is less than or equal to zero.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             If the amount of octaves are so high that one of the
	 *             frequencies will exceed the length.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             See
	 *             {@link utilities.NoiseChecks#checkAttributes(int, double)}
	 * @extends Noise2D
	 */
	AdvGradientVectorNoise2D(int frequencyWidth, int frequencyHeight,
			int width, int height, int octaves, double persistence)
			throws IllegalArgumentException {
		NoiseChecks.checkAttributes(octaves, persistence);
		/*
		 * Check and make sure the user input is appropriate for the
		 * functionality of this class.
		 */
		if (width <= 0) {
			throw new IllegalArgumentException(
					"The width must be greater than zero.");
		}
		if (height <= 0) {
			throw new IllegalArgumentException(
					"The height must be greater than zero.");
		}
		if (frequencyWidth <= 0) {
			throw new IllegalArgumentException(
					"The frequency on the width must be greater than zero");
		}
		if (frequencyHeight <= 0) {
			throw new IllegalArgumentException(
					"The frequency on the height must be greater than zero");
		}
		NoiseChecks.checkAttributes(octaves, persistence);
		/*
		 * Find if the number of octaves is too great for the width of the
		 * noise.
		 */
		if (frequencyWidth * Math.pow(2, octaves) > width) {
			throw new IllegalArgumentException(
					"The number of octaves is too large for the frequency given on the width.");
		}
		/*
		 * Find if the number of octaves is too great for the height of the
		 * noise.
		 */
		if (frequencyHeight * Math.pow(2, octaves) > height) {
			throw new IllegalArgumentException(
					"The number of octaves is too large for the frequency given on the height.");
		}
		noiseOctaves = new GradientVectorNoise2D[octaves];
		this.persistence = persistence;
		this.width = width;
		this.height = height;
		this.frequencyWidth = frequencyWidth;
		this.frequencyHeight = frequencyHeight;
		this.octaves = octaves;
		/*
		 * Calculate the sum of the amplitudes now to avoid overhead later when
		 * finding sums.
		 */
		amplitudeSum = 0;
		double amplitude = 1;
		for (int i = 0; i < octaves; ++i) {
			amplitudeSum += amplitude;
			amplitude *= persistence;
		}
	}

	/**
	 * This function calculates the sum of the values of the noise at the
	 * octaves.
	 * 
	 * @param x
	 *            The index from the start of the noise as an integer.
	 * @return The sum of the values of the noise at the octaves in the range of
	 *         [-1, 1].
	 * @throws IndexOutOfBoundsException
	 *             If access an index outside of the noise. -OR- If the array of
	 *             octaves has not been completely populated.
	 */
	protected double getSum(int x, int y) throws IndexOutOfBoundsException,
			ArithmeticException {
		if (currentIndex != octaves) {
			throw new IndexOutOfBoundsException(
					"Accessing null object. Please populate all octaves that have not been populates with a noise.");
		}
		inBounds(x, y);
		// This double will hold the sum of all octaves.
		double sum = 0;
		// The first octave has an amplitude of one.
		double amplitude = 1;
		// For every octave, calculate the sum.
		for (int i = 0; i < octaves; ++i) {
			sum += noiseOctaves[i].getNoise(x, y) * amplitude;
			// Apply the persistence to the amplitude for the next octave.
			amplitude *= persistence;
		}
		return sum / amplitudeSum;
	}

	/**
	 * 
	 * This function determined is the point given as (x, y) is within the
	 * bounds of the noise.
	 * 
	 * @param x
	 *            - The index of the distance vector needed relative to the
	 *            start of the noise as an integer on the x-axis.
	 * @param y
	 *            - The index of the distance vector needed relative to the
	 *            start of the noise as an integer on the y-axis.
	 * @throws IndexOutOfBoundsException
	 *             If the index of noise is outside of the bounds of the noise.
	 */
	protected void inBounds(int x, int y) throws IndexOutOfBoundsException {
		if (x < 0 || x >= width) {
			throw new IndexOutOfBoundsException("x at " + x
					+ " is not within bounds of [0," + width + ")");
		}
		if (y < 0 || y >= height) {
			throw new IndexOutOfBoundsException("y at " + y
					+ " is not within bounds of [0," + height + ")");
		}
	}

	/**
	 * This function populates the next octave with the noise brought in as a
	 * parameter.
	 * 
	 * @param toAdd
	 *            An initialized child of GradientVectorNoise1D.
	 * @throws IndexOutOfBoundsException
	 *             If trying to place another octave onto the array that is
	 *             already full of octaves.
	 */
	protected void populateNextIndex(GradientVectorNoise2D toAdd)
			throws IndexOutOfBoundsException {
		if (currentIndex >= octaves) {
			throw new IndexOutOfBoundsException("Cannot add more octaves");
		}
		noiseOctaves[currentIndex++] = toAdd;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * This function must add the octaves to the array stores in this class
	 * using the opulateNextIndex() function.
	 * 
	 * @throws IllegalArgumentException
	 *             If the index being added is not within the bounds of the
	 *             array of octaves.
	 */
	protected abstract void populateArray() throws IllegalArgumentException;

	@Override
	public abstract double getNoise(int x, int y)
			throws IndexOutOfBoundsException;

	/**
	 * 
	 * @return The frequency on the x-axis.
	 */
	protected int getFrequencyWidth() {
		return frequencyWidth;
	}
	
	/**
	 * 
	 * @return The frequency on the y-axis.
	 */
	protected int getFrequencyHeight() {
		return frequencyHeight;
	}

	/**
	 * @return The number of octaves in the noise as an integer.
	 */
	protected int getOctaves() {
		return octaves;
	}


}
