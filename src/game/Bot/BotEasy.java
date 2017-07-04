package game.Bot;

import game.Board;
import game.Field;

/**
 * Created by Mateusz on 2017-04-15.
 */
public class BotEasy {
    Board board;
    public BotEasy(Board board){
        this.board = board;
    }

    /**
     * @param player wpisujemy symbol, którym gra gracz, bot obiera symbol przeciwny. 0 - O, 1- X
     */
    public void levelEasy(int player, boolean canMove) {
        if (canMove) {
            boolean moveDone = false;
            int i;
            int j;
            while (!moveDone) {
                i = (int) (Math.random() * board.getSize());
                j = (int) (Math.random() * board.getSize());
                if (board.getBoard()[i][j] == null) {
                    if (player == 1) {
                        System.out.println("Ustawiam O");
                        board.getBoard()[i][j] = Field.O;
                        moveDone = true;
                    } else if (player == 0) {
                        System.out.println("Ustawiam X");
                        board.getBoard()[i][j] = Field.X;
                        moveDone = true;
                    }
                }
            }
        }
    }
}
