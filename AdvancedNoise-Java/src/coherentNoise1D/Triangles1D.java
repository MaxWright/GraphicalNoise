package coherentNoise1D;

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
		lhs = specialFloor(lhs);
		rhs = specialFloor(rhs);
		return (lhs + rhs) / 2;
	}

	/**
	 * The function is a special version of flooring a double and returning only
	 * its decimal value, 0, 1, or -1.
	 * 
	 * @param toWeight
	 *            The dot product as a double.
	 * @return Some value in the range of [-1, 1] as a double.
	 */
	private double specialFloor(double toSpecialFloor) {
		double dotFloor = Math.floor(toSpecialFloor);
		if (toSpecialFloor - dotFloor == 0) {
			if (toSpecialFloor < 0) {
				return -1;
			}
			if (toSpecialFloor > 0) {
				return 1;
			}
			if (toSpecialFloor == 0) {
				return 0;
			}
		}
		return toSpecialFloor - dotFloor;
	}
}
