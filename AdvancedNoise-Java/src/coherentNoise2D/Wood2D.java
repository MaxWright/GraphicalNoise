package coherentNoise2D;

import utilities.NoiseMath;

/**
 * This class defines the getNoise method for Wood Noise in 2D.
 * 
 * @extends GradientVectorNoise2D
 * 
 * @abstract
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class Wood2D extends GradientVectorNoise2D {

	/**
	 * The constructor for Wood Noise 2D.
	 * 
	 * @param sectionsOnWidth
	 *            See
	 *            {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
	 * @param sectionsOnHeight
	 *            See
	 *            {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
	 * @param width
	 *            See
	 *            {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
	 * @param height
	 *            See
	 *            {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
	 * @throws IllegalArgumentException
	 *             See
	 *             {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
     * @extends GradientVectorNoise2D
	 */
	public Wood2D(int sectionsOnWidth, int sectionsOnHeight, int width,
			int height) throws IllegalArgumentException {
		super(sectionsOnWidth, sectionsOnHeight, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getNoise(int x, int y) throws IndexOutOfBoundsException {
		// Check that the index is within bounds.
		inBounds(x, y);
		/*
		 * Get all the distance vector values possible for this index.
		 */
		double distanceX = getDistanceVectorX(x);
		double distanceY = getDistanceVectorY(y);
		double distanceOpX = getOpDistanceVectorX(x);
		double distanceOpY = getOpDistanceVectorY(y);
		/*
		 * Save the sections the x and y variables are located.
		 */
		int sectionX = getSectionX(x);
		int sectionY = getSectionY(y);
		/*
		 * Find the dot product of the distance of the index relative to the
		 * position of the gradient vector and the gradient vector.
		 */
		double topRightDot = specialDotProduct(sectionX + 1, sectionY, distanceOpX, distanceY);
		double bottomLeftDot =specialDotProduct(sectionX, sectionY + 1, distanceX, distanceOpY);
		double thirdCorner = 0;
		int denominator = 2;
		
		/*
		 * Find the local position of x and y.
		 */
		x -= sectionX * getUnitsPerSectionWidth();
		y -= sectionY * getUnitsPerSectionHeight();
		/*
		 * If the point being searched lies in the diagonal, vertexY would be
		 * equal to the x value being calculated.
		 */
		int vertexY = getUnitsPerSectionHeight() - y - 1;
		if (x > vertexY) {
			thirdCorner = specialDotProduct(sectionX + 1, sectionY + 1, distanceOpX,
					distanceOpY);
			++denominator;
		} else if (x < vertexY) {
			// Upper Triangle
			thirdCorner = specialDotProduct(sectionX, sectionY, distanceX, distanceY);
			++denominator;
		} 
		/*
		 *  Sense the thirdCorner is initialized at zero, the case of x == vertexY does not have to specified.
		 */
		return ( NoiseMath.specialFloor(topRightDot) + NoiseMath.specialFloor(bottomLeftDot) + NoiseMath.specialFloor(thirdCorner) ) / denominator;
	}
	
	/**
	 * This function performs a special dot product operation between the
	 * gradient vector at the given index and the dimensions given of the index.
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
	 *         gradient vector at position index and the distance vector with
	 *         some weight applied.
	 */
	private double specialDotProduct(int indexX, int indexY, double x, double y) {
		x = NoiseMath.fade(x);
		y = NoiseMath.fade(y);
		return (getGradientValX(indexX, indexY) * x + getGradientValY(indexX, indexY) * y);
	}

}
