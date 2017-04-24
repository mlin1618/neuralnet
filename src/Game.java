import org.jblas.DoubleMatrix;

import java.util.List;

/**
 * Created by Nilxam on 4/23/17.
 */
public class Game extends tictactoe{
    public List<tictactoe> history;
    public int N;
    public Network net;
    public int wins;
    public Game(int num, Network n){
        N = num;
        net = n;
        play();
    }
    public void play(){
        for(int i = 0; i < N; i++){
            TTT();
        }
    }
    public int netMove(tictactoe t){
        DoubleMatrix b = new DoubleMatrix(1, 9);
        for(int i = 0; i < 9; i++){
            b.data[i] = t.board[i];
        }
        DoubleMatrix out = net.feedforward(b);
        int move = net.maxIndex(out);
        return move;
    }
    public void TTT(){
        tictactoe t = new tictactoe();
        int netPiece = ((int)Math.random()*2) + 1;
        int randomAIPiece = 3 - netPiece;
        while(t.winner() == 0){
            if(netPiece == 1){
                int x = netMove(t);
                move(x, netPiece);
                randomAIMove(randomAIPiece);
            }
            else if(netPiece == 2){
                randomAIMove(randomAIPiece);
                int x = netMove(t);
                move(x, netPiece);
            }
        }
        if(t.winner() == netPiece){
            wins++;
        }
    }
    public double getScore(){
        return ((double)wins) / N;
    }
}
