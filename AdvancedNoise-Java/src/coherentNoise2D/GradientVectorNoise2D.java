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
public abstract class GradientVectorNoise2D extends Noise2D {
	/**
	 * This variable represents the width of the noise as an integer.
	 */
	private int width;
	/**
	 * This variable represents the height of the noise as an integer.
	 */
	private int height;
	/**
	 * The number of individual units per section on the width as an integer.
	 */
	private int unitsPerSectionWidth;
	/**
	 * The number of individual units per section on the Height as an integer.
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
			for (int j = 0; j < sectionsOnWidth; ++j) {
				vectorGradients[i][j] = rand
						.nextInt(gradientVectorsValues.length);
			}
		}
	}

	/**
	 * This function takes in an index relative to the start of the noise in
	 * respect to the x-axis and returns the vector value from the gradient
	 * position at the top left corner of the section this index belongs to to
	 * the x value given.
	 * 
	 * @param x
	 *            - The index of the distance vector needed relative to the
	 *            start of the noise as an integer on the x-axis.
	 * @return The vector value from the top left corner to the entered x
	 *         position.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on {@link #inBounds(int,int)} where the y value is
	 *             zero.
	 * @see {@link #getDistanceVectorY(int)}
	 */
	protected double getDistanceVectorX(int x) throws IndexOutOfBoundsException {
		// Check to see if the index is within bounds.
		inBounds(x, 0);
		// Find where in the section the index belongs.
		x -= x / unitsPerSectionWidth * unitsPerSectionWidth;
		// Return the distance from the left hand part of the section to that
		// position.
		return (double) x / (double) (unitsPerSectionWidth - 1);
	}

	/**
	 * This function takes in an index relative to the start of the noise in
	 * respect to the y-axis and returns the vector value from the gradient
	 * position at the top left corner of the section this index belongs to to
	 * the entered y value.
	 * 
	 * @param y
	 *            - The index of the distance vector needed relative to the
	 *            start of the noise as an integer on the y-axis.
	 * @return The distance from the top left corner to the entered y position.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on {@link #inBounds(int,int)} where the x value is
	 *             zero.
	 * @see {@link #getDistanceVectorX(int)}
	 */
	protected double getDistanceVectorY(int y) throws IndexOutOfBoundsException {
		// Check to see if the index is within bounds.
		inBounds(0, y);
		// Find where in the section the index belongs.
		y -= y / unitsPerSectionHeight * unitsPerSectionHeight;
		// Return the distance from the left hand part of the section to that
		// position.
		return (double) y / (double) (unitsPerSectionHeight - 1);
	}

	/**
	 * This function takes in an index relative to the start of the noise in
	 * respect to the x-axis and returns the vector value from the gradient
	 * position at the bottom right corner of the section this index belongs to
	 * to the entered x value.
	 * 
	 * @param x
	 *            - The index of the distance vector needed relative to the
	 *            start of the noise as an integer on the x-axis.
	 * @return The vector value from the bottom right gradient position of the
	 *         section to the entered x position.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on {@link #getDistanceVectorX(int)}
	 * @see {@link #getOpDistanceVectorY(int)}
	 */
	protected double getOpDistanceVectorX(int x)
			throws IndexOutOfBoundsException {
		return -(1 - getDistanceVectorX(x));
	}

