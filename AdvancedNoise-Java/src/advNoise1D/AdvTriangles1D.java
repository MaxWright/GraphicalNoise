package advNoise1D;

import coherentNoise1D.Triangles1D;

/**
 * This class initialize an instance of Triangles Noise with the concepts of
 * frequency, octaves, and persistence.
 * 
 * @extends AdvGradientVectorNoise1D
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class AdvTriangles1D extends AdvGradientVectorNoise1D {
	/**
	 * This constructor creates an instance of Advanced Triangles Noise with the
	 * entered parameters.
	 * 
	 * @param frequency
	 *            The number of independent sections in the noise as an integer.
	 * @param length
	 *            The length of the noise to be made as an integer.
	 * @param octaves
	 *            The number of octaves in the noise as an integer.
	 * @param persistence
	 *            How quickly the amplitude of each octave descends as a double.
	 * @throws IllegalArgumentException
	 *             As defined by the parent class
	 *             {@link AdvGradientVectorNoise1D}
	 * @extends AdvGradientVectorNoise1D
	 */
	public AdvTriangles1D(int frequency, int length, int octaves,
			double persistence) throws IllegalArgumentException {
		super(frequency, length, octaves, persistence);
		populateArray();
	}

	@Override
	protected void populateArray() {
		int tempFrequency = super.getFrequency();
		for (int i = 0; i < super.getOctaves(); ++i) {
			Triangles1D temp = new Triangles1D(tempFrequency, super.getSize());
			populateNextIndex(temp);
			tempFrequency *= 2;
		}
	}

	@Override
	public double getNoise(int x) throws IndexOutOfBoundsException,
			ArithmeticException {
		inBounds(x);
		return getSum(x);
	}

}
