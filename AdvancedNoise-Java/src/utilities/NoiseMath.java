package utilities;

/**
 * This class holds static methods involving common math used in noise functions.
 * 
 * @extends GradientVectorNoise1D
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 */
public final class NoiseMath {
	
	/**
	 * Utility class. Designed to never be initiated or inherited from.
	 */
	private NoiseMath() { }
	
	/**
	 * This function fades a given value. This fade function of 6t^5 - 15t^4 +
	 * 10t^3 changes a linear relationship into a curved relationship.
	 * 
	 * @param val
	 *            A double to be faded.
	 * @return A double of the given value after being faded.
	 */
	public static double fade(double val) {
		return val * val * val * (val * ((val * 6) - 15) + 10);
	}
	
	/**
	 * This function performs the mathematical function of interpolation where
	 * lhs is the left hand side, rhs is the right hand side, and mu is the
	 * distance from the left hand side to the point you are trying to find the
	 * value of.
	 * 
	 * @param mu The distance from the left hand side as a double.
	 * @param lhs The value of the left hand side as a double.
	 * @param rhs The value of the right hand side as a double.
	 * @return A double value of the interpolation.
	 */
	public static double interpolate(double mu, double lhs, double rhs) {
		return lhs + mu * (rhs - lhs);
	}

}
