package coherentNoise1D;

import utilities.NoiseMath;

public class Scales1D extends GradientVectorNoise1D {

	/**
	 * This class defines the methodology of generating Scales Noise in one
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
	public Scales1D(int sections, int length) {
		super(sections, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getNoise(int x) throws IndexOutOfBoundsException {
		// Check that the index is within bounds.
		inBounds(x);
		double distance = getDistanceVector(x);
		x = getSection(x);
		double lhs = specialDotProduct(x, distance);
		double rhs = specialDotProduct(x + 1, -(1 - distance));
		return lhs + rhs;
	}

	/**
	 * This function performs a special dot product operation between the
	 * gradient vector at the given index and the dimensions given of the index.
	 * 
	 * @param index
	 *            The corner that is being dotted with the entered distance.
	 * @param x
	 *            The value of the distance vector in the x direction as a
	 *            double.
	 * @return A double representing the result of the dot product of the
	 *         gradient vector at position index and the distance vector with
	 *         some weight applied.
	 */
	private double specialDotProduct(int index, double x) {
		double toReturn = (0.6 - x * x - x * x);
		if (toReturn < 0) {
			toReturn = 0;
		}
		toReturn = toReturn * toReturn;
		x = NoiseMath.fade(x);
		return toReturn * getGradientVal(index) * x;
	}
}
