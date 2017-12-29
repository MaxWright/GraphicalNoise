package coherentNoise2D;

import java.util.Random;

/**
 * This class provides common functionality between all @D noise styles that
 * utilize gradient vectors at set intervals in a grid.
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
public abstract class GradientVectorNoise2D {
	/**
	 * This variable represents the width of the noise as an integer.
	 */
	private int width;
	/**
	 * This variable represents the height of the noise as an integer.
	 */
	private int height;
	/**
	 * The number of individual units per section on the width.
	 */
	private int unitsPerSectionWidth;
	/**
	 * The number of individual units per section on the Height.
	 */
	private int unitsPerSectionHeight;
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
	 * An array of array integers where each integer maps to a particular
	 * gradient vector defined in the array of gradient values.
	 */
	private int[][] vectorGradients;

	public GradientVectorNoise2D(int sectionsOnWidth, int sectionsOnHeight,
			int width, int height) throws IllegalArgumentException {
		super();
		if (width <= 0) {
			throw new IllegalArgumentException(
					"The width must be greater than zero.");
		}
		if (height <= 0) {
			throw new IllegalArgumentException(
					"The height must be greater than zero.");
		}
		if (sectionsOnWidth <= 0 || sectionsOnWidth > width) {
			throw new IllegalArgumentException(
					"The number of sections on the width must be in the range of (0, "
							+ width + "]");
		}
		if (sectionsOnHeight <= 0 || sectionsOnHeight > height) {
			throw new IllegalArgumentException(
					"The number of sections on the height must be in the range of (0, "
							+ height + "]");
		}
		/*
		 * Initialize these variables before manipulation. The sections
		 * variables represents the desired number of sections the user wants
		 * the noise to have.
		 */
		this.unitsPerSectionWidth = width / sectionsOnWidth;
		this.unitsPerSectionHeight = height / sectionsOnHeight;
		this.width = width;
		this.height = height;
		/*
		 * Because the amount of desired sections may not be evenly divisible by
		 * the desired length, adjust the number of sections there actually need
		 * to be.
		 */
		while (width / unitsPerSectionWidth >= sectionsOnWidth) {
			++sectionsOnWidth;
		}
		while (height / unitsPerSectionHeight >= sectionsOnHeight) {
			++sectionsOnHeight;
		}
		/*
		 * The sections variable is now being used to determine how many
		 * gradient vectors are needed. One vectors gradient is needed at each
		 * corner of each section. Gradient vectors will be shared with the
		 * sections next to each other.
		 */
		++sectionsOnWidth;
		++sectionsOnHeight;
		vectorGradients = new int[sectionsOnHeight][sectionsOnWidth];
		Random rand = new Random();
		for (int i = 0; i < sectionsOnHeight; ++i) {
			for (int j = 0; i < sectionsOnWidth; ++j) {
				vectorGradients[i][j] = rand
						.nextInt(gradientVectorsValues.length);
			}
		}
	}

	/**
	 * This function takes in an index relative to the start of the noise in
	 * respect to the x axis and returns the length from the closest section to
	 * the left to that position.
	 * 
	 * @param x
	 *            The index of the distance vector needed relative to the start
	 *            of the noise as an integer on the x axis.
	 * @param y
	 *            The index of the distance vector needed relative to the start
	 *            of the noise as an integer on the y axis.
	 * @return The distance from the start of the local section to that point.
	 * @throws IndexOutOfBoundsException
	 *             If the entered index is greater than or equal to the length
	 *             of the noise or if it is less than the starting position.
	 */
	protected double getDistanceVectorX(int x) throws IndexOutOfBoundsException {
		// Check to see if the index is within bounds.
		inBounds(x);
		// Find where in the section the index belongs.
		x -= x / unitsPerSectionWidth * unitsPerSectionWidth;
		// Return the distance from the left hand part of the section to that
		// position.
		return (double) x / (double) (unitsPerSectionWidth - 1);
	}

	/**
	 * This function takes in an index relative to the start of the noise in
	 * respect to the y axis and returns the length from the closest section to
	 * the left to that position.
	 * 
	 * @param y
	 *            The index of the distance vector needed relative to the start
	 *            of the noise as an integer on the y axis.
	 * @return The distance from the start of the local section to that point.
	 * @throws IndexOutOfBoundsException
	 *             If the entered index is greater than or equal to the length
	 *             of the noise or if it is less than the starting position.
	 */
	protected double getDistanceVectorY(int y) throws IndexOutOfBoundsException {
		// Check to see if the index is within bounds.
		inBounds(y);
		// Find where in the section the index belongs.
		y -= y / unitsPerSectionHeight * unitsPerSectionHeight;
		// Return the distance from the left hand part of the section to that
		// position.
		return (double) y / (double) (unitsPerSectionHeight - 1);
	}
}
