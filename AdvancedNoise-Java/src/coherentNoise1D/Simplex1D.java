package coherentNoise1D;

/**
 * This class defines the methodology of generating Simplex Noise in one
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
public class Simplex1D extends GradientVectorNoise1D {

	/**
	 * This constructor creates an instance of Simplex Noise in one dimension.
	 * 
	 * @param sections
	 *            The number of independent sections in the noise as an integer.
	 * @param length
	 *            The length of the noise to be made.
	 * @extends GradientVectorNoise1D
	 */
	public Simplex1D(int sections, int length) {
		super(sections, length);
	}

	@Override
	public double getNoise(int x) throws IndexOutOfBoundsException {
		// Check that the index is within bounds.
		inBounds(x);
		double distance = getDistanceVector(x);
		x = getSection(x);
		double lhs = dotProduct(x, distance) * weight(distance);
		double rhs = dotProduct(x + 1, -(1 - distance))
				* weight(-(1 - distance));
		/*
		 * In testing, the constant 47 is the highest whole number available as a
		 * scalar that does not throw an error.
		 */
		return (lhs + rhs) * 47;
	}

	/**
	 * The function calculates weight from a distance vector.
	 * 
	 * @param toWeight
	 *            The distance vector value as a double.
	 * @return Zero or a weighted value as double.
	 */
	private double weight(double toWeight) {
		double toReturn = (0.6 - toWeight * toWeight - toWeight * toWeight);
		if (toReturn < 0) {
			toReturn = 0;
		}
		toReturn *= toReturn;
		toReturn *= toReturn;
		return toReturn;
	}
}
