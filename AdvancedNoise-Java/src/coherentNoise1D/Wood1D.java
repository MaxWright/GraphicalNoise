package coherentNoise1D;

/**
 * This class defines the methodology of generating Wood Noise in one dimension.
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 */
public class Wood1D extends GradientVectorNoise1D {

	/**
	 * This constructor creates an instance of Wood Noise in one dimension.
	 * 
	 * @param sections
	 *            The number of independent sections in the noise as an integer.
	 * @param length
	 *            The length of the noise to be made.
	 * @extends GradientVectorNoise1D
	 */
	public Wood1D(int sections, int length) {
		super(sections, length);
	}

	@Override
	public double getNoise(int x) throws IndexOutOfBoundsException {
		// Check that the index is within bounds.
		inBounds(x);
		double distance = getDistanceVector(x);
		x = getSection(x);
		double lhs = specialDotProduct(x, distance);
		double rhs = specialDotProduct(x + 1, -(1 - distance));
		return (lhs + rhs) / 2;
	}

	/**
	 * This function performs a special dot product operation between the
	 * gradient vector at the given index and the dimensions given of the index.
	 * The function also is a special version of flooring a double and returning
	 * only its decimal value, 0, 1, or -1.
	 * 
	 * @param index
	 *            The corner that is being dotted with the entered distance.
	 * @param x
	 *            The value of the distance vector in the x direction as a
	 *            double.
	 * @return A double representing the result of the dot product of the
	 *         gradient vector at position index and then going through a
	 *         special flooing process.
	 */
	private double specialDotProduct(int index, double x) {
		/*
		 * Fade the individual vector values of the distance vector.
		 */
		x = fade(x);
		/*
		 * Take the dot product of the given gradient vector and distance
		 * vector.
		 */
		double dot = getGradientVal(index) * x;
		/*
		 * Get the integer value of the double.
		 */
		double dotFloor = Math.floor(dot);
		/*
		 * If dot product happened to result as an integer.
		 */
		if (dot - dotFloor == 0) {
			if (dot < 0) {
				return -1;
			}
			if (dot > 0) {
				return 1;
			}
			if (dot == 0) {
				return 0;
			}
		}
		/*
		 * Else, only return the decimal value from the dot product.
		 */
		return dot - dotFloor;
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
