package coherentNoise1D;

/**
 * This class defines the methodology of generating Perlin Noise in one
 * dimension.
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class PerlinNoise1D extends GradientVectorNoise1D {

	/**
	 * This constructor creates an instance of Perlin Noise in one dimension.
	 * 
	 * @param sections
	 *            The number of independent sections in the noise as an integer.
	 * @param length
	 *            The length of the noise to be made.
	 * @extends GradientVectorNoise1D
	 */
	public PerlinNoise1D(int sections, int length) {
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
		distance = fade(distance);
		return interpolate(distance, lhs, rhs);
	}

	/**
	 * This function performs the mathematical function of interpolation where
	 * lhs is the left hand side, rhs is the right hand side, and mu is the
	 * distance from the left hand side to the point you are trying to find the
	 * value of.
	 * 
	 * @param mu
	 * @param lhs
	 * @param rhs
	 * @return
	 */
	private double interpolate(double mu, double lhs, double rhs) {
		return lhs + mu * (rhs - lhs);
	}

	/**
	 * This function fades a given value. This fade function of 6t^5 - 15t^4 +
	 * 10t^3 changes a linear relationship into a curved relationship.
	 * 
	 * @param val
	 *            A double to be faded.
	 * @return A double of the given value after being faded.
	 */
	private double fade(double val) {
		return val * val * val * (val * ((val * 6) - 15) + 10);
	}
}
