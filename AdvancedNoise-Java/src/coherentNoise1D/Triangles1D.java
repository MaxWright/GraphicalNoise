package coherentNoise1D;

import utilities.NoiseMath;

/**
 * This class defines the methodology of generating Triangle Noise in one
 * dimension.
 * 
 * @extends GradientVectorNoise1D
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class Triangles1D extends GradientVectorNoise1D {

	/**
	 * This constructor creates an instance of Triangle Noise in one dimension.
	 * 
	 * @param sections
	 *            The number of independent sections in the noise as an integer.
	 * @param length
	 *            The length of the noise to be made.
	 * @extends GradientVectorNoise1D
	 */
	public Triangles1D(int sections, int length) {
		super(sections, length);
	}

	@Override
	public double getNoise(int x) throws IndexOutOfBoundsException {
		// Check that the index is within bounds.
		inBounds(x);
		double distance = getDistanceVector(x);
		x = getSection(x);
		double lhs = dotProduct(x, distance);
		double rhs = dotProduct(x + 1, -(1 - distance));
		lhs = NoiseMath.specialFloor(lhs);
		rhs = NoiseMath.specialFloor(rhs);
		return (lhs + rhs) / 2;
	}
}
