/*
 *Daniel Cancelmo
 *Project 3 -- Fireworks
 *Lab: Tues. & Thurs. 12:30-1:45
 *TA: Patrick Conway
 *I did not collaborate with anyone on this assignment.
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Main class. Main method is in here
@SuppressWarnings("serial")
public class FireworksGUI extends JFrame implements ActionListener, ChangeListener{
	
	//Instance Variables
	protected JButton launchButton;
	protected JLabel velocityLabel, angleLabel, timeLabel, colorLabel, typeLabel;
	protected JSlider velocitySlider, angleSlider, timeSlider;
	protected Canvas canvas;
	@SuppressWarnings("rawtypes")
	protected JComboBox colorBox, typeBox;
	protected static double t;
	protected static double vi;
	protected static double angleRad;
	protected static Color color = Color.BLACK;
	protected String type;

	
	//Constructor. Creates the GUI with all the  sliders, comboboxes, button, and labels.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FireworksGUI() {
		launchButton = new JButton("Launch!");
		launchButton.addActionListener(this);
		add(launchButton);
		
		velocityLabel = new JLabel("Velocity (m/s): null");
		add(velocityLabel);
		
		//Slider works like this: (min, max, initial)
		velocitySlider = new JSlider(75, 125, 75);
		velocitySlider.addChangeListener(this);
		add(velocitySlider);
		
		angleLabel = new JLabel("Angle (deg): null");
		add(angleLabel);
		
		angleSlider = new JSlider(25, 70, 25);
		angleSlider.addChangeListener(this);
		add(angleSlider);
		
		timeLabel = new JLabel("Time (s): null");
		add(timeLabel);
		
		timeSlider = new JSlider(1, 3, 1);
		timeSlider.addChangeListener(this);
		add(timeSlider);
		
		colorLabel = new JLabel("Color:");
		add(colorLabel);
		
		//Drop-down menu for color selection.
		String[] colors = {"Black", "Blue", "Cyan", "Red", "Orange", "Green", "Magenta", "Pink"};
		colorBox = new JComboBox(colors);
		colorBox.addActionListener(this);
		add(colorBox);
		
		typeLabel = new JLabel("Type:");
		add(typeLabel);
		
		//Drop-down menu for type selection.
		String[] types = {"Standard", "Circles", "Funnel", "Pokeball", "Squares"};
		typeBox = new JComboBox(types);
		typeBox.addActionListener(this);
		add(typeBox);
		//Defaults to 'Standard' type.
		type = (String) typeBox.getSelectedItem();
		
		//Sets the behavior of the frame.
		setLayout(new FlowLayout());
		setTitle("Fireworks GUI");
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//JButton (launchButton) and JComboBox (colorBox and typeBox) are in actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("FireworksGUI.actionPerformed");
		Object source = e.getSource();
		//If-else-if statement to identify source.
		if (source == launchButton) {
			//Creates an instance of the nested frame class.
			Frame frame = new Frame();
			frame.setVisible(true);
		} else if (source == colorBox) {
			//Converts the color to a string.
			String colorString = (String) colorBox.getSelectedItem();
			//Identifies the color and changes the selected color to the user's choice.
			switch (colorString) {
				case "Black":
					color = Color.BLACK;
					break;
				case "Blue":
					color = Color.BLUE;
					break;
				case "Cyan":
					color = Color.CYAN;
					break;
				case "Red":
					color = Color.RED;
					break;
				case "Orange":
					color = Color.ORANGE;
					break;
				case "Green":
					color = Color.GREEN;
					break;
				case "Magenta":
					color = Color.MAGENTA;
					break;
				case "Pink":
					color = Color.PINK;
					break;
			}
			System.out.println("Color changed to " + String.valueOf(color));
		} else if (source == typeBox) {
			//Changes the type of the explosion to the user desired type.
			type = (String) typeBox.getSelectedItem();
		}
	}
	
	//JSlider (all sliders) are in stateChanged
	@Override
	public void stateChanged(ChangeEvent e) {
		System.out.println("FireworksGUI.stateChanged");
		Object source = e.getSource();
		//If-else-if identifies source
		if (source == velocitySlider) {
			//Sets value of velocity to the slider value.
			velocityLabel.setText("Velocity (m/s): " + String.valueOf(velocitySlider.getValue()));
			vi = velocitySlider.getValue();
		} else if (source == angleSlider) {
			//Sets value of angle to the slider value.
			angleLabel.setText("Angle (deg): " + String.valueOf(angleSlider.getValue()));
			angleRad = angleSlider.getValue();
			//Converts from degrees to radians.
			angleRad = Math.toRadians(angleRad);
		} else if (source == timeSlider) {
			//Sets value of time to the slider value.
			timeLabel.setText("Time (s): " + String.valueOf(timeSlider.getValue()));
			t = timeSlider.getValue();
		}
	}
	
	//Main method. Creates instance of FireworksGui and sets visible.
	public static void main(String[] args) {
		new FireworksGUI().setVisible(true);
	}

	
	//Nested class. Creates a new frame.
	public class Frame extends JFrame {
		public Frame() {
			setTitle("Fireworks Demo");
			setSize(640, 480);
			setResizable(false);
			//Calls the Canvas class nested in FireworksGUI and adds it to the frame.
			Canvas canvas = new Canvas();
			add(canvas);
			canvas.setVisible(true);
		}	
	}
	
	//Nested class. creates the canvas the explosion will draw on.
	public class Canvas extends JPanel {	
		@SuppressWarnings("unused")
		@Override
		public void paintComponent(Graphics img) {
			System.out.println("FireworksGUI.Canvas.paintComponent");
			super.paintComponent(img);
			int x = 0;
			int y = getHeight()-4;
			img.setColor(color);
			//img.fillOval(x, y, 4, 4);
			//Calls trajectory method in Fireworks class to draw the trajectory.
			Fireworks.trajectory(img);
			//Calls the finalPoint method in Fireworks class to find the final point of the explosion.
			Fireworks.finalPoint();
			//Switch case identifies and calls the type of firework selected.
			switch (type) {
				case "Standard":
					Fireworks.standard(img);
					break;
				case "Circles":
					Fireworks.circles(img);
					break;
				case "Funnel":
					Fireworks.funnel(img);
					break;
				case "Pokeball":
					Fireworks.pokeball(img);
					break;
				case "Squares":
					Fireworks.squares(img);
					break;
				default:
					Fireworks.standard(img);
					break;
			}
		}

	}
	
	
}
