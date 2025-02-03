package neural_networks;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
	static int trainingCycle = 0;
	
    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron(3);
        Trainer trainer = new Trainer(100, perceptron); // Generate 10 random training points
    	for (Trainer.Point pt: trainer.getTrainingData()) {
            float[] inputs = {pt.x, pt.y, pt.bias};
            pt.setGuess(perceptron.guess(inputs));
    	} 
        JFrame frame = new JFrame("Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(trainer);
        frame.setSize(trainer.range_x, trainer.range_y);
        frame.setVisible(true);
        //trainer.showFrame(); // Display training points


        
        frame.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		retrain(trainer, perceptron);
        	}
        });
    }
    
    public static void retrain(Trainer trainer, Perceptron perceptron) {
    	for (Trainer.Point pt: trainer.getTrainingData()) {
            float[] inputs = {pt.x, pt.y, pt.bias} ;
            perceptron.train(inputs, pt.label);
            int prediction = perceptron.guess(inputs);
            pt.setGuess(prediction);
    	} 

    	trainer.repaint();
    }
}

