package renderers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import advNoise1D.AdvPerlin1D;
import advNoise1D.AdvScales1D;
import advNoise1D.AdvSimplex1D;
import advNoise1D.AdvSquares1D;
import advNoise1D.AdvTriangles1D;
import advNoise1D.AdvWood1D;
import coherentNoise1D.Noise1D;
import coherentNoise1D.Perlin1D;
import coherentNoise1D.Scales1D;
import coherentNoise1D.Simplex1D;
import coherentNoise1D.Squares1D;
import coherentNoise1D.Triangles1D;
import coherentNoise1D.Wood1D;

/**
 * This class renders all 1D Noise.
 * 
 * @extends Renderer
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class Renderer1D extends Renderer {

	/**
	 * The noise selected to be rendered as a Noise1D object, utilizing
	 * inheritance.
	 */
	Noise1D toDraw = new Perlin1D(10, 1000);

	/**
	 * 
	 * @param frequency
	 *            The number of independent sections in the noise as an integer.
	 * @param length
	 *            The length of the noise to be made as an integer.
	 * @param octaves
	 *            The number of octaves in the noise as an integer.
	 * @param persistence
	 *            How quickly the amplitude of each octave descends as a double
	 * @throws IllegalArgumentException
	 *             As determined by the parent class' constructor
	 *             {@link Renderer#Renderer(int, int, int, double)}
	 * @extends Renderer
	 */
	public Renderer1D(int frequency, int length, int octaves, double persistence)
			throws IllegalArgumentException {
		super(frequency, length, octaves, persistence);
	}

	@Override
	public BufferedImage getGraphic() throws ArithmeticException {
		int size = toDraw.getSize();
		BufferedImage image = new BufferedImage(size, 400,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(getColor(1));
		for (int i = 0; i < size; ++i) {
			double temp = toDraw.getNoise(i);
			if (temp > 1 || temp < -1) {
				throw new ArithmeticException(
						"The noise value must be in the range of [-1, 1].");
			}
			int length = (int) (200 * (temp + 1));
			g2.drawRect(i, 400 - length, 1, length);
		}
		return image;
	}

	@Override
	public void resetGraphic(Noise noiseType) {
		switch (noiseType) {
		case PERLIN:
			toDraw = new Perlin1D(super.getFrequency(), super.getLength());
			break;
		case SIMPLEX:
			toDraw = new Simplex1D(super.getFrequency(), super.getLength());
			break;
		case SCALES:
			toDraw = new Scales1D(super.getFrequency(), super.getLength());
			break;
		case SQUARES:
			toDraw = new Squares1D(super.getFrequency(), super.getLength());
			break;
		case TRIANGLES:
			toDraw = new Triangles1D(super.getFrequency(), super.getLength());
			break;
		case WOOD:
			toDraw = new Wood1D(super.getFrequency(), super.getLength());
			break;
		case ADV_PERLIN:
			toDraw = new AdvPerlin1D(super.getFrequency(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_SIMPLEX:
			toDraw = new AdvSimplex1D(super.getFrequency(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_SCALES:
			toDraw = new AdvScales1D(super.getFrequency(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_SQUARES:
			toDraw = new AdvSquares1D(super.getFrequency(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_TRIANGLES:
			toDraw = new AdvTriangles1D(super.getFrequency(),
					super.getLength(), super.getOctaves(),
					super.getPersistence());
			break;
		case ADV_WOOD:
			toDraw = new AdvWood1D(super.getFrequency(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		}
	}
}
