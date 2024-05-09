import java.util.Arrays;

public class Perceptron {
    private double[] weights;
    private double bias;
    private double learningRate;

    public Perceptron(int numInputs, double learningRate) {
        this.weights = new double[numInputs];
        this.bias = 0.0;
        this.learningRate = learningRate;
    }

    public int predict(double[] inputs) {
        double weightedSum = 0.0;
        for (int i = 0; i < weights.length; i++) {
            weightedSum += weights[i] * inputs[i];
        }
        double activation = weightedSum + bias;
        return activation > 0 ? 1 : 0;
    }

    public void train(double[][] inputs, int[] labels, int numEpochs) {
        for (int epoch = 0; epoch < numEpochs; epoch++) {
            for (int i = 0; i < inputs.length; i++) {
                int predictedLabel = predict(inputs[i]);
                int trueLabel = labels[i];
                double error = trueLabel - predictedLabel;
                updateWeights(inputs[i], error);
            }
        }
    }

    private void updateWeights(double[] inputs, double error) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] += learningRate * error * inputs[i];
        }
        bias += learningRate * error;
    }

    public double[] getWeights() {
        return weights;
    }

    public static void main(String[] args) {
        double[][] inputs = {{0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}, {1, 1, 1, 1}};
        int[] labels = {0, 0, 0, 1};
        Perceptron p = new Perceptron(4, 0.1);
        p.train(inputs, labels, 100);
        System.out.println(Arrays.toString(p.getWeights()));
        System.out.println(p.predict(new double[]{0, 0, 0, 0}));  // Output: 0
        System.out.println(p.predict(new double[]{0, 1, 0, 1}));  // Output: 0
        System.out.println(p.predict(new double[]{1, 0, 1, 0}));  // Output: 0
        System.out.println(p.predict(new double[]{1, 1, 1, 1}));  // Output: 1
    }
}