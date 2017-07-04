package game.Bot;

import game.Board;
import game.Field;
import game.Spot;

import java.util.ArrayList;

import static game.Field.*;

/**
 * Created by Mateusz on 2017-04-15.
 */
public class BotHard {
    Board board;
    ArrayList<Spot> tacticList = new ArrayList<Spot>();
    public BotHard(Board board){
        this.board = board;
    }

    public boolean ifDisturb(Spot first,Spot second){
        int distance = Math.abs(first.getX()-second.getX())+Math.abs(first.getY()-second.getY());
        int distanceFromSpot=1;
        Field wanted = null;
        if(board.getBoard()[first.getX()][first.getY()] == Field.X){
            wanted = Field.X;
        }else if(board.getBoard()[first.getX()][first.getY()] == Field.O){
            wanted = Field.O;
        }
        while(distanceFromSpot < distance+1){
            Spot current = null;
            Spot previous = null;
            for(int i=0; i<4; i++) {
                switch (i) {
                    case 0: {
                        current = new Spot(first.getX(), first.getY() + distanceFromSpot);
                        if (board.getBoard()[current.getX()][current.getY()] == wanted) {
                            return true;
                        }
                    }
                    break;
                    case 1: {
                        current = new Spot(first.getX() + distanceFromSpot, first.getY());
                        if (board.getBoard()[current.getX()][current.getY()] == wanted) {
                            return true;
                        }
                    }
                    break;
                    case 2: {
                        current = new Spot(first.getX() + distanceFromSpot, first.getY() + distanceFromSpot);
                        if (board.getBoard()[current.getX()][current.getY()] == wanted) {
                            return true;
                        }
                    }
                    break;
                    case 3: {
                        current = new Spot(first.getX() + distanceFromSpot, first.getY() - distanceFromSpot);
                        if (board.getBoard()[current.getX()][current.getY()] == wanted) {
                            return true;
                        }
                    }
                    break;
                }
            }
            distanceFromSpot++;
        }
        return false;
    }

    /**
     * @param player wpisujemy symbol, którym gra gracz, bot obiera symbol przeciwny. 0 - O, 1- X
     */
    public void levelHard(int player, boolean canMove, int fieldsNumber, Spot lastMove) {
        tacticList.add(lastMove);
        if (canMove) {
            boolean moveDone = false;
            Field opponentPlayer = null;
            if(player == 0){
                opponentPlayer = O;
            }else if(player == 1){
                opponentPlayer = X;
            }
            int k = 0;
            while (k<(tacticList.size()*tacticList.size())) {
                int i = (int)(Math.random()*tacticList.size());
                int j = (int)(Math.random()*tacticList.size());
                if(tacticList.size()>0){
                    System.out.println(ifDisturb(tacticList.get(i),tacticList.get(j)));
                }
                k++;
            }
            if (!moveDone) {
                int i = (int) (Math.random() * board.getSize());
                int j = (int) (Math.random() * board.getSize());
                if (board.getBoard()[i][j] == null) {
                    if (player == 1) {
                        System.out.println("Ustawiam O");
                        board.getBoard()[i][j] = O;
                        moveDone = true;
                    } else if (player == 0) {
                        System.out.println("Ustawiam X");
                        board.getBoard()[i][j] = X;
                        moveDone = true;
                    }
                }
            }
        }
    }
}
