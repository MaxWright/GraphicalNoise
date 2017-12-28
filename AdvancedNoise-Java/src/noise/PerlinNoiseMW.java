/*
filename    PerlinNoiseMW.java
author      Max Wright
uploaded    12/20/2017

Brief Description:
  
  
  Free for use. Use at your own risk, Max Wright will not be liable for any
  damages caused by this code.
 */
package noise;

import java.util.Random;

// Iteration 1
public class PerlinNoiseMW {
	// ////////////
	// Grid Values
	// ////////////
	/**
	 * The number of rows of larger rectangles.
	 */
	private int rows;
	/**
	 * The number of columns of larger rectangles.
	 */
	private int columns;
	/**
	 * The number of units inside a rectangle in the height.
	 */
	private int rowHeight;
	/**
	 * The number of units inside a rectangle in the width.
	 */
	private int columnWidth;

	// ////////////////////////////
	// Gradient Vector Grid Values
	// ////////////////////////////
	/**
	 * The number of corners in the height of the structure.
	 */
	private int yCorners;
	/**
	 * The number of corners in the width of the structure.
	 */
	private int xCorners;
	/**
	 * A 2D array of ints where each int represents one of vectors defined in
	 * the gradientVals 2Darray.
	 */
	private int gradientVectors[][];

	/**
	 * A 2D array of doubles representing gradient vectors whose length is equal
	 * to the sqrt(2) within a minor deviation. The first parameter of the array
	 * represents the vector, the second parameter represents a value in the
	 * vector.
	 */
	private double[][] gradientVals = {
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
			// equivalences
			{ 1.38704, 0.275899 }, { -1.38704, 0.275899 },
			{ 1.38704, -0.275899 }, { -1.38704, -0.275899 },
			{ 0.275899, 1.38704 }, { -0.275899, 1.38704 },
			{ 0.275899, -1.38704 }, { -0.275899, -1.38704 },
			{ 0.785695, 1.17588 }, { -0.785695, 1.17588 },
			{ 0.785695, -1.17588 }, { -0.785695, -1.17588 },
			{ 1.17588, 0.785695 }, { -1.17588, 0.785695 },
			{ 1.17588, -0.785695 }, { -1.17588, -0.785695 },

	};
	// ///////
	// Output
	// ///////
	/**
	 * A 2D array of doubles that holds the final output after all calculation
	 * has been done.
	 */
	private double result[][];

	/**
	 * This constructor takes in the parameters for desired attributes of the
	 * noise to be made.
	 * 
	 * @param y
	 *            Integer of the number of larger rectangles in the y axis.
	 * @param x
	 *            Integer of the number of larger rectangles in the x axis.
	 * @param height
	 *            Integer of the number of units in each square along the y
	 *            axis.
	 * @param width
	 *            Integer of the number of units in each square along the x
	 *            axis.
	 */
	public PerlinNoiseMW(int y, int x, int height, int width) {
		rows = y;
		columns = x;
		rowHeight = height;
		columnWidth = width;

		/*
		 * Add one to account that there needs to be vectors on the corners of
		 * the squares.
		 */
		yCorners = rows + 1;
		xCorners = columns + 1;

		result = new double[y * height][x * width];
		gradientVectors = new int[yCorners][xCorners];

		generateGradientVectors();
		calcNoise();
	}

	/**
	 * This function sets the different gradient vectors.
	 */
	private void generateGradientVectors() {
		Random rand = new Random();
		for (int i = 0; i < yCorners; ++i) {
			for (int j = 0; j < xCorners; ++j) {
				gradientVectors[i][j] = rand.nextInt(gradientVals.length);
			}
		}
	}

