package neural_networks;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class DrawCircle extends JPanel {
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Set circle properties
        int x = 100; // x coordinate of the top-left corner of the bounding rectangle
        int y = 100; // y coordinate of the top-left corner of the bounding rectangle
        int diameter = 20; // Diameter of the circle
        
        g.setColor(Color.RED); // Set circle color
        g.drawOval(x, y, diameter, diameter); // Draw the circle
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Circle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DrawCircle());
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
