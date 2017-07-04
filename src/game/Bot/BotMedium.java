package game.Bot;

import game.Board;
import game.Field;
import game.Spot;

/**
 * Created by Mateusz on 2017-04-15.
 */
public class BotMedium {
    Board board;
    public BotMedium(Board board){
        this.board = board;
    }

    /**
     * @param player wpisujemy symbol, którym gra gracz, bot obiera symbol przeciwny. 0 - O, 1- X
     */
    public void levelMedium(int player, boolean canMove) {
        if (canMove) {
            boolean moveDone = false;
            Field opponentPlayer = null;
            if(player == 0){
                opponentPlayer = Field.O;
            }else if(player == 1){
                opponentPlayer = Field.X;
            }
            while (!moveDone) {
                    for(int k=0; k<board.getSize(); k++){
                        for(int l=0; l<board.getSize(); l++){
                            if(board.getBoard()[k][l] == opponentPlayer){
                                Spot currentSpot = new Spot(k,l);
                                int i = (int)(Math.random()*3);
                                int j = (int)(Math.random()*3);
                                currentSpot = new Spot(k-(i-1), l-(j-1));
                                if(!board.checkOut(currentSpot) && board.getBoard()[currentSpot.getX()][currentSpot.getY()]==null && !moveDone){
                                    if(player == 1){
                                        board.setO(currentSpot);
                                        System.out.println("Ustawiam O");
                                        moveDone = true;
                                    }else if(player == 0){
                                        board.setX(currentSpot);
                                        System.out.println("Ustawiam X");
                                        moveDone = true;
                                    }
                                }
                            }
                        }

                    }
            }
        }
    }
}
