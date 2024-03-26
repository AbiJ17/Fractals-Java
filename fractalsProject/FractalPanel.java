package fractalsProject;

import javax.swing.*; 
import java.awt.*;     // awt - abstract window toolkit

//Class that calls the panel containing the fractals (demonstrates the recursive drawing of fractals)
public class FractalPanel extends JPanel{
	
	//`'global' variables, instance variables (fields), class-wide variables
	
			// Stores color used to draw fractal
			private Color color; 
			
			// Stores current level of fractal
			private int level; 
			
			// Constructor method - Sets the initial fractal level to the value specified and sets up JPanel specifications
			public FractalPanel(int currentLevel) { 
				
				// Initialize the color of the drawing to blue, and the initial fractal level
				color = Color.RED; 
				level = currentLevel; 
				
				// Initializes the background color as white and sets the dimensions of the fractals
				setBackground(Color.WHITE); 
				setPreferredSize(new Dimension(800, 600)); 
				 
				
			} // end method FractalPanel
			
			public void drawDragonCurveFractal(int level, int x1, int y1, int x2, int y2, Graphics g) {
				// Placeholder for the correct implementation of the Dragon Curve
				if (level == 0) {
					g.drawLine(x1, y1, x2, y2);
				} else {
					int xm = (x1 + x2) / 2 + (y1 - y2) / 2;
					int ym = (y1 + y2) / 2 + (x2 - x1) / 2;
					drawDragonCurveFractal(level - 1, x1, y1, xm, ym, g);
					drawDragonCurveFractal(level - 1, xm, ym, x2, y2, g);
				}
			}
			
			// Recursive method of drawing Lo Fractals
			public void drawLoFractal(int level, int x1, int y1, int x2, int y2, Graphics g) {
				
				// base case: draw a line connecting 2 given points
				if (level == 0)
					g.drawLine(x1, y1, x2, y2);
				
				// recursion step: determine new points, draw next level
				else { 
					
					// calculate midpoint between (x1, y1) and (x2, y2) 
					int x3 = (x1 + x2) / 2; 
					int y3 = (y1 + y2) / 2; 
					
					// Calculate the 4th point (x4, y4) which forms an isosceles right triangle between (x1, y1) and (x3, y3) 
					// where the right angle is at (x4, y4) 
					int x4 = x1 + (x3 - x1) / 2 - (y3 - y1) / 2; 
					int y4 = y1 + (y3 - y1) / 2 + (x3 - x1) / 2; 
					
					// recursively draw the fractal
					drawLoFractal(level - 1, x4, y4, x1, y1, g); 
					drawLoFractal(level - 1, x4, y4, x3, y3, g); 
					drawLoFractal(level - 1, x4, y4, x2, y2, g); 
					
				} // end else
			} // end method drawLoFractal
			
			// Recursive method of drawing Sierpinski Triangle
			public void drawSierpinskiTriangle(int level, int x1, int y1, int x2, int y2, int x3, int y3, Graphics g) {
				
				// Base case: Draw a regular triangle
				if(level ==0) {
					g.drawLine(x1, y1, x2, y2);
					g.drawLine(x2, y2, x3, y3);
					g.drawLine(x3, y3, x1, y1);
				}
				
				// Recursion step: 
				else { 
					
					//Finds midpoint on side 1
					int xn1 = (x1+x2)/2;
					int yn1 = (y1+y2)/2;
					
					//Finds midpoint on side 2
					int xn2 = (x2+x3)/2;
					int yn2 = (y2+y3)/2;
					
					//Finds midpoint on side 3
					int xn3 = (x3+x1)/2;
					int yn3 = (y3+y1)/2;
					
					//create 3 new triangles by connecting each vertex of the 
					//previous triangle to the midpoint and connect the midpoints to each other
					drawSierpinskiTriangle(level-1, x1, y1, xn1, yn1, xn3, yn3, g);	
					drawSierpinskiTriangle(level-1, xn1, yn1, x2, y2, xn2, yn2, g);
					drawSierpinskiTriangle(level-1, x3, y3, xn3, yn3, xn2, yn2, g);
				}
				
			}
			
