package renderers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import advNoise2D.AdvPerlin2D;
import advNoise2D.AdvScales2D;
import advNoise2D.AdvSimplex2D;
import advNoise2D.AdvSquares2D;
import advNoise2D.AdvTriangles2D;
import advNoise2D.AdvWood2D;
import coherentNoise2D.Noise2D;
import coherentNoise2D.Perlin2D;
import coherentNoise2D.Scales2D;
import coherentNoise2D.Simplex2D;
import coherentNoise2D.Squares2D;
import coherentNoise2D.Triangles2D;
import coherentNoise2D.Wood2D;

public class Renderer2D extends Renderer {
	/**
	 * The noise selected to be rendered as a Noise1D object, utilizing
	 * inheritance.
	 */
	private Noise2D toDraw = new Perlin2D(10, 10, 1000, 1000);

	public Renderer2D(int frequency, int length, int octaves, double persistence)
			throws IllegalArgumentException {
		super(frequency, length, octaves, persistence);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BufferedImage getGraphic() throws ArithmeticException {
		/*
		 * Save the dimensions locally for use.
		 */
		int width = toDraw.getWidth();
		int height = toDraw.getHeight();
		/*
		 * Create a new image and set it to be drawn.
		 */
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		/*
		 * For every element in the noise, draw in to the screen with a color
		 * dependent on the value of the noise.
		 */
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				double temp = toDraw.getNoise(i, j);
				if (temp > 1 || temp < -1) {
					throw new ArithmeticException(
							"The noise value must be in the range of [-1, 1].");
				}
				g2.setColor(getColor(temp));
				g2.drawRect(i, j, 1, 1);
			}
		}
		return image;
	}

	@Override
	public void resetGraphic(Noise noiseType) {
		switch (noiseType) {
		case PERLIN:
			toDraw = new Perlin2D(super.getFrequency(),
					super.getFrequency(), super.getLength(), super.getLength());
			break;
		case SIMPLEX:
			toDraw = new Simplex2D(super.getFrequency(),
					super.getFrequency(), super.getLength(), super.getLength());
			break;
		case SCALES:
			toDraw = new Scales2D(super.getFrequency(),
					super.getFrequency(), super.getLength(), super.getLength());
			break;
		case SQUARES:
			toDraw = new Squares2D(super.getFrequency(),
					super.getFrequency(), super.getLength(), super.getLength());
			break;
		case TRIANGLES:
			toDraw = new Triangles2D(super.getFrequency(),
					super.getFrequency(), super.getLength(), super.getLength());
			break;
		case WOOD:
			toDraw = new Wood2D(super.getFrequency(),
					super.getFrequency(), super.getLength(), super.getLength());
			break;
		case ADV_PERLIN:
			toDraw = new AdvPerlin2D(super.getFrequency(), super.getFrequency(), super.getLength(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_SIMPLEX:
			toDraw = new AdvSimplex2D(super.getFrequency(), super.getFrequency(), super.getLength(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_SCALES:
			toDraw = new AdvScales2D(super.getFrequency(), super.getFrequency(), super.getLength(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_SQUARES:
			toDraw = new AdvSquares2D(super.getFrequency(), super.getFrequency(), super.getLength(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_TRIANGLES:
			toDraw = new AdvTriangles2D(super.getFrequency(), super.getFrequency(), super.getLength(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		case ADV_WOOD:
			toDraw = new AdvWood2D(super.getFrequency(), super.getFrequency(), super.getLength(), super.getLength(),
					super.getOctaves(), super.getPersistence());
			break;
		}
	}

}