	/**
	 * This function assures that all sections of the noise are processed.
	 */
	private void calcNoise() {
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				calcSection(i, j);
			}
		}
	}

	/**
	 * This function processes a section of the noise.
	 * 
	 * @param y
	 *            An integer representing the y coordinate of the section.
	 * @param x
	 *            An integer representing the x coordinate of the section.
	 */
	private void calcSection(int y, int x) {
		for (int i = 0; i < rowHeight; ++i) {
			for (int j = 0; j < columnWidth; ++j) {
				calcCoordinate(y, x, i, j);
			}
		}
	}

	/**
	 * This function processes an individual part of a section.
	 * 
	 * @param row
	 *            An integer representing the y coordinate of the section.
	 * @param column
	 *            An integer representing the x coordinate of the section.
	 * @param y
	 *            An integer representing the y coordinate of what will stored
	 *            in the final array.
	 * @param x
	 *            An integer representing the x coordinate of what will stored
	 *            in the final array.
	 */
	private void calcCoordinate(int row, int column, int y, int x) {
		/*
		 * Find the values of the vector from the top left point of the section
		 * to the current point being calculated.
		 */
		double tl2PosY = ((double) y) / (double) (rowHeight - 1);
		double tl2PosX = ((double) x) / (double) (columnWidth - 1);

		/*
		 * Find the value of the gradientVector at the specified corner and dot
		 * it with the vector from that corner to the point being calculated.
		 */
		double tlCorner = dotProduct(gradientVectors[row][column], tl2PosY,
				tl2PosX);
		double trCorner = dotProduct(gradientVectors[row][column + 1], tl2PosY,
				-(1 - tl2PosX));
		double blCorner = dotProduct(gradientVectors[row + 1][column],
				-(1 - tl2PosY), tl2PosX);
		double brCorner = dotProduct(gradientVectors[row + 1][column + 1],
				-(1 - tl2PosY), -(1 - tl2PosX));

		/*
		 * Interpolate. Use the fade function to smooth out the noise by fading
		 * the vectors values of the distance vector.
		 */
		tl2PosY = fade(tl2PosY);
		tl2PosX = fade(tl2PosX);
		double top = interpolate(tl2PosX, tlCorner, trCorner);
		double down = interpolate(tl2PosX, blCorner, brCorner);
		result[row * rowHeight + y][column * columnWidth + x] = interpolate(
				tl2PosY, top, down);
	}

	/**
	 * This function performs interpolation on two given values and the length
	 * to the new value.
	 * 
	 * @param length
	 *            The length as a double from the left hand side to the point
	 *            where the value will be calculated.
	 * @param lhs
	 *            The value as a double on the left hand side.
	 * @param rhs
	 *            The value as a double on the right hand side.
	 * @return A double that is the interpolated value of the parameters given.
	 */
	private double interpolate(double length, double lhs, double rhs) {
		return lhs + length * (rhs - lhs);
	}

	/**
	 * This function performs a dot product on two given vectors.
	 * 
	 * @param gradientVectorType An integer representing which vector in the 2D array of gradient vectors we are using for this dot product.
	 * @param y A double representing the y value in the distance vector.
	 * @param x A double representing the x value in the distance vector.
	 * @return A double that is the dot product of the given vectors.
	 */
	private double dotProduct(int gradientVectorType, double y, double x) {
		return gradientVals[gradientVectorType][0] * y
				+ gradientVals[gradientVectorType][1] * x;
	}

	/**
	 * This function fades a given value. This fade function of 6t^5 - 15t^4 + 10t^3 changes a linear relationship into a curved relationship.
	 * @param val A double to be faded.
	 * @return A double of the given value after being faded.
	 */
	private double fade(double val) {
		return val * val * val * (val * ((val * 6) - 15) + 10);
	}

	/**
	 * 
	 * This function gets the value of the noise at the given point.
	 * 
	 * @param y The y index of the noise.
	 * @param x The x index of the noise.
	 * @return The value of the noise at the point.
	 */
	public double getNoise(int y, int x) {
		return result[y][x];
	}

	/**
	 * @return An integer of the height of the noise.
	 */
	public int getHeight() {
		return rows * rowHeight;
	}

	/**
	 * @return An integer of the width of the noise.
	 */
	public int getWidth() {
		return columns * columnWidth;
	}

}
