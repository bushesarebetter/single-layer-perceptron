 package neural_networks;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.io.Console;
public class Trainer extends JPanel {
	int range_x = 400;
	int range_y = 400;
	int radius = 8;
	float m = 4f;
	float b = 0.2f;
	
	Perceptron perceptron;
	float f(float x) {
		return m * x + b;
	}
	class Point {
		float x;
		float y;
		float bias = 1;
		int label;
		int guess;
		Random random = new Random();
		
		Point() {
			x = random.nextFloat() * 2 -1;
			y = random.nextFloat() * 2 -1;
			
			label = (y > f(x) ) ? 1 : -1;
		}
		Point(float x_, float y_) {
			x = x_;
			y = y_;
			
			label = (y > f(x)) ? 1 : -1;
		}
		

		public int getLabel() {
			return label;
		}
		public void setGuess(int guess) {
			this.guess = guess;
		}
		public boolean isCorrect() {
			return guess == label;
		}
		public int xValue() {
			float [] currentRange = {-1.0f, 1.0f};
			float [] expectedRangeX = {0.0f, range_x};
			return (int) map(currentRange, expectedRangeX, x);
		}
		public int yValue() {
			float [] currentRange = {-1.0f, 1.0f};
			float [] expectedRangeY = {0.0f, range_y};
			return (int) map(currentRange, expectedRangeY, -y);
		}
	}
	
	private List<Point> points;
	
	public Trainer(int numPoints, Perceptron perceptron_) {
		points = new ArrayList<>();
		for (int i = 0; i < numPoints; i++) {
			points.add(new Point());
		}
		perceptron = perceptron_;
	}
	public List<Point> getTrainingData() {
        return points;
    }

	float map(float[] currentRange, float[] expectedRange, float value) {
	    float minC = currentRange[0];
	    float maxC = currentRange[1];
	    
	    float minE = expectedRange[0];
	    float maxE = expectedRange[1];

	    // Normalize value to [0,1] range
	    float normalized = (value - minC) / (maxC - minC);

	    // Scale to new range
	    return minE + (normalized * (maxE - minE));
	}

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    g.setColor(Color.BLACK);
	    
	    Point p1 = new Point(-1, f(-1));
	    Point p2 = new Point(1, f(1));
	    
	    g.drawLine(p1.xValue(), p1.yValue(), p2.xValue(), p2.yValue()); // Draw decision boundary
	    	
	    Point p3 = new Point(-1, perceptron.guessY(-1));
	    Point p4 = new Point(1, perceptron.guessY(1));
	    g.drawLine(p3.xValue(), p3.yValue(), p4.xValue(), p4.yValue()); // Draw decision boundary

	    float[] currentRange = {-1.0f, 1.0f};
	    float[] expectedRangeX = {0.0f, (float) range_x};
	    float[] expectedRangeY = {0.0f, (float) range_y};

	    for (Point p : points) {
	        g.setColor((p.guess == 1) ? Color.RED : Color.BLUE);
	        
	        int mappedX = (int) map(currentRange, expectedRangeX, p.x);
	        int mappedY = (int) map(currentRange, expectedRangeY, -p.y);


	        g.fillOval(mappedX - radius / 2, mappedY - radius / 2, radius, radius);
	    }
	}

	
	
	public void showFrame() {
		JFrame frame = new JFrame("Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(range_x + 30, range_y + 30);
        frame.setVisible(true);
	}


}