			// Recursive method of drawing Koch Snowflake
			public void drawkochSnowflake(int level, int x1, int y1, int x2, int y2, Graphics g) { 
				
				// Base Case: Draw a regular triangle
				if (level == 0) 
					g.drawLine(x1, y1, x2, y2);
				
				// Recursion Step
				else {
					
					// Determines the length of the line segment (ensure they are divided into equal line segments) 
					int dX = x2 - x1;
					int dY = y2 - y1;					

					// Use an equilateral triangle to divide the middle of the first line segment into 3 equal parts
					int x3 = x1 + dX / 3; 
					int y3 = y1 + dY / 3;
			
					// Use an equilateral triangle to divide the middle of the second line segment into 3 equal parts 
			        int x4 = x1 + 2 * dX / 3; 
			        int y4 = y1 + 2 * dY / 3; 
	
			        // Use an equilateral triangle to divide the middle of the third line segment into 3 equal parts
			        int x5 = (int)(0.5 * (x1 + x2) + Math.sqrt(3) * (y1 - y2) / 6); 
					int y5 = (int)(0.5 * (y1 + y2) + Math.sqrt(3) * dX / 6); 
					
					// Keeps on continuing the dividing process to form the Koch Snowflake
			
			        // Recursively draw the fractal (Adds outward bends to each side of the previous level (equilateral triangle), making smaller equilateral triangles) 
			        drawkochSnowflake(level - 1, x1, y1, x3, y3, g);
			        drawkochSnowflake(level - 1, x3, y3, x5, y5, g);
			        drawkochSnowflake(level - 1, x5, y5, x4, y4, g);
			        drawkochSnowflake(level - 1, x4, y4, x2, y2, g);
				}
					
			}
			
			// Method that draws the fractal
			public void paintComponent(Graphics g){
				
				// Call the JPanel's paintComponent method first
				super.paintComponent(g);   
			
				// Sets the color of the fractal
				g.setColor(color); 
			
				// Draws the Dragon Curve fractal pattern
				if (FractalFrame.fractalType.equals("Dragon Curve"))
					drawDragonCurveFractal(level, 500, 200, 300, 200, g);
				
				// Draws the Lo Fractal Pattern
				else if(FractalFrame.fractalType.equals("Lo Fractal"))
					drawLoFractal(level, 100, 300, 700, 300, g); 
				
				// Draws the Lo Star Fractal Pattern
				else if (FractalFrame.fractalType.equals("Lo Star Fractal")) {
					
					drawLoFractal(level, 400, 300, 400, 100, g); 
					drawLoFractal(level, 400, 300, 590, 238, g); 
					drawLoFractal(level, 400, 300, 540, 450, g); 
					drawLoFractal(level, 400, 300, 260, 450, g); 
					drawLoFractal(level, 400, 300, 210, 238, g); 
				}
				
				// Draws the Sierpinski Triangle
				else if (FractalFrame.fractalType.equals("Sierpinski Triangle")) {
					drawSierpinskiTriangle(level, 200, 400, 600, 400, 400, 100, g); 
					
				}
				
				// Draws the Koch Snowflake
				else if (FractalFrame.fractalType.equals("Koch Snowflake")) { 
					
					drawkochSnowflake(level, 200, 400, 600, 400, g); 
					drawkochSnowflake(level, 600, 400, 400, 100, g);
					drawkochSnowflake(level, 400, 100, 200, 400, g);
					
				}
			}
			
			// Sets the default/chosen color
			public void setColor(Color c) {
				color = c; 
			}
			
			// Sets the current/new level of recursion
			public void setLevel(int currentLevel) { 
				level = currentLevel; 
			}
			
			// Returns the level of the fractal/drawing
			public int getLevel() { 
				return level; 
			}
}
