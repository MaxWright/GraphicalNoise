package coherentNoise2D;

/**
 * This class defines a promised interface for all 2D noise made in this
 * library.
 * 
 * @abstract
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public abstract class Noise2D {
	/**
	 * Default constructor.
	 */
	public Noise2D() {

	}

	/**
	 * @abstract
	 * @return The width 2D noise is as an integer.
	 */
	public abstract int getWidth();

	/**
	 * @abstract
	 * @return The height 2D noise is as an integer.
	 */
	public abstract int getHeight();

	/**
	 * This function will return the noise value at the given coordinate. The
	 * value be in the range of [-1, 1].
	 * 
	 * @abstract
	 * @param x
	 *            The index of the noise generated as an integer on the x axis.
	 * @param y
	 *            The index of the noise generated as an integer on the y axis.
	 * @return A double between the values of zero and one.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If the input is greater than the length of the noise.
	 */
	public abstract double getNoise(int x, int y)
			throws IndexOutOfBoundsException;

}
