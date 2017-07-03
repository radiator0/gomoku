package connection;

/**
 * Created by radiator on 2017-07-03.
 */
public interface Multiplayer {
    /**
     * Wykonuje ruch na podane wspolrzedne
     * @param x
     * @param y
     */
    void move(int x, int y);

    /**
     * Sprawdza czy kolejny ruch musi zrobić użytkownik
     * @return
     * true - jesli tak
     * false - jeśli nie
     */
    boolean isMyTurn();
}
