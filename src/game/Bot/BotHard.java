package game.Bot;

import game.Board;
import game.Field;
import game.Spot;

/**
 * Created by Mateusz on 2017-04-15.
 */
public class BotHard {
    Board board;

    public BotHard(Board board) {this.board = board;}

    public boolean doMove(int x, int y, int player) {
        if (player == 1) {
            System.out.println("Ustawiam O");
            return board.setO(new Spot(x, y));
        } else if (player == 0) {
            System.out.println("Ustawiam X");
            return board.setX(new Spot(x, y));
        }
        return false;
    }

    public Spot findC(Spot enemy, int fieldsNumber, Field sign) {
        int size = board.getSize() - 1;
        Spot current = null;
        for (int i = 0; i < 8; i++) {
            int distanceFromSpot = 2;
            while (distanceFromSpot < fieldsNumber) {
                switch (i) {
                    case 0: {
                        current = new Spot(enemy.getX(), enemy.getY() + distanceFromSpot);
                        if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                            break;
                        }
                        if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                            //doMove(current.getX(),current.getY()-1,player);
                            Spot temp = new Spot(current.getX(), current.getY() - 1);
                            if (!board.checkOut(temp)) {
                                return temp;
                            }
                        }
                    }
                    break;
                    case 1: {
                        current = new Spot(enemy.getX(), enemy.getY() - distanceFromSpot);
                        if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                            break;
                        }
                        if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                            //doMove(current.getX(),current.getY()+1,player);
                            Spot temp = new Spot(current.getX(), current.getY() + 1);
                            if (!board.checkOut(temp)) {
                                return temp;
                            }
                        }
                    }
                    break;
                    case 2: {
                        current = new Spot(enemy.getX() + distanceFromSpot, enemy.getY());
                        if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                            break;
                        }
                        if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                            //doMove(current.getX()-1,current.getY(),player);
                            Spot temp = new Spot(current.getX() - 1, current.getY());
                            if (!board.checkOut(temp)) {
                                return temp;
                            }
                        }
                    }
                    break;
                    case 3: {
                        current = new Spot(enemy.getX() - distanceFromSpot, enemy.getY());
                        if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                            break;
                        }
                        if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                            //doMove(current.getX()+1,current.getY()+1,player);
                            Spot temp = new Spot(current.getX() + 1, current.getY());
                            if (!board.checkOut(temp)) {
                                return temp;
                            }
                        }
                    }
                    break;
                    case 4: {
                        current = new Spot(enemy.getX() + distanceFromSpot, enemy.getY() + distanceFromSpot);
                        if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                            break;
                        }
                        if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                            //doMove(current.getX()-1,current.getY()-1,player);
                            Spot temp = new Spot(current.getX() - 1, current.getY() - 1);
                            if (!board.checkOut(temp)) {
                                return temp;
                            }
                        }
                    }
                    break;
                    case 5: {
                        current = new Spot(enemy.getX() - distanceFromSpot, enemy.getY() - distanceFromSpot);
                        if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                            break;
                        }
                        if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                            //doMove(current.getX()+1,current.getY()+1,player);
                            Spot temp = new Spot(current.getX() + 1, current.getY() + 1);
                            if (!board.checkOut(temp)) {
                                return temp;
                            }
                        }
                    }
                    break;
                    case 6: {
                        current = new Spot(enemy.getX() + distanceFromSpot, enemy.getY() - distanceFromSpot);
                        if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                            break;
                        }
                        if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                            //doMove(current.getX()-1,current.getY()+1,player);
                            Spot temp = new Spot(current.getX() - 1, current.getY() + 1);
                            if (!board.checkOut(temp)) {
                                return temp;
                            }
                        }
                    }
                    break;
                    case 7: {
                        current = new Spot(enemy.getX() - distanceFromSpot, enemy.getY() + distanceFromSpot);
                        if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                            break;
                        }
                        if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                            //doMove(current.getX()+1,current.getY()-1,player);
                            Spot temp = new Spot(current.getX() + 1, current.getY() - 1);
                            if (!board.checkOut(temp)) {
                                return temp;
                            }
                        }
                    }
                    break;
                }
                distanceFromSpot++;
            }
        }
        return null;
    }

    public Spot findB(Spot enemy, int fieldsNumber, Field sign) {
        int size = board.getSize() - 1;
        Spot current = null;
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0: {
                    current = new Spot(enemy.getX(), enemy.getY() + 1);
                    if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                        break;
                    }
                    if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX(),current.getY()-1,player);
                        Spot temp = new Spot(current.getX(), current.getY() - 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 1: {
                    current = new Spot(enemy.getX(), enemy.getY() - 1);
                    if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                        break;
                    }
                    if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX(),current.getY()+1,player);
                        Spot temp = new Spot(current.getX(), current.getY() + 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 2: {
                    current = new Spot(enemy.getX() + 1, enemy.getY());
                    if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                        break;
                    }
                    if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()-1,current.getY(),player);
                        Spot temp = new Spot(current.getX() - 2, current.getY());
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 3: {
                    current = new Spot(enemy.getX() - 1, enemy.getY());
                    if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                        break;
                    }
                    if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()+1,current.getY()+1,player);
                        Spot temp = new Spot(current.getX() + 2, current.getY());
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 4: {
                    current = new Spot(enemy.getX() + 1, enemy.getY() + 1);
                    if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                        break;
                    }
                    if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()-1,current.getY()-1,player);
                        Spot temp = new Spot(current.getX() - 2, current.getY() - 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 5: {
                    current = new Spot(enemy.getX() - 1, enemy.getY() - 1);
                    if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                        break;
                    }
                    if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()+1,current.getY()+1,player);
                        Spot temp = new Spot(current.getX() + 2, current.getY() + 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 6: {
                    current = new Spot(enemy.getX() + 1, enemy.getY() - 1);
                    if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                        break;
                    }
                    if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()-1,current.getY()+1,player);
                        Spot temp = new Spot(current.getX() - 2, current.getY() + 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 7: {
                    current = new Spot(enemy.getX() - 1, enemy.getY() + 1);
                    if (current.getX() < 0 || current.getY() < 0 || current.getX() > size || current.getY() > size) {
                        break;
                    }
                    if (board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()+1,current.getY()-1,player);
                        Spot temp = new Spot(current.getX() + 2, current.getY() - 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
            }
        }
        return null;
    }

    public Spot findA(Spot enemy, int fieldsNumber, Field sign) {
        int size = board.getSize() - 1;
        Spot current = null;
        Spot current2 = null;
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0: {
                    current = new Spot(enemy.getX(), enemy.getY() + 1);
                    current2 = new Spot(enemy.getX(), enemy.getY() + 2);
                    if (board.checkOut(current)) {
                        break;
                    }
                    if (board.getBoard()[current2.getX()][current2.getY()] == sign && board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX(),current.getY()-1,player);
                        Spot temp = new Spot(current.getX(), current.getY() - 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 1: {
                    current = new Spot(enemy.getX(), enemy.getY() - 1);
                    current2 = new Spot(enemy.getX(), enemy.getY() - 2);
                    if (board.checkOut(current)) {
                        break;
                    }
                    if (board.getBoard()[current2.getX()][current2.getY()] == sign && board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX(),current.getY()+1,player);
                        Spot temp = new Spot(current.getX(), current.getY() + 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 2: {
                    current = new Spot(enemy.getX() + 1, enemy.getY());
                    current2 = new Spot(enemy.getX() + 2, enemy.getY());
                    if (board.checkOut(current)) {
                        break;
                    }
                    if (board.getBoard()[current2.getX()][current2.getY()] == sign && board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()-1,current.getY(),player);
                        Spot temp = new Spot(current.getX() - 2, current.getY());
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 3: {
                    current = new Spot(enemy.getX() - 1, enemy.getY());
                    current2 = new Spot(enemy.getX() - 2, enemy.getY());
                    if (board.checkOut(current)) {
                        break;
                    }
                    if (board.getBoard()[current2.getX()][current2.getY()] == sign && board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()+1,current.getY()+1,player);
                        Spot temp = new Spot(current.getX() + 2, current.getY());
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 4: {
                    current = new Spot(enemy.getX() + 1, enemy.getY() + 1);
                    current2 = new Spot(enemy.getX() + 2, enemy.getY() + 2);
                    if (board.checkOut(current)) {
                        break;
                    }
                    if (board.getBoard()[current2.getX()][current2.getY()] == sign && board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()-1,current.getY()-1,player);
                        Spot temp = new Spot(current.getX() - 2, current.getY() - 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 5: {
                    current = new Spot(enemy.getX() - 1, enemy.getY() - 1);
                    current2 = new Spot(enemy.getX() - 2, enemy.getY() - 2);
                    if (board.checkOut(current)) {
                        break;
                    }
                    if (board.getBoard()[current2.getX()][current2.getY()] == sign && board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()+1,current.getY()+1,player);
                        Spot temp = new Spot(current.getX() + 2, current.getY() + 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 6: {
                    current = new Spot(enemy.getX() + 1, enemy.getY() - 1);
                    current2 = new Spot(enemy.getX() + 2, enemy.getY() - 2);
                    if (board.checkOut(current)) {
                        break;
                    }
                    if (board.getBoard()[current2.getX()][current2.getY()] == sign && board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()-1,current.getY()+1,player);
                        Spot temp = new Spot(current.getX() - 2, current.getY() + 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
                case 7: {
                    current = new Spot(enemy.getX() - 1, enemy.getY() + 1);
                    current2 = new Spot(enemy.getX() - 2, enemy.getY() + 2);
                    if (board.checkOut(current)) {
                        break;
                    }
                    if (board.getBoard()[current2.getX()][current2.getY()] == sign && board.getBoard()[current.getX()][current.getY()] == sign && !board.checkOut(current)) {
                        //doMove(current.getX()+1,current.getY()-1,player);
                        Spot temp = new Spot(current.getX() + 2, current.getY() - 2);
                        if (!board.checkOut(temp)) {
                            return temp;
                        }
                    }
                }
                break;
            }
        }
        return null;
    }

    public void levelHard(Spot enemy, int fieldsNumber, boolean canMove) {
        Field wanted = null;
        boolean moveDone = false;
        int player = -1;
        if (board.getBoard()[enemy.getX()][enemy.getY()] == Field.X) {
            wanted = Field.X;
            player = 1;
        } else if (board.getBoard()[enemy.getX()][enemy.getY()] == Field.O) {
            wanted = Field.O;
            player = 0;
        }
        if (canMove) {
            Spot found = findA(enemy, fieldsNumber, wanted);
            if (found != null) {
                System.out.println("Wariant A:" + found.getX() + ":-" + found.getY());
                if (doMove(found.getX(), found.getY(), player)) {
                    moveDone = true;
                }
            } else {
                found = findB(enemy, fieldsNumber, wanted);
                if (found != null) {
                    System.out.println("Wariant B:" + found.getX() + ":-" + found.getY());
                    if (doMove(found.getX(), found.getY(), player)) {
                        moveDone = true;
                    }
                } else {
                    found = findC(enemy, fieldsNumber, wanted);
                    if (found != null) {
                        System.out.println("Wariant B:" + found.getX() + ":-" + found.getY());
                        if (doMove(found.getX(), found.getY(), player)) {
                            moveDone = true;
                        }
                    }
                }
            }
                while (!moveDone) {
                    Spot currentSpot = enemy;
                    int i = (int) (Math.random() * 3);
                    int j = (int) (Math.random() * 3);
                    currentSpot = new Spot(enemy.getX() - (i - 1), enemy.getY() - (j - 1));
                    if (!board.checkOut(currentSpot) && board.getBoard()[currentSpot.getX()][currentSpot.getY()] == null && !moveDone) {
                        if (player == 1) {
                            board.setO(currentSpot);
                            System.out.println("Ustawiam O");
                            moveDone = true;
                        } else if (player == 0) {
                            board.setX(currentSpot);
                            System.out.println("Ustawiam X");
                            moveDone = true;
                        }
                    }
                }
            }
        }
    }


