package fractalsProject;

import java.awt.Color; 
import java.awt.FlowLayout;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import javax.swing.*; 

//Method demonstrates user interface for drawing a fractal (Sets up the GUI) 
public class FractalFrame extends JFrame implements ActionListener {
	
	//`'global' variables, instance variables (fields), class-wide variables
	
			// Creates the menu bar
			private JMenuBar menubar = new JMenuBar(); 
			private JMenu fractalMenu = new JMenu("Fractal"); 
			private JMenuItem[] fractalArray = new JMenuItem[5]; 
			
			// Creates the 2 panels
			private JPanel controlPanel = new JPanel(); 
			private FractalPanel fractalPanel = new FractalPanel(0); 
			
			// Creates the 4 buttons/sections that are in the menu bar
			private JButton changeColorButton = new JButton("Color"); 
			private JButton increaseLevelButton = new JButton("Increase Level"); 
			private JButton decreaseLevelButton = new JButton("Decrease Level"); 
			private JLabel levelLabel = new JLabel("Level: 0"); 
			
			// Fractals run from level 0 - 20, which are constants (makes code more understandable and clear)
			private static final int MIN_LEVEL = 0, MAX_LEVEL = 20; 
			
			// Variable that carries the fractal type
			// static - available throughout the program
			public static String fractalType = ""; 
			
			// Constructor Method - constructs a new Fractal Frame 
			public FractalFrame() { 
				
				// Setting up the frame with the starting values and sizes
				setLayout(new FlowLayout());     // Lays out the panels and buttons in the frame
				setTitle("Fractal App"); 
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				setSize(800, 650); 
				
				// Lists the fractals user can choose from
				fractalArray[0] = new JMenuItem("Dragon Curve"); 
				fractalArray[1] = new JMenuItem("Lo Fractal");
				fractalArray[2] = new JMenuItem("Lo Star Fractal"); 
				fractalArray[3] = new JMenuItem("Sierpinski Triangle"); 
				fractalArray[4] = new JMenuItem("Koch Snowflake"); 
				
				// Adding action listeners to menu items
				for (JMenuItem item: fractalArray) {
					fractalMenu.add(item); 
					item.addActionListener(this); 
				}
				
				// Add fractal menu onto menu bar
				menubar.add(fractalMenu); 
				setJMenuBar(menubar); 
				
				// Adds the 3 control buttons onto the menu bar
				setupControlButtons(); 
				
				// Adding the level label onto the control panel
				controlPanel.add(levelLabel); 
				
				// Add the 2 panels onto the menu bar
				add(controlPanel); 
				add(fractalPanel); 
				
				// Make the fractal application visible to user
				setVisible(true); 
				
			}
			
			// Sets up the 3 control buttons 
			// Void - only performs a task, doesn't need to return anything
			private void setupControlButtons() { 
				
				// Adds color button to the control panel, also registers listener
				controlPanel.add(changeColorButton); 
				changeColorButton.addActionListener(
						new ActionListener(){    // anonymous inner class
							
							// process changeColorButton event
							public void actionPerformed(ActionEvent event){
								
								// Allows user to select color
								Color color = JColorChooser.showDialog(FractalFrame.this, "Choose a color", Color.RED); 
								
								// sets default color, if no color is returned
								if (color == null)
									color = Color.BLUE; 
								
								// Returns the color chosen by the user
								fractalPanel.setColor(color); 
								
							} // end method actionPerformed
						} // end anonymous inner class
					);  // end actionListener
				
				// Adds the decrease level button to the control panel and register listener
				controlPanel.add(decreaseLevelButton); 
				decreaseLevelButton.addActionListener(
						new ActionListener(){      // anonymous inner class
							
							// process decreaseLevelButton event
							public void actionPerformed(ActionEvent event){
								
								// decrease level by one
								int level = fractalPanel.getLevel(); 
								level--; 
								
								// modify level if possible
								if (level >= MIN_LEVEL && level <= MAX_LEVEL) { 
									levelLabel.setText("Level: " + level);
									fractalPanel.setLevel(level); 
									repaint(); 
								} 
										
							} // end method actionPerformed
						}	// end anonymous inner class
				); // end actionListener
				
				// Adds the increase level button to the control panel and registers listener
				controlPanel.add(increaseLevelButton); 
				increaseLevelButton.addActionListener(
						new ActionListener(){   // anonymous inner class
							
							// process increaseLevelButton event
							public void actionPerformed(ActionEvent event){
										
								// increase level by one 
								int level = fractalPanel.getLevel(); 
								level++; 
										
								// modify level if possible
								if (level >= MIN_LEVEL && level <= MAX_LEVEL) { 
									levelLabel.setText("Level: " + level);
									fractalPanel.setLevel(level); 
									repaint(); 
								} 
												
							} // end method actionPerformed
						} // end anonymous inner class
				); // end actionListener
			}
				
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() instanceof JMenuItem){
					JMenuItem menuItem = (JMenuItem)event.getSource();
					fractalType = menuItem.getText(); 
					levelLabel.setText("Level: 0"); // Consider setting a higher initial level if needed
					fractalPanel.setLevel(0); // Or set to a higher level if the fractal doesn't show at level 0
					fractalPanel.repaint(); // Ensure this calls the paintComponent in FractalPanel
				}
				// No need to handle color changes here, it's done in setupControlButtons
			}
}
