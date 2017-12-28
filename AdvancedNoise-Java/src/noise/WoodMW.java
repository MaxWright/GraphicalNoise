/*
filename    WoodMW.java
author      Max Wright
uploaded    12/20/2017

Brief Description:
  
  
  © 2017 Max Wright. All rights reserved. 
  Free for use. Use at your own risk, Max Wright will not be liable for any
  damages caused by this code.
*/
package noise;

import java.util.Random;

public class WoodMW {
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
	private int gradients[][];

	/**
	 * A 2D array of doubles representing gradient vectors whose length is equal
	 * to the sqrt(2) within a minor deviation. The first parameter of the array
	 * represents the vector, the second parameter represents a value in the
	 * vector.
	 */
	private double[][] gradientVals = {
			{ 1.41421, 0 },
			{ 0, 1.41421 },
			{ -1.41421, 0 },
			{ 0, -1.41421 }, // + or - 0, 90, 180 degrees
			{ 1, 1 },
			{ 1, -1 },
			{ -1, 1 },
			{ -1, -1 }, // + or - 45, 135 degrees

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
	 * @param y The rows and columns in the noise.
	 * @param height The height and width of the noise.
	 */
	public WoodMW(int y, int height) {
		rows = y;
		columns = y;
		rowHeight = height;
		columnWidth = height;
		
		yCorners = rows + 1;
		xCorners = columns + 1;
		
		result = new double[y * height][y * height];
		gradients = new int[yCorners][xCorners];
		
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
				gradients[i][j] = rand.nextInt(gradientVals.length);
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
	 * @param y An integer representing the y coordinate of the section.
	 * @param x An integer representing the x coordinate of the section.
	 */
	void calcSection(int y, int x) {
		int tempWidth = columnWidth - 1;
		int tempWidth2 = 1;
		int k = rowHeight - 1;
		int l = columnWidth - 1;
		//int tempWidth2 = columnWidth - 1;
		for(int i = 0; i < rowHeight; ++i, --k) {
			for(int j = 0; j < tempWidth; ++j) {
				calcCoordinateUp(y, x, i, j);
			}
			for(int j = tempWidth2; j < columnWidth; ++j) {
				calcCoordinateDown(y, x, k, j);
			}
			--tempWidth;
			++tempWidth2;
			calcCoordinateDivide(y, x, i, l);
			--l;
		}
	}
	
	/**
	 * This function processes the upper right triangle values.
	 * 
	 * @param row An integer representing the y coordinate of the section. 
	 * @param column An integer representing the x coordinate of the section.
	 * @param y An integer representing the y coordinate of what will stored in the final array.
	 * @param x An integer representing the x coordinate of what will stored in the final array.
	 */
	void calcCoordinateUp(int row, int column, int y, int x) {
		/*
		 * Find the values of the vector from the top left point of the
		 * section to the current point being calculated.
		 */
		double tl2PosY = ((double) y) / (double)(rowHeight);
		double tl2PosX = ((double) x) / (double)(columnWidth);
		/*
		 * Find the value of the gradientVector at the specified corner
		 * and dot it with the vector from that corner to the point being
		 * calculated.
		 */
		double tlCorner = dotProductAndWeight(gradients[row][column], tl2PosY, tl2PosX);
		double trCorner = dotProductAndWeight(gradients[row][column + 1], tl2PosY, -(1 - tl2PosX));
		double blCorner = dotProductAndWeight(gradients[row + 1][column], -(1 - tl2PosY), tl2PosX);
		result[row * rowHeight + y][column * columnWidth + x] = (tlCorner + trCorner + blCorner) / 3;
	}

	/**
	 * This function processes the lower left triangle values.
	 * 
	 * @param row An integer representing the y coordinate of the section. 
	 * @param column An integer representing the x coordinate of the section.
	 * @param y An integer representing the y coordinate of what will stored in the final array.
	 * @param x An integer representing the x coordinate of what will stored in the final array.
	 */
	void calcCoordinateDown(int row, int column, int y, int x) {
		/*
		 * Find the values of the vector from the top left point of the
		 * section to the current point being calculated.
		 */
		double tl2PosY = ((double) y) / (double)(rowHeight);
		double tl2PosX = ((double) x) / (double)(columnWidth);
		/*
		 * Find the value of the gradientVector at the specified corner
		 * and dot it with the vector from that corner to the point being
		 * calculated.
		 */
		double trCorner = dotProductAndWeight(gradients[row][column + 1], tl2PosY, -(1 - tl2PosX));
		double blCorner = dotProductAndWeight(gradients[row + 1][column], -(1 - tl2PosY), tl2PosX);
		double brCorner = dotProductAndWeight(gradients[row + 1][column + 1], -(1 - tl2PosY), -(1 - tl2PosX));

		result[row * rowHeight + y][column * columnWidth + x] = (trCorner + brCorner + blCorner) / 3;
	}
	
	/**
	 * This function processes the middle values.
	 * 
	 * @param row An integer representing the y coordinate of the section. 
	 * @param column An integer representing the x coordinate of the section.
	 * @param y An integer representing the y coordinate of what will stored in the final array.
	 * @param x An integer representing the x coordinate of what will stored in the final array.
	 */
	void calcCoordinateDivide(int row, int column, int y, int x) {
		/*
		 * Find the values of the vector from the top left point of the
		 * section to the current point being calculated.
		 */
		double tl2PosY = ((double) y) / (double)(rowHeight);
		double tl2PosX = ((double) x) / (double)(columnWidth);
		/*
		 * Find the value of the gradientVector at the specified corner
		 * and dot it with the vector from that corner to the point being
		 * calculated.
		 */
		double trCorner = dotProductAndWeight(gradients[row][column + 1], tl2PosY, -(1 - tl2PosX));
		double blCorner = dotProductAndWeight(gradients[row + 1][column], -(1 - tl2PosY), tl2PosX);
		result[row * rowHeight + y][column * columnWidth + x] = (blCorner + trCorner) / 2;
	}
	
	/**
	 * This function performs a dot product on two given vectors and applies a weight to it.
	 * 
	 * @param gradientVectorType An integer representing which vector in the 2D array of gradient vectors we are using for this dot product.
	 * @param y A double representing the y value in the distance vector.
	 * @param x A double representing the x value in the distance vector.
	 * @return A double that is the dot product of the given vectors with a weight applied to it.
	 */
	double dotProductAndWeight(int gradientVectorType, double y, double x) {
		/*
		 * Fade the individual vector values of the distance vector.
		 */
		y = fade(y);
		x = fade(x);
		/*
		 * Take the dot product of the given gradient vector and distance vector.
		 */
		double dot = gradientVals[gradientVectorType][0] * y + gradientVals[gradientVectorType][1] * x;
		/*
		 * Get the integer value of the double.
		 */
		double dotFloor = Math.floor(dot);
		/*
		 * If dot product happened to result as an integer.
		 */
		if(dot - dotFloor == 0) {
			if(dot < 0) {
				return -1;
			}
			if(dot > 0) {
				return 1;
			}
			if(dot == 0) {
				return 0;
			}
		}
		/*
		 * Else, only return the decimal value from the dot product.
		 */
		return dot - dotFloor;
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
	 * This function fades a given value. This fade function of 6t^5 - 15t^4 + 10t^3 changes a linear relationship into a curved relationship.
	 * 
	 * @param val A double to be faded.
	 * @return A double of the given value after being faded.
	 */
	double fade(double val) {
		return val * val * val * (val * ((val * 6) - 15) + 10);
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
