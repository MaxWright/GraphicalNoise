package advNoise2D;

import coherentNoise2D.Simplex2D;

/**
 * This class initialize an instance of Simplex Noise in 2D with the concepts of
 * frequency, octaves, and persistence.
 * 
 * @extends AdvGradientVectorNoise2D
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class AdvSimplex2D extends AdvGradientVectorNoise2D {

	/**
	 * This constructor creates an instance of Advanced Simplex Noise with the
	 * entered parameters.
	 * 
	 * @param frequencyWidth
	 *            See
	 *            {@link AdvGradientVectorNoise2D#AdvGradientVectorNoise2D(int, int, int, int, int, double)
	 *            Parent}
	 * @param frequencyHeight
	 *            See
	 *            {@link AdvGradientVectorNoise2D#AdvGradientVectorNoise2D(int, int, int, int, int, double)
	 *            Parent}
	 * @param width
	 *            See
	 *            {@link AdvGradientVectorNoise2D#AdvGradientVectorNoise2D(int, int, int, int, int, double)
	 *            Parent}
	 * @param height
	 *            See
	 *            {@link AdvGradientVectorNoise2D#AdvGradientVectorNoise2D(int, int, int, int, int, double)
	 *            Parent}
	 * @param octaves
	 *            See
	 *            {@link AdvGradientVectorNoise2D#AdvGradientVectorNoise2D(int, int, int, int, int, double)
	 *            Parent}
	 * @param persistence
	 *            See
	 *            {@link AdvGradientVectorNoise2D#AdvGradientVectorNoise2D(int, int, int, int, int, double)
	 *            Parent}
	 * @throws IllegalArgumentException
	 *             See
	 *             {@link AdvGradientVectorNoise2D#AdvGradientVectorNoise2D(int, int, int, int, int, double)
	 *             Parent}
	 * @extends AdvGradientVectorNoise2D
	 */
	public AdvSimplex2D(int frequencyWidth, int frequencyHeight, int width, int height,
			int octaves, double persistence) throws IllegalArgumentException {
		super(frequencyWidth, frequencyHeight, width, height, octaves,
				persistence);
		populateArray();
	}

	@Override
	protected void populateArray() throws IllegalArgumentException {
		int tempFrequencyWidth = super.getFrequencyWidth();
		int tempFrequencyHeight = super.getFrequencyHeight();
		for (int i = 0; i < super.getOctaves(); ++i) {
			Simplex2D temp = new Simplex2D(tempFrequencyWidth,
					tempFrequencyHeight, super.getWidth(), super.getHeight());
			populateNextIndex(temp);
			tempFrequencyWidth *= 2;
			tempFrequencyHeight *= 2;
		}
	}

	@Override
	public double getNoise(int x, int y) throws IndexOutOfBoundsException {
		inBounds(x, y);
		return getSum(x, y);
	}

}