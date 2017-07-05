package game.Bot;

import game.Board;
import game.Field;
import game.Spot;
import display.Panel;

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

    public void doMove(int x, int y, int player){
        if (player == 1) {
            System.out.println("Ustawiam O");
            board.setO(new Spot(x,y));
        } else if (player == 0) {
            System.out.println("Ustawiam X");
            board.setX(new Spot(x,y));
        }
    }

    public void levelHard(Spot enemy, int fieldsNumber){
        int distanceFromSpot=2;
        Field wanted = null;
        boolean moveDone = false;
        int player = -1;
        if(board.getBoard()[enemy.getX()][enemy.getY()] == Field.X){
            wanted = Field.X;
            player = 1;
        }else if(board.getBoard()[enemy.getX()][enemy.getY()] == Field.O){
            wanted = Field.O;
            player = 0;
        }
        while(distanceFromSpot < fieldsNumber){
            Spot current = null;
            for(int i=0; i<4 ; i++) {
                switch (i) {
                    case 0: {
                        current = new Spot(enemy.getX(), enemy.getY() + distanceFromSpot);
                        if (board.getBoard()[current.getX()][current.getY()] == wanted && !board.checkOut(current)) {
                            doMove(current.getX(),current.getY()-1,player);
                            moveDone = true;
                        }
                    }
                    break;
                    case 1: {
                        current = new Spot(enemy.getX() + distanceFromSpot, enemy.getY());
                        if (board.getBoard()[current.getX()][current.getY()] == wanted && !board.checkOut(current)) {
                            doMove(current.getX()-1,current.getY(),player);
                            moveDone = true;
                        }
                    }
                    break;
                    case 2: {
                        current = new Spot(enemy.getX() + distanceFromSpot, enemy.getY() + distanceFromSpot);
                        if (board.getBoard()[current.getX()][current.getY()] == wanted && !board.checkOut(current)) {
                            doMove(current.getX()-1,current.getY()-1,player);
                            moveDone = true;
                        }
                    }
                    break;
                    case 3: {
                        current = new Spot(enemy.getX() + distanceFromSpot, enemy.getY() - distanceFromSpot);
                        if (board.getBoard()[current.getX()][current.getY()] == wanted && !board.checkOut(current)) {
                            doMove(current.getX()+1,current.getY()+1,player);
                            moveDone = true;
                        }
                    }
                    break;
                }
            }
            distanceFromSpot++;
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
