package game.Bot;

import game.Board;
import game.Field;
import game.Spot;

/**
 * Created by Mateusz on 2017-04-15.
 */
public class BotMedium implements Bot{
    private Board board;
    public BotMedium(Board board){
        this.board = board;
    }

    public void step(Spot enemy, int fieldsNumber, boolean canMove) {
        int player = -1;
        if(board.getBoard()[enemy.getX()][enemy.getY()] == Field.X){
            player = 1;
        }else if(board.getBoard()[enemy.getX()][enemy.getY()] == Field.O){
            player = 0;
        }
        if (canMove) {
            boolean moveDone = false;
            Field opponentPlayer = null;
            if(player == 0){
                opponentPlayer = Field.O;
            }else if(player == 1){
                opponentPlayer = Field.X;
            }
            while (!moveDone) {
                                Spot currentSpot = enemy;
                                int i = (int)(Math.random()*3);
                                int j = (int)(Math.random()*3);
                                currentSpot = new Spot(enemy.getX()-(i-1), enemy.getY()-(j-1));
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



