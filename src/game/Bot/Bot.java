package game.Bot;

import game.Spot;

/**
 * Created by radiator on 2017-07-10.
 */
public interface Bot {
    void step(Spot enemy, int fieldsNumber, boolean canMove);
}
