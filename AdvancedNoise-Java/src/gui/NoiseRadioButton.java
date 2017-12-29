package gui;

import javax.swing.JRadioButton;

import renderers.Noise;

/**
 * This class creates a special version of JRadioButton that is associated with
 * a Noise from the enum {@link renderers.Noise}
 * 
 * @extends JRadioButton
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class NoiseRadioButton extends JRadioButton {
	/**
	 * The serial version of this class as required by JRadioButton.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The type of noise associated with this radio button, saved as a Noise
	 * type.
	 */
	private Noise noiseType;

	/**
	 * The constructor to make a new radio button associated with a type of
	 * noise.
	 * 
	 * @param display
	 *            The text that will be displayed to the string for this radio
	 *            button as a string.
	 * @param noiseType
	 *            The noise that will associated with this button from the enum
	 *            {@link renderers.Noise}
	 * @extends JRadioButton
	 */
	NoiseRadioButton(String display, Noise noiseType) {
		super(display);
		this.noiseType = noiseType;
	}

	/**
	 * 
	 * @return The Noise associated with this NoiseRadioButton.
	 */
	public Noise getAssociatedNoise() {
		return noiseType;
	}
}
