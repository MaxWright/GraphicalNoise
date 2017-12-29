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

}
