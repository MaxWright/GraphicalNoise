package gui;

import javax.swing.JRadioButton;

import renderers.Noise;

public class NoiseRadioButton extends JRadioButton {
	private Noise noiseType;
	NoiseRadioButton(String title, Noise noiseType) {
		super(title);
		this.noiseType = noiseType;
	}

	public Noise getAssociatedNoise() {
		return noiseType;
	}
}
