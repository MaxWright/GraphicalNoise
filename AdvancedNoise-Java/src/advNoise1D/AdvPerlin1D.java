package advNoise1D;

import coherentNoise1D.PerlinNoise1D;

public class AdvPerlin1D<T> extends AdvGradientVectorNoise1D {
	/**
	 * This constructor creates an instance of PerlinNoise with the entered
	 * parameters.
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
	 *             <p>
	 *             If the length is less than or equal to zero.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             If the number of sections is less than or equal to zero of is
	 *             greater than the length.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             If the octaves are less than or equal to zero or if the
	 *             octaves are so great that the frequency divides so much in
	 *             truncates into zero.
	 *             <p>
	 *             -OR-
	 *             <p>
	 *             If the persistence equals zero.
	 * @extends AdvGradientVectorNoise1D
	 */
	public AdvPerlin1D(int frequency, int length, int octaves,
			double persistence) throws IllegalArgumentException {
		super(frequency, length, octaves, persistence);
		populateArray();
	}

	@Override
	protected void populateArray() {
		int tempFrequency = super.getFrequency();
		for (int i = 0; i < super.getOctaves(); ++i) {
			PerlinNoise1D temp = new PerlinNoise1D(tempFrequency,
					super.getSize());
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
