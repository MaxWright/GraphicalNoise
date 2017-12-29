package renderers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import advNoise1D.AdvPerlin1D;
import coherentNoise1D.Noise1D;
import coherentNoise1D.Perlin1D;
import coherentNoise1D.Scales1D;
import coherentNoise1D.Simplex1D;
import coherentNoise1D.Squares1D;
import coherentNoise1D.Triangles1D;
import coherentNoise1D.Wood1D;

public class Renderer1D extends Renderer {

	Noise1D toDraw = new Perlin1D(10, 1000);

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
			if(temp > 1 || temp < -1) {
				throw new ArithmeticException("The noise value must be in the range of [-1, 1].");
			}
			int length = (int)(200 * (temp + 1));
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
			break;
		case ADV_SCALES:
			break;
		case ADV_SQUARES:
			break;
		case ADV_TRIANGLES:
			break;
		case ADV_WOOD:
			break;
		}
	}
}
