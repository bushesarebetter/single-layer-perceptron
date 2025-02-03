package neural_networks;
import java.util.Random;



public class Perceptron {
	
	
	Random rand = new Random();
	float[] weights;
	
	float lr = 0.04f;
	int sign(float input) {
		return (input > 0) ? 1 : -1;
	}
	Perceptron(int n) {
		weights = new float[n];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = rand.nextFloat(-1, 1);
			weights[i] = -1.0f;
			System.out.println(weights[i]);
		}
	}
	
	int guess(float[] inputs) {
		float sum = 0;
		for(int i =0; i < weights.length; i++) {
			sum += inputs[i]* weights[i]; 
		}
		
		return sign(sum);
	}
	
	void train(float[] inputs, int target) {
		int guess = guess(inputs);
		int error = target - guess;
		if(error != 0) {
			System.out.println(error);
		}
		for (int i = 0; i < weights.length; i++) {
			weights[i] += error * inputs[i] * lr;
			System.out.println(weights[i]);
		}


	}
	
	float guessY(float x) {

		// w0x + w1y + w2b = 0
		// y = - w0/w1 * x + - w2/w1 * b
		
		return -(weights[0]/weights[1]) *x - weights[2]/weights[1];
	}
}
