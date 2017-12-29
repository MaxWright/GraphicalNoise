package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import renderers.Noise;

/**
 * This class creates a user interface to view and manipulate the graphical
 * noise.
 * 
 * @implements ActionListener
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class GUI implements ActionListener {

	/**
	 * A JComponent that displays the noise function specified through the
	 * interactive components of the GUI.
	 */
	RendererDisplay rendererDisplay;

	/**
	 * Default constructor, which constructs the GUI.
	 */
	public GUI() {
		// Create the JFrame and set attributes
		JFrame frame = new JFrame("Graphical Noise Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Noise Generator");
		frame.setMinimumSize(new Dimension(1500, 1000));
		frame.setLayout(new BorderLayout());

		/*
		 * Initialize the renderer which holds and displays different noise
		 * functions.
		 */
		rendererDisplay = new RendererDisplay();
		frame.add(rendererDisplay, BorderLayout.CENTER);
		frame.add(noiseSelector(), BorderLayout.WEST);
		frame.add(rgbSliders(), BorderLayout.EAST);
		frame.add(saveComponents(), BorderLayout.NORTH);
		frame.add(attributeSliders(), BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	/**
	 * This function constructs and defines the functionality of radio buttons
	 * that determine the dimension and type of noise.
	 * 
	 * @return A JPanel of radio buttons that determines the dimension of noise
	 *         to display and which type of noise to display.
	 */
	private JPanel noiseSelector() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		/*
		 * Toggle between 1D and 2D noise.
		 */
		JRadioButton oneDim = new JRadioButton("1D");
		oneDim.setActionCommand("1D");
		oneDim.setSelected(true);
		JRadioButton twoDim = new JRadioButton("2D");
		twoDim.setActionCommand("2D");

		ButtonGroup dimensions = new ButtonGroup();
		dimensions.add(oneDim);
		dimensions.add(twoDim);

		panel.add(oneDim);
		panel.add(twoDim);

		oneDim.addActionListener(this);
		twoDim.addActionListener(this);
		/*
		 * Define the radio buttons to toggle different noises.
		 */
		// Perlin
		JRadioButton perlin = new NoiseRadioButton("Perlin Noise", Noise.PERLIN);
		perlin.setSelected(true);

		// Simplex
		JRadioButton simplex = new NoiseRadioButton("Simplex Noise",
				Noise.SIMPLEX);

		// Scales
		JRadioButton scales = new NoiseRadioButton("Scales", Noise.SCALES);

		// Squares (inverse of Perlin)
		JRadioButton squares = new NoiseRadioButton("Squares", Noise.SQUARES);

		// Triangles
		JRadioButton triangles = new NoiseRadioButton("Triangles",
				Noise.TRIANGLES);

		// Wood
		JRadioButton wood = new NoiseRadioButton("Wood", Noise.WOOD);

		// Adv_Perlin
		JRadioButton adv_perlin = new NoiseRadioButton("Adv Perlin Noise",
				Noise.ADV_PERLIN);

		// Adv_Simplex
		JRadioButton adv_simplex = new NoiseRadioButton("Adv Simplex Noise",
				Noise.ADV_SIMPLEX);

		// Adv_Scales
		JRadioButton adv_scales = new NoiseRadioButton("Adv Scales",
				Noise.ADV_SCALES);

		// Adv_Squares (inverse of Perlin)
		JRadioButton adv_squares = new NoiseRadioButton("Adv Squares",
				Noise.ADV_SQUARES);

		// Adv_Triangles
		JRadioButton adv_triangles = new NoiseRadioButton("Adv Triangles",
				Noise.ADV_TRIANGLES);

		// Adv_Wood
		JRadioButton adv_wood = new NoiseRadioButton("Adv Wood", Noise.ADV_WOOD);

		/*
		 * This object exists to group the radio buttons together so that the
		 * radio buttons function like radio buttons.
		 */
		ButtonGroup noiseSelectors = new ButtonGroup();
		noiseSelectors.add(perlin);
		noiseSelectors.add(simplex);
		noiseSelectors.add(squares);
		noiseSelectors.add(scales);
		noiseSelectors.add(wood);
		noiseSelectors.add(triangles);
		noiseSelectors.add(adv_perlin);
		noiseSelectors.add(adv_simplex);
		noiseSelectors.add(adv_squares);
		noiseSelectors.add(adv_scales);
		noiseSelectors.add(adv_wood);
		noiseSelectors.add(adv_triangles);

		/*
		 * Place radio buttons onto JPanel.
		 */
		panel.add(perlin);
		panel.add(simplex);
		panel.add(squares);
		panel.add(scales);
		panel.add(wood);
		panel.add(triangles);
		panel.add(adv_perlin);
		panel.add(adv_simplex);
		panel.add(adv_squares);
		panel.add(adv_scales);
		panel.add(adv_wood);
		panel.add(adv_triangles);

		/*
		 * Add functionality to radio buttons.
		 */
		perlin.addActionListener(this);
		simplex.addActionListener(this);
		squares.addActionListener(this);
		scales.addActionListener(this);
		wood.addActionListener(this);
		triangles.addActionListener(this);
		adv_perlin.addActionListener(this);
		adv_simplex.addActionListener(this);
		adv_squares.addActionListener(this);
		adv_scales.addActionListener(this);
		adv_wood.addActionListener(this);
		adv_triangles.addActionListener(this);

		return panel;
	}

	/**
	 * This creates a series of sliders that manipulates the RGB values in the
	 * renderer.
	 * 
	 * @return JPanel containing sliders.
	 */
	private JPanel rgbSliders() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));

		/*
		 * Create the red slider. Manipulation of this slider will change how
		 * much red is present in the noise. 127 is the maximum, 0 is minimum.
		 */
		JSlider setRed = new JSlider(JSlider.VERTICAL, 0, 127, 127);
		/*
		 * Set background to red to signify this slider manipulates the red
		 * value.
		 */
		setRed.setBackground(Color.RED);
		setRed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				/*
				 * Set the red in the rendererDisplay to the value of the
				 * slider.
				 */
				rendererDisplay.setRed((int) source.getValue());
			}
		});

		/*
		 * Create the green slider. Manipulation of this slider will change how
		 * much red is present in the noise. 127 is the maximum, 0 is minimum.
		 */
		JSlider setGreen = new JSlider(JSlider.VERTICAL, 0, 127, 127);
		/*
		 * Set background to green to signify this slider manipulates the green
		 * value.
		 */
		setGreen.setBackground(Color.GREEN);
		setGreen.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				/*
				 * Set the green in the rendererDisplay to the value of the
				 * slider.
				 */
				rendererDisplay.setGreen((int) source.getValue());
			}
		});

		/*
		 * Create the blue slider. Manipulation of this slider will change how
		 * much red is present in the noise. 127 is the maximum, 0 is minimum.
		 */
		JSlider setBlue = new JSlider(JSlider.VERTICAL, 0, 127, 127);
		/*
		 * Set background to blue to signify this slider manipulates the blue
		 * value.
		 */
		setBlue.setBackground(Color.BLUE);
		setBlue.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				/*
				 * Set the blue in the rendererDisplay to the value of the
				 * slider.
				 */
				rendererDisplay.setBlue((int) source.getValue());
			}
		});

		panel.add(setRed);
		panel.add(setGreen);
		panel.add(setBlue);

		return panel;
	}

	/**
	 * Creates sliders that manipulates the frequency, octaves, and persistence
	 * of the noise
	 * 
	 * @return A JPanel containing a three sliders that manipulates the
	 *         frequency, octaves, and persistence of the noise.
	 */
	private JPanel attributeSliders() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));

		/*
		 * Create the intensity slider. Manipulation of this slider will change
		 * the squares that define the noise. The values range from 1 to 50.
		 */
		JSlider frequencySlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 10);
		frequencySlider.setToolTipText("Frequency Slider");
		frequencySlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				if (!source.getValueIsAdjusting()) {
					try {
						rendererDisplay.setFrequency(source.getValue());
					} catch (IllegalArgumentException e) {
						rendererDisplay.setFrequency(rendererDisplay
								.getMaxFrequency());
						source.setValue(rendererDisplay.getMaxFrequency());
					}
				}
			}
		});

		JSlider octaveSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
		octaveSlider.setToolTipText("Octave Slider");
		octaveSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				if (!source.getValueIsAdjusting()) {
					try {
						rendererDisplay.setOctaves(source.getValue());
					} catch (IllegalArgumentException e) {
						rendererDisplay.setOctaves(rendererDisplay
								.getMaxOctaves());
						source.setValue(rendererDisplay.getMaxOctaves());
					}
				}
			}
		});

		JSlider persistenceSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 100);
		persistenceSlider.setToolTipText("Persistence Slider");
		persistenceSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				if (!source.getValueIsAdjusting()) {
					try {
						rendererDisplay.setPersistance((double) source
								.getValue() / 100);
					} catch (IllegalArgumentException e) {
						rendererDisplay.setPersistance(1);
						source.setValue(100);
					}
				}
			}
		});

		panel.add(frequencySlider);
		panel.add(octaveSlider);
		panel.add(persistenceSlider);
		return panel;
	}

	/**
	 * Create a JTextField whose string will be used as the name of the save of
	 * the graphic made from {@link RendererDisplay}, creates a JButton that
	 * will execute the save.
	 * 
	 * @return A JPanel with a JTextField and two buttons.
	 */
	private JPanel saveComponents() {
		JPanel tile = new JPanel();

		/*
		 * Creates an editable JTextField that the user can type in to define
		 * the save name of the file.
		 */
		JTextField saveName = new JTextField("", 15);
		saveName.setEditable(true);

		/*
		 * Creates a button that when pressed, saves the image of the noise with
		 * the entered name.
		 */
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = saveName.getText();
				try {
					File myNewLineArt = new File(temp + ".png");
					ImageIO.write(rendererDisplay.getGraphic(), "png",
							myNewLineArt);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		tile.add(saveName);
		tile.add(save);

		return tile;
	}

	/**
	 * This function defines the functionality of the radio buttons inside the
	 * noiseSelector function.
	 */
	public void actionPerformed(ActionEvent e) {
		// Account for the dimension radio buttons first.
		String toCompare = e.getActionCommand();
		if (toCompare.equals("1D")) {
			rendererDisplay.setDimension(1);
		} else if (toCompare.equals("2D")) {
			rendererDisplay.setDimension(2);
		} else {
			/*
			 * Now account for the noise radio buttons. Because of the special
			 * NoiseRadioButton class, the source must be casted. If another
			 * normal radio button has been added without accounting for
			 * functionality, the try catch block is included.
			 */
			try {

				NoiseRadioButton button = (NoiseRadioButton) e.getSource();
				rendererDisplay.resetGraphic(button.getAssociatedNoise());
			} catch (ClassCastException exc) {

			}
		}
	}

}
