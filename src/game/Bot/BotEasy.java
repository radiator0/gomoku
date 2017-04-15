package game.Bot;

import game.Board;
import game.Field;

/**
 * Created by Mateusz on 2017-04-15.
 */
public class BotEasy {
    Board b;
    public BotEasy(Board b){
        this.b = b;
    }

    public void levelEasy(int p, boolean m) {
        if (m == true) {
            boolean temp = false;
            int i;
            int j;
            while (temp == false) {
                i = (int) (Math.random() * b.getSize());
                j = (int) (Math.random() * b.getSize());
                if (b.getBoard()[i][j] == null) {
                    if (p == 0) {
                        System.out.println("Ustawian O");
                        b.getBoard()[i][j] = Field.O;
                        temp = true;
                    } else if (p == 1) {
                        System.out.println("Ustawiam X");
                        b.getBoard()[i][j] = Field.X;
                        temp = true;
                    }
                }
            }
        }
    }
}
