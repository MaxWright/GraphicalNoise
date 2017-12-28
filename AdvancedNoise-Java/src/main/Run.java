/*
filename    Run.java
author      Max Wright
uploaded    12/20/2017

Brief Description:
  This object extends JFrame and acts as a display for the PatternCanvas
  object.
  
  © 2017 Max Wright. All rights reserved. 
  Free for use. Use at your own risk, Max Wright will not be liable for any
  damages caused by this code.
 */
package main;

import gui.GUI;

import javax.swing.SwingUtilities;

/**
 * Executes and runs a new interface.
 * 
 * @author Max
 * @version 1.0
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
