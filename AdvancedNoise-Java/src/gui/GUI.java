/*
filename    GUI.java
author      Max Wright
uploaded    12/20/2017

Brief Description:
  This object extends JFrame and acts as a display for the PatternCanvas
  object.
  
  © 2017 Max Wright. All rights reserved. 
  Free for use. Use at your own risk, Max Wright will not be liable for any
  damages caused by this code.
 */

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

/**
 * This object is the GUI for displaying different noises.
 * 
 * @author Max Wright
 * @version 2.0
 */
public class GUI implements ActionListener {

	/**
	 * A JComponent that stores and renders the noise functions.
	 */
	Renderer2D renderer;

	/**
	 * Default constructor, which constructs the GUI.
	 */
	public GUI() {
		// Create the JFrame and set attributes
		JFrame frame = new JFrame("Noise");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Noise Generator");
		frame.setMinimumSize(new Dimension(1500, 1000));
		frame.setLayout(new BorderLayout());

		/*
		 * Initialize the renderer which holds and displays different noise
		 * functions.
		 */
		renderer = new Renderer2D(10, 71);
		Renderer1D renderer1D = new Renderer1D();
		frame.add(renderer1D, BorderLayout.CENTER);
		frame.add(noiseSelector(), BorderLayout.WEST);
		frame.add(intensitySliders(), BorderLayout.EAST);
		frame.add(saveComponents(), BorderLayout.NORTH);
		frame.add(rgbSliders(), BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private JPanel noiseSelector() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		/*
		 * Define the radio buttons to toggle diffenert noises.
		 */
		// Perlin
		JRadioButton perlin = new JRadioButton("Perlin Noise");
		perlin.setActionCommand("0");
		perlin.setSelected(true);

		// Simplex
		JRadioButton simplex = new JRadioButton("Simplex Noise");
		simplex.setActionCommand("1");

		// Squares (inverse of Perlin)
		JRadioButton squares = new JRadioButton("Squares");
		squares.setActionCommand("2");

		// Scales
		JRadioButton scales = new JRadioButton("Scales");
		scales.setActionCommand("3");

		// Wood
		JRadioButton wood = new JRadioButton("Wood");
		wood.setActionCommand("4");

		// Triangles
		JRadioButton triangles = new JRadioButton("Triangles");
		triangles.setActionCommand("5");

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

		/*
		 * Place radio buttons onto JPanel.
		 */
		panel.add(perlin);
		panel.add(simplex);
		panel.add(squares);
		panel.add(scales);
		panel.add(wood);
		panel.add(triangles);

		/*
		 * Add functionality to radio buttons.
		 */
		perlin.addActionListener(this);
		simplex.addActionListener(this);
		squares.addActionListener(this);
		scales.addActionListener(this);
		wood.addActionListener(this);
		triangles.addActionListener(this);

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
		panel.setLayout(new GridLayout(3, 1));

		/*
		 * Create the red slider. Manipulation of this slider will change how
		 * much red is present in the noise. 127 is the maximum, 0 is minimum.
		 */
		JSlider setRed = new JSlider(JSlider.HORIZONTAL, 0, 127, 127);
		// Set background to red to signify this slider manipulates the red
		// value.
		setRed.setBackground(Color.RED);
		setRed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				// Set the red in the renderer to the value of the slider.
				renderer.setRed((int) source.getValue());
			}
		});

		/*
		 * Create the green slider. Manipulation of this slider will change how
		 * much red is present in the noise. 127 is the maximum, 0 is minimum.
		 */
		JSlider setGreen = new JSlider(JSlider.HORIZONTAL, 0, 127, 127);
		// Set background to green to signify this slider manipulates the green
		// value.
		setGreen.setBackground(Color.GREEN);
		setGreen.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				// Set the green in the renderer to the value of the slider.
				renderer.setGreen((int) source.getValue());
			}
		});

		/*
		 * Create the blue slider. Manipulation of this slider will change how
		 * much red is present in the noise. 127 is the maximum, 0 is minimum.
		 */
		JSlider setBlue = new JSlider(JSlider.HORIZONTAL, 0, 127, 127);
		// Set background to blue to signify this slider manipulates the blue
		// value.
		setBlue.setBackground(Color.BLUE);
		setBlue.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				// Set the blue in the renderer to the value of the slider.
				renderer.setBlue((int) source.getValue());
			}
		});

		panel.add(setRed);
		panel.add(setGreen);
		panel.add(setBlue);

		return panel;
	}

	/**
	 * Creates a slider that manipulates the intensity of the noise
	 * 
	 * @return JPanel containing a slider.
	 */
	private JPanel intensitySliders() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));

		/*
		 * Create the intensity slider. Manipulation of this slider will change
		 * the squares that define the noise. The values range from 1 to 50.
		 */
		JSlider intensity1 = new JSlider(JSlider.VERTICAL, 1, 50, 10);
		intensity1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				if (!source.getValueIsAdjusting()) {
					int frequency = (int) source.getValue();
					int width = 700 / frequency;
					/*
					 * For the simplex noise function, it is written for the
					 * inner squares to have an odd length. It makes it easier
					 * for code implementation. If the width is even, make it
					 * odd.
					 */
					if (width % 2 == 0) {
						++width;
					}
					renderer.reset(frequency, width);
				}
			}
		});
		panel.add(intensity1);
		return panel;
	}

	/**
	 * Create a JTextField whose string will be used as the name of the save of
	 * the graphic made from {@link Renderer2D}, creates a JButton who will
	 * execute the save, create a second JButton to swtich between the type of
	 * noise being displayed.
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
					ImageIO.write(renderer.getGraphic(), "png", myNewLineArt);
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
		renderer.change(e.getActionCommand().charAt(0) - 48);
	}

}
