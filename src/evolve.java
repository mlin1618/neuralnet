/**
 * Created by Nilxam on 4/23/17.
 */
import java.util.*;
import org.jblas.*;
public class evolve {
    public List<Network> population;
    public List<Double> scores;
    public evolve(){
        population = new ArrayList<Network>();
    }
    public void generatePopulation(int N, int h){
        int[] s = {9, h, 9};
        for(int i = 0; i < N; i++){
            population.add(new Network(s));
        }
    }
    public double scoreNet(int target){
        Game g = new Game(100, population.get(target));
        return g.getScore();
    }
    public void scorePop(){
        for(int i = 0; i < population.size(); i++){
            scores.add(scoreNet(i));
        }
    }
    public Network splice(Network a, Network b){
        if(a.equals(b)){
            return a;
        }
        for(int i = 0; i < a.biases.size(); i++){
            for(int j = 0; j < a.biases.get(i).columns; j++){
                
            }
        }

    }
    public boolean coinFlip(){
        return Math.random() < 0.5;
    }
    public Network reproduce(){

    }
}
