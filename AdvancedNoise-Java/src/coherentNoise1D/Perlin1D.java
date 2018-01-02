package coherentNoise1D;

import utilities.NoiseMath;

/**
 * This class defines the methodology of generating Perlin Noise in one
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
public class Perlin1D extends GradientVectorNoise1D {

	/**
	 * This constructor creates an instance of Perlin Noise in one dimension.
	 * 
	 * @param sections
	 *            The number of independent sections in the noise as an integer.
	 * @param length
	 *            The length of the noise to be made.
	 * @extends GradientVectorNoise1D
	 */
	public Perlin1D(int sections, int length) {
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
		distance = NoiseMath.fade(distance);
		return NoiseMath.interpolate(distance, lhs, rhs);
	}
}
