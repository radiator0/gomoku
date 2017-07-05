package game;

/**
 * Author: Mateusz Rokosa
 * Date: 2017-04-14
 */

public class Board {
    int size;
    public Field table[][];

    public Board(int size){
        this.size = size;
        table = new Field[size][size];
    }

    public boolean setX(Spot spot){
        if(table[spot.x][spot.y]==null) {
            table[spot.x][spot.y] = Field.X;
            return true;
        }
        return false;
    }

    public boolean setO(Spot spot){
        if(table[spot.x][spot.y]==null) {
            table[spot.x][spot.y] = Field.O;
            return true;
        }
        return false;
    }

    public void setNull(Spot spot){
        table[spot.x][spot.y] = Field.empty;
    }

    public Field[][] getBoard() { return table; }

    public int getSize() { return size; }

    /**
     * Funkcja checkOut sprawdza czy punkt(Spot) nie leży poza tablicą gry
     */
    public boolean checkOut(Spot spot){
        boolean temp = false;
        if( (spot.x<0) || (spot.y<0) || (spot.x>=size) || (spot.y>=size) ){
            temp = true;
        }
        return temp;
    }

    public boolean isFull(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(table[i][j] == null){
                     return false;
                }
            }
        }
        return true;
    }

    /**
     * checkAll
     * Funkcja sprawdza czy spelnione sa warunki zwyciestwa
     * @param spot Punkt wokół którego sprawdzane są warunki zwycięstwa
     * @param fieldsNumber określa w ilu polach obok siebie muszą być takie same znaki aby zgłosić zwycięstwo
     * @param variant określa w jakim kierunku sprawdzane jest zwyciestwo - pionowym, poziomym, ukośny
     */
    public Spot[] checkAll(Spot spot, int fieldsNumber, int variant){
        Spot[] tab = new Spot[fieldsNumber];
        tab[0] = new Spot(spot.x,spot.y);
        int distanceFromSpot=1;
        int tempTableCount=1;
        while(distanceFromSpot < fieldsNumber){
            Spot current=null;
            switch(variant){
                case 0:{
                    current = new Spot(spot.x, spot.y+distanceFromSpot);
                }break;
                case 1:{
                    current = new Spot(spot.x+distanceFromSpot, spot.y);
                }break;
                case 2:{
                    current = new Spot(spot.x+distanceFromSpot, spot.y+distanceFromSpot);
                }break;
                case 3:{
                    current = new Spot(spot.x+distanceFromSpot, spot.y-distanceFromSpot);
                }break;
            }

            if( (checkOut(current)==false) && (table[current.x][current.y] != null) ){
                if(table[spot.x][spot.y].getValue()==table[current.x][current.y].getValue()) {
                    tab[tempTableCount] = current;
                    tempTableCount++;
                }
            }
            if(tempTableCount == fieldsNumber){
                return tab;
            }
            distanceFromSpot++;
        }
        return null;
    }

    /**
     * winner
     * Wywoluje funkcje sprawdzajaca zwyciestwo, oraz zwraca tablice zawierajaca punkty, ktore zwyciezyly
     * @param fieldsNumber ilosc punktow obok siebie potrzebnych aby zwyciezyc
     */
    public Spot[] winner(int fieldsNumber){
        Spot[] tab = new Spot[fieldsNumber];
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table[i].length; j++){
                if(table[i][j]!=null) {
                    for(int k=0; k<4; k++){
                        if (checkAll(new Spot(i, j), fieldsNumber, k) != null) {
                            tab = checkAll(new Spot(i, j), fieldsNumber, k);
                            return tab;
                        }
                    }
                }
            }
        }
        return null;
    }

    public int whoWon(Spot[] tab){
        if(tab != null) {
            int x = tab[0].x;
            int y = tab[0].y;
            return table[x][y].getValue();
        }
        return -1;
    }
}