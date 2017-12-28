package coherentNoise1D;

/**
 * This class defines a promised interface for all 1D noise made in this
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
public abstract class Noise1D {
	/**
	 * Default constructor.
	 */
	public Noise1D() {
		
	}
	
	/**
	 * @abstract
	 * @return How long the 1D noise is as an integer.
	 */
	public abstract int getSize();

	/**
	 * This function will return the noise value at the given coordinate. The
	 * value be in between or equal to -1 and 1, [-1, 1].
	 * 
	 * @abstract
	 * @param x
	 *            the index of the noise generated as an integer.
	 * @return A double between the values of zero and one.
	 * @throws IndexOutOfBoundsException
	 *             If the input is greater than the length of the noise.
	 */
	public abstract double getNoise(int x) throws IndexOutOfBoundsException;
}
