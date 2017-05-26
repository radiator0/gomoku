package game;

/**
 * Author: Mateusz RokosaW
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

    /**
     * checkAll
     * Funkcja sprawdza czy spelnione sa warunki zwyciestwa
     * @param spot Punkt wokół którego sprawdzane są warunki zwycięstwa
     * @param s określa w ilu polach obok siebie muszą być takie same znaki aby zgłosić zwycięstwo
     * @param d określa w jakim kierunku sprawdzane jest zwyciestwo - pionowym, poziomym, ukośny
     */
    public Spot[] checkAll(Spot spot, int s, int d){
        Spot[] tab = new Spot[s];
        tab[0] = new Spot(spot.x,spot.y);
        int t=1;
        int z=1;
        while(t<s){
            Spot current=null;
            switch(d){
                case 0:{
                    current = new Spot(spot.x, spot.y+t);
                }break;
                case 1:{
                    current = new Spot(spot.x+t, spot.y);
                }break;
                case 2:{
                    current = new Spot(spot.x+t, spot.y+t);
                }break;
                case 3:{
                    current = new Spot(spot.x+t, spot.y-t);
                }break;
            }

            if( (checkOut(current)==false) && (table[current.x][current.y] != null) ){
                if(table[spot.x][spot.y].getValue()==table[current.x][current.y].getValue()) {
                    tab[z] = current;
                    z++;
                }
            }
            if(z==s){
                return tab;
            }
            t++;
        }
        return null;
    }

    /**
     * winner
     * Wywoluje funkcje sprawdzajaca zwyciestwo, oraz zwraca tablice zawierajaca punkty, ktore zwyciezyly
     * @param s ilosc punktow obok siebie potrzebnych aby zwyciezyc
     */
    public Spot[] winner(int s){
        Spot[] tab = new Spot[s];
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table[i].length; j++){
                if(table[i][j]!=null) {
                    if (checkAll(new Spot(i, j), s, 0) != null) {
                        tab = checkAll(new Spot(i, j), s, 0);
                        return tab;
                    }
                    if (checkAll(new Spot(i, j), s, 1) != null) {
                        tab = checkAll(new Spot(i, j), s, 1);
                        return tab;
                    }
                    if (checkAll(new Spot(i, j), s, 2) != null) {
                        tab = checkAll(new Spot(i, j), s, 2);
                        return tab;
                    }
                    if (checkAll(new Spot(i, j), s, 3) != null) {
                        tab = checkAll(new Spot(i, j), s, 3);
                        return tab;
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

    public void showWinner(int i){
        if(i==0){
            System.out.println("Zwyciezyl gracz O");
        }
        else if(i==1){
            System.out.println("Zwyciezyl gracz X");
        }
    }

    public void showSpots(Spot[] table){
        if(table!=null) {
            for (int i = 0; i < table.length; i++) {
                System.out.println(table[i].x + "," + table[i].y);
            }
        }
    }

    public void show(){
        int i=0;
        for( ; i<table.length; ){
            for(int j=0; j< table[i].length; j++){
                System.out.printf("%5d", table[i][j].getValue());
            }
            System.out.println();
            i++;
        }
    }

    public void test(){
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table[i].length; j++){
                setNull(new Spot(i,j));
            }
        }
        setX(new Spot(0,0));
        setX(new Spot(1,1));
        setX(new Spot(2,2));
    }

    public static void main(String[] args){
        Board b = new Board(3);
        b.test();
        b.show();
        b.showSpots( b.winner(3));
        //System.out.println(b.whoWon(b.winner(5)));
    }
}