	/**
	 * This function takes in an index relative to the start of the noise in
	 * respect to the y-axis and returns the vector value from the gradient at
	 * the bottom right corner of the section this index belongs to to the
	 * entered x value.
	 * 
	 * @param y
	 *            - The index of the distance vector needed relative to the
	 *            start of the noise as an integer on the y-axis.
	 * @return The vector value from the bottom right gradient position of the
	 *         section to the entered y position.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on {@link #getDistanceVectorY(int)}
	 * @see {@link #getOpDistanceVectorX(int)}
	 */
	protected double getOpDistanceVectorY(int y)
			throws IndexOutOfBoundsException {
		return -(1 - getDistanceVectorY(y));
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
	 * This function finds the section the x index is located in on the x axis.
	 * 
	 * @param x
	 *            - The index of the distance vector needed relative to the
	 *            start of the noise as an integer on the x-axis.
	 * @return The x-axis section that this index is in as an integer.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on {@link #inBounds(int,int)} where the y value is
	 *             zero.
	 * @see {@link #getSectionY(int)}
	 */
	protected int getSectionX(int x) {
		inBounds(x, 0);
		return x / unitsPerSectionWidth;
	}

	/**
	 * This function finds the section the y index is located in on the y axis.
	 * 
	 * @param y
	 *            - The index of the distance vector needed relative to the
	 *            start of the noise as an integer on the y-axis.
	 * @return The y-axis section that this index is in as an integer.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on {@link #inBounds(int,int)} where the x value is
	 *             zero.
	 * @see {@link #getSectionX(int)}
	 */
	protected int getSectionY(int y) {
		inBounds(0, y);
		return y / unitsPerSectionHeight;
	}

	/**
	 * This function performs a dot product operation between the gradient
	 * vector at the given index and the dimensions given of the index. Since
	 * the y distance will only ever be zero only the x distance is needed.
	 * 
	 * @param indexX
	 *            - The corner that is being dotted with the entered distance on
	 *            the x-axis as an integer.
	 * @param indexY
	 *            - The corner that is being dotted with the entered distance on
	 *            the y-axis as an integer.
	 * @param x
	 *            - The value of the distance vector in the x direction as a
	 *            double.
	 * @param y
	 *            - The value of the distance vector in the y direction as a
	 *            double.
	 * @return A double representing the result of the dot product of the
	 *         gradient vector at position index and the distance vector.
	 * @throws IndexOutOfBoundsException
	 *             If the indexes of the vector gradient entered are not an
	 *             appropriate index.
	 * @throws IllegalArgumentException
	 *             If the values entered for the x or y distance vector values
	 *             is outside the bounds of [-1, 1].
	 */
	protected double dotProduct(int indexX, int indexY, double x, double y)
			throws IndexOutOfBoundsException, IllegalArgumentException {
		// Check that the indexes are within bounds of the number of sections.
		if (indexY < 0 || indexY >= vectorGradients.length) {
			throw new IndexOutOfBoundsException("Index: at " + indexY
					+ " is not within bounds of [0," + vectorGradients.length
					+ ")");
		}
		if (indexX < 0 || indexX >= vectorGradients[0].length) {
			throw new IndexOutOfBoundsException("Index: at " + indexX
					+ " is not within bounds of [0," + vectorGradients.length
					+ ")");
		}
		// If the x value is not within the bounds of [-1, 1].
		if (x < -1 || x > 1) {
			throw new IllegalArgumentException(
					"x value entered whose value is " + x
							+ " is not within bounds of [-1, 1].");
		}
		// If the y value is not within the bounds of [-1, 1].
		if (y < -1 || y > 1) {
			throw new IllegalArgumentException(
					"y value entered whose value is " + y
							+ " is not within bounds of [-1, 1].");
		}
		/*
		 * Return the dot product calculation of the gradient vector and the
		 * distance vector.
		 */
		return gradientVectorsValues[vectorGradients[indexY][indexX]][0] * x
				+ gradientVectorsValues[vectorGradients[indexY][indexX]][1] * y;
	}

	/**
	 * This function exists in case the normal dot product defined in the class
	 * is not a good fit for the noise being made.
	 * 
	 * @param indexX
	 *            - The corner that is being dotted with the entered distance on
	 *            the x-axis as an integer.
	 * @param indexY
	 *            - The corner that is being dotted with the entered distance on
	 *            the y-axis as an integer.
	 * @return The value of the x of the gradient vector at the corner given.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on {@link #getGradientVals(int, int)}
	 * @see {@link #getGradientValY(int,int)}
	 * @see {@link #getGradientVals(int,int)}
	 */
	protected double getGradientValX(int indexX, int indexY)
			throws IndexOutOfBoundsException {
		return getGradientVals(indexX, indexY)[0];
	}

	/**
	 * This function exists in case the normal dot product defined in the class
	 * is not a good fit for the noise being made.
	 * 
	 * @param indexX
	 *            - The corner that is being dotted with the entered distance on
	 *            the x-axis as an integer.
	 * @param indexY
	 *            - The corner that is being dotted with the entered distance on
	 *            the y-axis as an integer.
	 * @return The value of the y of the gradient vector at the corner given.
	 * @throws IndexOutOfBoundsException
	 *             Dependent on {@link #getGradientVals(int, int)}
	 * @see {@link #getGradientValY(int,int)}
	 * @see {@link #getGradientVals(int,int)}
	 */
	protected double getGradientValY(int indexX, int indexY)
			throws IndexOutOfBoundsException {
		return getGradientVals(indexX, indexY)[1];
	}

	/**
	 * This function exists in case the normal dot product defined in the class
	 * is not a good fit for the noise being made.
	 * 
	 * @param indexX
	 *            - The corner that is being dotted with the entered distance on
	 *            the x-axis as an integer.
	 * @param indexY
	 *            - The corner that is being dotted with the entered distance on
	 *            the y-axis as an integer.
	 * @return An array of doubles of length two where the first index is the x
	 *         value and the second index is the y value.
	 * @throws IndexOutOfBoundsException
	 *             If the indexes of the vector gradient entered are not an
	 *             appropriate index.
	 */
	protected double[] getGradientVals(int indexX, int indexY) throws IndexOutOfBoundsException {
		// Check that the indexes are within bounds of the number of sections.
		if (indexY < 0 || indexY >= vectorGradients.length) {
			throw new IndexOutOfBoundsException("Index: at " + indexY
					+ " is not within bounds of [0," + vectorGradients.length
					+ ")");
		}
		if (indexX < 0 || indexX >= vectorGradients[0].length) {
			throw new IndexOutOfBoundsException("Index: at " + indexX
					+ " is not within bounds of [0," + vectorGradients.length
					+ ")");
		}
		return gradientVectorsValues[vectorGradients[indexY][indexX]];
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
	 * 
	 * @return The units per section on the x-axis.
	 * @see {@link #getUnitsPerSectionHeight()}
	 */
	protected int getUnitsPerSectionWidth() {
		return unitsPerSectionWidth;
	}

	/**
	 * 
	 * @return The units per section on the x-axis.
 * @see {@link #getUnitsPerSectionWidth()}
	 */
	protected int getUnitsPerSectionHeight() {
		return unitsPerSectionHeight;
	}

}
