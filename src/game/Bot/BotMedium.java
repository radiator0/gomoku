package game.Bot;

import game.Board;
import game.Field;

/**
 * Created by Mateusz on 2017-04-15.
 */
public class BotMedium {
    Board b;
    public BotMedium(Board b){
        this.b = b;
    }

    public void levelMedium(int p, boolean m) {
        int i=0;
        int j=0;
        int i1=0;
        int j1 = 0;
        int f=-1;
        if (m == true) {
            if(p==0){
                f=1;
            }
            else if(p==1) {
                f = 0;
            }
            boolean temp = false;


            while (temp == false) {
                i = (int) (Math.random() * b.getSize());
                j = (int) (Math.random() * b.getSize());
                if (b.getBoard()[i][j] != null) {
                    if(b.getBoard()[i][j].getValue()==f)
                        i1 = (int)(Math.random()*b.getSize());
                        j1 = (int)(Math.random()*b.getSize());
                        if( (Math.abs(i-i1)<=1) && (Math.abs(j-j1)<=1) ){
                            if(b.getBoard()[i1][j1] == null){
                                if (p == 0) {
                                    System.out.println("Ustawian O");
                                    b.getBoard()[i1][j1] = Field.O;
                                    temp = true;
                                } else if (p == 1) {
                                    System.out.println("Ustawiam X");
                                    b.getBoard()[i1][j1] = Field.X;
                                    temp = true;
                                }
                            }
                        }
                }
            }
        }
    }
}
