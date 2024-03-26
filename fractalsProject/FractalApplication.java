package fractalsProject;

import javax.swing.SwingUtilities;

// Class that contains the program/application
public class FractalApplication {
	
	public static void main(String[] args) {
				// Invocation of the FractalFrame to start the application
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new FractalFrame();
					}
				});
	}


}
