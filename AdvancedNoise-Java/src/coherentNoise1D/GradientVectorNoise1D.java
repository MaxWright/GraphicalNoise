package coherentNoise1D;

import java.util.Random;

/**
 * This class provides common functionality between all 1D noise styles that
 * utilize gradient vectors at set intervals in a line.
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
public abstract class GradientVectorNoise1D extends Noise1D {
	/**
	 * This variable represents the length of the noise as an integer.
	 */
	private int length;
	/**
	 * The number of individual units inside a section of the noise.
	 */
	private int unitsPerSection;
	/**
	 * A series of vectors designated by the first parameter of the array. The
	 * vectors are represented as doubles and the length of each vector equals
	 * the square root of two.
	 */
	private double[][] gradientVectorsValues = {
			
			// + or - 0, 90, 180 degrees
			{ 1.41421, 0 },
			{ 0, 1.41421 },
			{ -1.41421, 0 },
			{ 0, -1.41421 },
			 
			// + or - 45, 135 degrees
			{ 1, 1 },
			{ 1, -1 },
			{ -1, 1 },
			{ -1, -1 },

			// + or - 22.5 degrees and 67.5 degrees
			{ 0.541196, 1.30656 },
			{ -0.541196, 1.30656 },
			{ 0.541196, -1.30656 },
			{ -0.541196, -1.30656 },
			// + or - 112.5 degrees and 157.5 degrees
			{ 1.30656, 0.541196 },
			{ -1.30656, 0.541196 },
			{ 1.30656, -0.541196 },
			{ -1.30656, -0.541196 },
			// 11.25, 33.75, 56.25, and 78.75 degrees and all other appropriate
			// equivalences in each quadrant of the unit circle.
			{ 1.38704, 0.275899 }, { -1.38704, 0.275899 },
			{ 1.38704, -0.275899 }, { -1.38704, -0.275899 },
			{ 0.275899, 1.38704 }, { -0.275899, 1.38704 },
			{ 0.275899, -1.38704 }, { -0.275899, -1.38704 },
			{ 0.785695, 1.17588 }, { -0.785695, 1.17588 },
			{ 0.785695, -1.17588 }, { -0.785695, -1.17588 },
			{ 1.17588, 0.785695 }, { -1.17588, 0.785695 },
			{ 1.17588, -0.785695 }, { -1.17588, -0.785695 }, };

	/**
	 * An array of integers where each integer maps to a particular gradient
	 * vector defined in the array of gradient values.
	 */
	private int[] vectorGradients;

	/**
	 * This constructor populates the gradient vectors of the noise and defines
	 * the length of the noise.
	 * 
	 * @param sections
	 *            The number of independent sections in the noise as an integer.
	 * @param length
	 *            The length of the noise to be made as an integer.
	 * @throws IllegalArgumentException
	 *             If the length is less than or equal to zero.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             If the number of sections is less than or equal to zero of is
	 *             greater than the length.
	 * @extends Noise1D
	 */
	public GradientVectorNoise1D(int sections, int length)
			throws IllegalArgumentException {
		super();
		if (length <= 0) {
			throw new IllegalArgumentException(
					"The length must be greater than zero.");
		}
		if (sections <= 0 || sections > length) {
			throw new IllegalArgumentException(
					"The number of sections must be in the range of (0, "
							+ length + "]");
		}
		/*
		 * Initialize these variables before manipulation. The sections variable
		 * represents the desired number of sections the user wants the noise to
		 * have. The length represents the desired length.
		 */
		this.unitsPerSection = length / sections;
		this.length = length;
		/*
		 * Because the amount of desired sections may not be evenly divisible by
		 * the desired length, adjust the number of sections there actually need
		 * to be.
		 */
		while (length / unitsPerSection >= sections) {
			++sections;
		}
		/*
		 * The sections variable is now being used to determine how many
		 * gradient vectors are needed. One vectors gradient is needed at each
		 * corner of each section. Two sections will share one corner. Add one
		 * more to the section variable to account for the last corner.
		 */
		++sections;
		vectorGradients = new int[sections];
		Random rand = new Random();
		for (int i = 0; i < sections; ++i) {
			vectorGradients[i] = rand.nextInt(gradientVectorsValues.length);
		}
	}

	/**
	 * This function takes in an index relative to the start of the noise and
	 * returns the length from the closest section to the left to that position.
	 * 
	 * @param x
	 *            The index of the distance vector needed relative to the start
	 *            of the noise as an integer.
	 * @return The distance from the start of the local section to that point.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on {@link #inBounds()}
	 */
	protected double getDistanceVector(int x) throws IndexOutOfBoundsException {
		// Check to see if the index is within bounds.
		inBounds(x);
		// Find where in the section the index belongs.
		x -= x / unitsPerSection * unitsPerSection;
		// Return the distance from the left hand part of the section to that
		// position.
		return (double) x / (unitsPerSection - 1);
	}

	/**
	 * This function takes in an index relative to the start of the noise and
	 * returns the length from the closest section to the right to that
	 * position.
	 * 
	 * @param x
	 *            The index of the distance vector needed relative to the start
	 *            of the noise as an integer.
	 * @return The distance from the start of the local section to that point.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on
	 *             {@link GradientVectorNoise1D#getDistanceVector(int)}
	 */
	protected double getOpDistanceVector(int x)
			throws IndexOutOfBoundsException {
		return -(1 - getDistanceVector(x));
	}

	/**
	 * This function finds the section the index is located in.
	 * 
	 * @param x
	 *            Represents an index from the start of the noise as an integer.
	 * @return The section the noise is in as an integer.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on
	 *             {@link GradientVectorNoise1D#getDistanceVector(int)}
	 */
	protected int getSection(int x) throws IndexOutOfBoundsException {
		inBounds(x);
		return x / unitsPerSection;
	}

	/**
	 * This function performs a dot product operation between the gradient
	 * vector at the given index and the dimensions given of the index. Since
	 * the y distance will only ever be zero only the x distance is needed.
	 * 
	 * @param index
	 *            The corner that is being dotted with the entered distance as
	 *            an integer.
	 * @param x
	 *            The value of the distance vector in the x direction as a
	 *            double.
	 * @return A double representing the result of the dot product of the
	 *         gradient vector at position index and the distance vector.
	 * @throws IndexOutOfBoundsException
	 *             If the index of the vector gradient entered is not an
	 *             appropriate index.
	 * @throws IllegalArgumentException
	 *             If the values entered for the x distance vector values is
	 *             outside the bounds of [-1, 1].
	 */
	protected double dotProduct(int index, double x)
			throws IndexOutOfBoundsException, IllegalArgumentException {
		// Check that the index that is within bounds of the number of sections.
		if (index < 0 || index >= vectorGradients.length) {
			throw new IndexOutOfBoundsException("Index: at " + index
					+ " is not within bounds of [0," + vectorGradients.length
					+ ")");
		}
		// If the x value is not within the bounds of [-1, 1].
		if (x < -1 || x > 1) {
			throw new IllegalArgumentException(
					"x value entered whose value is " + x
							+ " is not within bounds of [-1, 1].");
		}
		/*
		 * Return the dot product calculation of the gradient vector and the
		 * distance vector.
		 */
		return gradientVectorsValues[vectorGradients[index]][0] * x;
	}

	/**
	 * This function checks if the entered index is within the bounds of the
	 * noise.
	 * 
	 * @param x
	 *            Represents an index from the start of the noise as an integer.
	 * @throws IndexOutOfBoundsException
	 *             If the index of noise is outside of the bounds of the noise.
	 */
	protected void inBounds(int x) throws IndexOutOfBoundsException {
		if (x < 0 || x >= length) {
			throw new IndexOutOfBoundsException("x at " + x
					+ " is not within bounds of [0," + length + ")");
		}
	}

	/**
	 * This function exists for the Scales1D class and Wood1D class.
	 * 
	 * @param index
	 *            The corner that is being dotted with the entered distance as
	 *            an integer.
	 * @return The value of the x of the gradient vector at the corner given.
	 */
	protected double getGradientVal(int index) throws IndexOutOfBoundsException {
		// Check that the index that is within bounds of the number of sections.
		if (index < 0 || index >= vectorGradients.length) {
			throw new IndexOutOfBoundsException("Index: at " + index
					+ " is not within bounds of [0," + vectorGradients.length
					+ ")");
		}
		return gradientVectorsValues[vectorGradients[index]][0];
	}

	@Override
	public int getSize() {
		return length;
	}

	@Override
	public abstract double getNoise(int x) throws IndexOutOfBoundsException;

}
