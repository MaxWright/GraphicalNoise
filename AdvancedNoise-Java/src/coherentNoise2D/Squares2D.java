package coherentNoise2D;

import utilities.NoiseMath;

/**
 * This class defines the getNoise method for Squares Noise in 2D.
 * 
 * @extends GradientVectorNoise2D
 * 
 * @abstract
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class Squares2D extends GradientVectorNoise2D {

	/**
	 * The constructor for Squares Noise 2D.
	 * 
	 * @param sectionsOnWidth
	 *            See
	 *            {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
	 * @param sectionsOnHeight
	 *            See
	 *            {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
	 * @param width
	 *            See
	 *            {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
	 * @param height
	 *            See
	 *            {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
	 * @throws IllegalArgumentException
	 *             See
	 *             {@link GradientVectorNoise2D#GradientVectorNoise2D(int, int, int, int) Parent}
     * @extends GradientVectorNoise2D
	 */
	public Squares2D(int sectionsOnWidth, int sectionsOnHeight, int width,
			int height) throws IllegalArgumentException {
		super(sectionsOnWidth, sectionsOnHeight, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getNoise(int x, int y) throws IndexOutOfBoundsException {
		// Check that the index is within bounds.
		inBounds(x, y);
		/*
		 * Get all the distance vector values possible for this index.
		 */
		double distanceX = getDistanceVectorX(x);
		double distanceY = getDistanceVectorY(y);
		double distanceOpX = getOpDistanceVectorX(x);
		double distanceOpY = getOpDistanceVectorY(y);
		/*
		 * Mend the x and y values to represent the section the index given is
		 * in.
		 */
		x = getSectionX(x);
		y = getSectionY(y);
		/*
		 * Find the dot product of the distance of the index relative to the
		 * position of the gradient vector and the gradient vector.
		 */
		double topLeftDot = dotProduct(x, y, -distanceX, -distanceY);
		double topRightDot = dotProduct(x + 1, y, -distanceOpX, distanceY);
		double bottomLeftDot = dotProduct(x, y + 1, distanceX, -distanceOpY);
		double bottomRightDot = dotProduct(x + 1, y + 1, -distanceOpX,
				-distanceOpY);
		/*
		 * Fade the distance vectors that will be used in interpolation to make
		 * the noise look smooth.
		 */
		distanceX = NoiseMath.fade(distanceX);
		distanceY = NoiseMath.fade(distanceY);
		/*
		 * Interpolate the x axis values.
		 */
		double top = NoiseMath.interpolate(distanceX, topLeftDot, topRightDot);
		double bottom = NoiseMath.interpolate(distanceX, bottomLeftDot,
				bottomRightDot);
		/*
		 * Return the interpolation of the interpolation of the x axis values.
		 */
		return NoiseMath.interpolate(distanceY, top, bottom);
	}

}
