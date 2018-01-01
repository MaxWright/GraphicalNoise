package coherentNoise2D;

import utilities.NoiseMath;

public class Simplex2D extends GradientVectorNoise2D {

	public Simplex2D(int sectionsOnWidth, int sectionsOnHeight, int width,
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
		double topRightDot = dotProduct(sectionX + 1, sectionY, distanceOpX, distanceY) * NoiseMath.weight(distanceOpX, distanceY);
		double bottomLeftDot = dotProduct(sectionX, sectionY + 1, distanceX, distanceOpY) * NoiseMath.weight(distanceX, distanceOpY);
		double thirdCorner = 0;
		
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
			thirdCorner = dotProduct(sectionX + 1, sectionY + 1, distanceOpX,
					distanceOpY) * NoiseMath.weight(distanceOpX, distanceOpY);
		} else if (x < vertexY) {
			// Upper Triangle
			thirdCorner = dotProduct(sectionX, sectionY, distanceX, distanceY) * NoiseMath.weight(distanceX, distanceY);
		} 
		/*
		 *  Sense the thirdCorner is initialized at zero, the case of x == vertexY does not have to specified.
		 */

		return (topRightDot + bottomLeftDot + thirdCorner) / 3 * 100;
	}

}
