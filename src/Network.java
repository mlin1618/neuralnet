/**
 * Created by Nilxam on 4/18/17.
 */
import java.util.*;
import java.math.*;
import org.jblas.*;

public class Network {
    public int num_layers;
    public int[] sizes;
    public List<DoubleMatrix> weights;
    public List<DoubleMatrix> biases;
    public Random R;

    public Network(int[] s){
        sizes = s;
        num_layers = s.length;
        weights = new ArrayList();
        biases = new ArrayList();
        for(int i = 0; i < num_layers-1; i++){
            int j = sizes[i+1];
            int k = sizes[i];
            weights.add(randn(j,k));
        }
        for(int i = 1; i < num_layers; i++){
            int j = sizes[i];
            biases.add(randn(j, 1));
        }
    }
    public void setWeights(List<DoubleMatrix> w){
        weights = w;
    }
    public void setBiases(List<DoubleMatrix> b){
        biases = b;
    }

    public DoubleMatrix feedforward(DoubleMatrix input){
        for(int i = 1; i < num_layers; i++){
            input = sigmoid(weights.get(i).mmul(input).add(biases.get(i)));
        }
        return input;
    }
    public DoubleMatrix randn(int r, int c){
        DoubleMatrix m = new DoubleMatrix(r, c);
        for(int i = 0; i < r*c; i++){
            m.data[i] = R.nextGaussian();
        }
        return m;
    }
    public int maxIndex(DoubleMatrix M){
        double max = 0;
        int maxIndex = 0;
        for(int i = 0; i < M.rows*M.columns; i++){
            if(M.data[i] > max){
                max = M.data[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public double sigmoid(double x){
        return 1.0/(1.0 + Math.exp(-x));
    }
    public DoubleMatrix sigmoid(DoubleMatrix x){
        for(int i = 0; i < x.rows*x.columns; i++){
            x.data[i] = sigmoid(x.data[i]);
        }
        return x;
    }
}
