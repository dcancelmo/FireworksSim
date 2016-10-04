
/*
 *Daniel Cancelmo
 *Project 3 -- Fireworks
 *Lab: Tues. & Thurs. 12:30-1:45
 *TA: Patrick Conway
 *I did not collaborate with anyone on this assignment.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

@SuppressWarnings("serial")
public class Fireworks extends FireworksGUI {
	
	//Instance variables
	protected static int explosionX;
	protected static int explosionY;
	protected static Random rand = new Random();
	
	//Calculates and draws the trajectory of the firework.
	public static void trajectory(Graphics img) {
		//Canvas canvas = new Canvas();
		//int daHeight = canvas.getHeight() - 4;
		//For loop to draw the trajectory using a series of small circles.
		for (double i = 0; i <= t; i += 0.005) {
			int x = (int) ((vi)*(Math.cos(angleRad)*i));
			int y = 455 - (int) ((vi*Math.sin(angleRad)*i)-(0.5*9.8*Math.pow(i, 2)));
			img.fillOval(x - (4/2), y - (4/2), 4, 4);
		}
	}
	
	//Calculates the final point of the firework. This will be used to make the firework shapes.
	public static void finalPoint() {
		//Canvas canvas = new Canvas();
		//int theHeight = canvas.getHeight() - 4;
		explosionX = (int) ((vi)*(Math.cos(angleRad)*t));
		explosionY = 455 - (int) (((vi*Math.sin(angleRad)*t)-(0.5*9.8*Math.pow(t, 2))));
	}
	
	//Draws the 'Standard' shape
	public static void standard(Graphics img) {
		System.out.println("Fireworks.standard");
		FireworksGUI gui = new FireworksGUI();
		//Identifies index that will be used for the color.
		int c = 0;
		for (int i = 0; i <= 10; i ++) {
			//If statement resets c to zero when it reaches the end, preventing an error and allowing the colors to repeat without a larger array.
			if (c == 7) {
				c = 0;
			}
			//Array of colors that are referenced by setColor
			Color[] colors = {color, Color.BLUE, color, Color.BLACK, color, Color.GREEN, color};
			img.setColor(colors[c]);
			
			//Draws four fans of lines originating at the center of the explosion
			img.drawLine(explosionX, explosionY, gui.getWidth()/15 + i*10, gui.getHeight()/15 + i*10);
			img.drawLine(explosionX, explosionY, explosionX - gui.getWidth()/15 + i*10, explosionY + gui.getHeight()/15 + i*10);
			img.drawLine(explosionX, explosionY, explosionX + gui.getWidth()/15 + i*10, explosionY- gui.getHeight()/15 + i*10);
			img.drawLine(explosionX, explosionY, -gui.getWidth()/15 + i*20, -gui.getHeight()/15 + i*20);
			c++;

		}
	}
	
	//Draws the 'Circles' shape
	@SuppressWarnings("unused")
	public static void circles(Graphics img) {
		System.out.println("Fireworks.circles");
		FireworksGUI gui = new FireworksGUI();
		//Identifies index that will be used for the color.
		int c = 0;
		//For loop to draw circles. A total of forty circles should be made
		for (int i = 0; i <= 10; i ++) {
			//If statement resets c to zero when it reaches the end, preventing an error and allowing the colors to repeat without a larger array.
			if (c == 7) {
				c = 0;
			}
			//Array of colors that are referenced by setColor
			Color[] colors = {color, Color.BLUE, color, Color.ORANGE, color, Color.MAGENTA, color};
			img.setColor(colors[c]);
			
			//Creates a random diameter of the circle for each loop
			int diameter = rand.nextInt(50)+5;
			
			//Creates circles at random distances from the center with a range of 5 to 55 pixels.
			img.drawOval(explosionX + rand.nextInt(50)+5 - diameter/2, explosionY + rand.nextInt(50)+5 - diameter/2, diameter, diameter);
			img.drawOval(explosionX + rand.nextInt(50)+5 - diameter/2, explosionY - rand.nextInt(50)+5 - diameter/2, diameter, diameter);
			img.drawOval(explosionX - rand.nextInt(50)+5 - diameter/2, explosionY + rand.nextInt(50)+5 - diameter/2, diameter, diameter);
			img.drawOval(explosionX - rand.nextInt(50)+5 - diameter/2, explosionY - rand.nextInt(50)+5 - diameter/2, diameter, diameter);

			c++;
		}
	}
	
	//Draws the 'Funnel" shape
	public static void funnel(Graphics img) {
		System.out.println("Fireworks.funnel");
		//Pixel diameter of initial circle. 
		int p = 20;
		//Identifies index that will be used for the color.
		int c = 0;
		//Four for loops that print the funnels. The locations are changed so they each radiate out in a different direction from the explosion.
		for (int i = 1; i <= 8; i++) {
			//If statement resets c to zero when it reaches the end, preventing an error and allowing the colors to repeat without a larger array.
			if (c == 6) {
				c = 0;
			}
			//Array of colors that are referenced by setColor
			Color[] colors = {color, Color.GREEN, color, Color.RED, color, Color.YELLOW};
			img.setColor(colors[c]);
			//Draws concentric circles what stay in center of frame.
			img.drawOval(explosionX, explosionY - (8/2)-p, p, p);
			//Increases pixel size for next circle.
			p += 15;
			//Allows for next color to be used.
			c++;
		}
		p = 20;
		for (int i = 1; i <= 8; i++) {
			//If statement resets c to zero when it reaches the end, preventing an error and allowing the colors to repeat without a larger array.
			if (c == 6) {
				c = 0;
			}
			//Array of colors that are referenced by setColor
			Color[] colors = {color, Color.GREEN, color, Color.RED, color, Color.YELLOW};
			img.setColor(colors[c]);
			//Draws concentric circles what stay in center of frame.
			img.drawOval(explosionX-p, explosionY - (8/2)-p, p, p);
			//Increases pixel size for next circle.
			p += 15;
			//Allows for next color to be used.
			c++;
		}
		p = 20;
		for (int i = 1; i <= 8; i++) {
			//If statement resets c to zero when it reaches the end, preventing an error and allowing the colors to repeat without a larger array.
			if (c == 6) {
				c = 0;
			}
			//Array of colors that are referenced by setColor
			Color[] colors = {color, Color.GREEN, color, Color.RED, color, Color.YELLOW};
			img.setColor(colors[c]);
			//Draws concentric circles what stay in center of frame.
			img.drawOval(explosionX-p, explosionY+p-(8/2)-p, p, p);
			//Increases pixel size for next circle.
			p += 15;
			//Allows for next color to be used.
			c++;
		}
		p = 20;
		for (int i = 1; i <= 8; i++) {
			//If statement resets c to zero when it reaches the end, preventing an error and allowing the colors to repeat without a larger array.
			if (c == 6) {
				c = 0;
			}
			//Array of colors that are referenced by setColor
			Color[] colors = {color, Color.GREEN, color, Color.RED, color, Color.YELLOW};
			img.setColor(colors[c]);
			//Draws concentric circles what stay in center of frame.
			img.drawOval(explosionX, explosionY+p-(8/2)-p, p, p);
			//Increases pixel size for next circle.
			p += 15;
			//Allows for next color to be used.
			c++;
		}
	}
	
	//Draws the 'Pokeball' shape
	public static void pokeball(Graphics img) {
		System.out.println("Fireworks.pokeball");
		FireworksGUI gui = new FireworksGUI();
		int p = gui.getWidth()/15;
		//Creates top half of Pokeball by just creating a whole oval.
		img.setColor(Color.RED);
		img.fillOval(explosionX - (p/2), explosionY - (p/2), p, p);
		//Creates bottom half of pokeball by drawing an arc (half circle) over top of the bottom half of the red oval.
		img.setColor(Color.WHITE);
		img.fillArc(explosionX - (p/2), explosionY - (p/2), p, p, 0, -180);
		//Creates the black outline of the button in the center.
		img.setColor(Color.BLACK);
		img.fillOval(explosionX - (p/10), explosionY - (p/10), p/5, p/5);
		//Creates the white inside of the button in the center. It is one pixel smaller than the black circle to show the line.
		img.setColor(Color.WHITE);
		img.fillOval(explosionX - (p/10)+1, explosionY - (p/10)+1, (p/5)-2, (p/5)-2);
	}
	
	//Draws the 'Squares' shape
	@SuppressWarnings("unused")
	public static void squares (Graphics img) {
		System.out.println("Fireworks.squares");
		FireworksGUI gui = new FireworksGUI();
		//Identifies index that will be used for the color.
		int c = 0;
		//For loop to draw squares. A total of forty squares should be made
		for (int i = 0; i <= 10; i ++) {
			//If statement resets c to zero when it reaches the end, preventing an error and allowing the colors to repeat without a larger array.
			if (c == 5) {
				c = 0;
			}
			//Array of colors that are referenced by setColor
			Color[] colors = {color, Color.RED, color, Color.BLACK, color};
			img.setColor(colors[c]);
			
			//Creates a random side length of the square for each loop
			int length = rand.nextInt(75)+5;
			
			//Creates squares at random distances from the center with a range of 5 to 25 pixels.
			img.drawRect(explosionX + rand.nextInt(20)+5 - length/2, explosionY + rand.nextInt(20)+5 - length/2, length, length);
			img.drawRect(explosionX + rand.nextInt(20)+5 - length/2, explosionY - rand.nextInt(20)+5 - length/2, length, length);
			img.drawRect(explosionX - rand.nextInt(20)+5 - length/2, explosionY + rand.nextInt(20)+5 - length/2, length, length);
			img.drawRect(explosionX - rand.nextInt(20)+5 - length/2, explosionY - rand.nextInt(20)+5 - length/2, length, length);

			c++;
		}	
	}
}
