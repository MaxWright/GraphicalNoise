package advNoise1D;

import coherentNoise1D.GradientVectorNoise1D;
import coherentNoise1D.Noise1D;

/**
 * This class defines a bulk of functionality for 1D noise styles that
 * implements the concepts of frequency, octaves, and persistence.
 * 
 * @extends Noise1D
 * 
 * @abstract
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public abstract class AdvGradientVectorNoise1D extends Noise1D {
	/**
	 * An array the holds the octaves of which style of gradient vector noise we
	 * are using.
	 */
	private GradientVectorNoise1D[] noiseOctaves;
	/**
	 * This integer will keep track of which is the last a new octave has been
	 * saved to.
	 */
	private int currentIndex = 0;
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
	 * The sum of all the maximum amplitudes possible from each octave. This
	 * helps the getSum function assure that the value will be in the range of
	 * [-1, 1].
	 */
	private double amplitudeSum;

	/**
	 * This constructor populates the gradient vectors of the noise and defines
	 * the length of the noise.
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
	 * @extends Noise1D
	 */
	AdvGradientVectorNoise1D(int frequency, int length, int octaves,
			double persistence) throws IllegalArgumentException {
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

		noiseOctaves = new GradientVectorNoise1D[octaves];
		this.persistence = persistence;
		this.length = length;
		this.frequency = frequency;
		this.octaves = octaves;
		// Calculate the sum of the amplitudes now to avoid overhead later when
		// finding sums.
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
	 * @return The sum of the values of the noise at the octaves in the range of [-1, 1].
	 * @throws IndexOutOfBoundsException
	 *             If access an index outside of the noise. -OR- If the array of
	 *             octaves has not been completely populated.
	 */
	protected double getSum(int x) throws IndexOutOfBoundsException,
			ArithmeticException {
		if (currentIndex != octaves) {
			throw new IndexOutOfBoundsException(
					"Accessing null object. Please populate all octaves that have not been populates with a noise.");
		}
		inBounds(x);
		// This double will hold the sum of all octaves.
		double sum = 0;
		// The first octave has an amplitude of one.
		double amplitude = 1;
		// For every octave, calculate the sum.
		for (int i = 0; i < octaves; ++i) {
			sum += noiseOctaves[i].getNoise(x) / amplitude;
			// Apply the persistence to the amplitude for the next octave.
			amplitude *= persistence;
		}
		/*
		 * Doubles are tricky. The absolute value of sum should never be greater
		 * than the amplitudeSum, but doubles have their own agenda.
		 */
		if (sum > amplitudeSum) {
			amplitudeSum = sum;
			return 1;
		} else if (-sum > amplitudeSum) {
			amplitudeSum = -sum;
			return -1;
		} else {
			return sum / amplitudeSum;
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
	protected void populateNextIndex(GradientVectorNoise1D toAdd)
			throws IndexOutOfBoundsException {
		if (currentIndex >= octaves) {
			throw new IndexOutOfBoundsException("Cannot add more octaves");
		}
		noiseOctaves[currentIndex++] = toAdd;
	}

	/**
	 * This function tells if the given index is within bounds of the noise.
	 * 
	 * @param x
	 *            Is the index from the start of the noise as an integer.
	 * @throws IndexOutOfBoundsException
	 *             If the value brought in is outside the bounds of the noise.
	 */
	protected void inBounds(int x) throws IndexOutOfBoundsException {
		if (x < 0 || x >= length) {
			throw new IndexOutOfBoundsException("x at " + x
					+ " is not within bounds of [0," + length + ")");
		}
	}

	/**
	 * @return The length of the noise as an integer.
	 */
	public int getSize() {
		return length;
	}

	/**
	 * @return The number of octaves in the noise as an integer.
	 */
	protected int getOctaves() {
		return octaves;
	}

	/**
	 * @return The frequency of the first octave as an integer.
	 */
	protected int getFrequency() {
		return frequency;
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
	public abstract double getNoise(int x) throws IndexOutOfBoundsException;
}
