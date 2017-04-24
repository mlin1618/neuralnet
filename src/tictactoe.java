/**
 * Created by Nilxam on 4/17/17.
 */
import java.util.*;
import java.math.*;
import org.jblas.*;

public class tictactoe {
    public int[] board;
    public List<int[]> history;
    public int X = 1;
    public int O = 2;
    public boolean turn; //true = X, false = O
    public tictactoe(){

    }
    public void newBoard(){
        board = new int[9];
    }
    public boolean isEmpty(){
        for(int i = 0; i < board.length; i++){
            if(board[i] != 0) {
                return false;
            }
        }
        return true;
    }
    public int getSquare(int i){
        return board[i];
    }
    public void move(int i, int p){
        history.add(board);
        board[i] = p;
        turn = !turn;
    }
    public void undo(){
        board = history.get(history.size()-2);
        history.remove(history.size()-1);
    }
    public int winner(){
        for(int i = 0; i < 3; i++){
            if((board[i] + board[i+3] + board[i+6] == 3)){
                return X;
            }
            else if(board[i] + board[i+3] + board[i+6] == 6) {
                return O;
            }
        }
        if(board[1] + board[5] + board[7] == 3 || board[3]+board[5]+board[7]==3){
            return X;
        }
        else if(board[1] + board[5] + board[7] == 6 || board[3]+board[5]+board[7]==6) {
            return O;
        }
        return 0;
    }
    public String toString(){
        String s = "";
        for(int i = 0; i < 3; i++){
            s+=(getPiece(board[3*i]) + "" + getPiece(board[3*i+1]) + "" + getPiece(board[3*i+2]));
            s+="\n";
        }
        return s;
    }
    public boolean equals(tictactoe b){
        return Arrays.equals(this.board, b.board);
    }
    public List<Integer> availableSquares(){
        List<Integer> a = new ArrayList<Integer>();
        for(int i= 0; i < 9; i++){
            if(board[i] == 0){
                a.add(i);
            }
        }
        return a;
    }
    public String getPiece(int x){
        if(x == 1)
            return "X";
        else if(x == 2)
            return "O";
        return " ";
    }
    public void randomAIMove(int p){
        List a = availableSquares();
        int x = (int)Math.random()*a.size();
        move(x, p);
    }
}
