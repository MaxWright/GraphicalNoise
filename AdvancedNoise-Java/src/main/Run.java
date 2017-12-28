package main;

import gui.GUI;

import javax.swing.SwingUtilities;

/**
 * This class holds the main function.
 * 
 * @author Max Wright
 * @version 1.0
 * 
 * @copyright Max Wright, All Rights Reserved
 * @license LICENSE
 * 
 */
public class Run {
	public static void main(String args[]) {
		Runnable doRun = new Runnable() {
			public void run() {
				new GUI();
			}
		};
		SwingUtilities.invokeLater(doRun);
	}
}
