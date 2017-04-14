package game;

/**
 * Author: Mateusz RokosaW
 * Date: 2017-04-14
 */
public class Board {
    int size;
    Field table[][];
    public Board(int size){
        this.size = size;
        table = new Field[size][size];
    }

    public void setX(Spot spot){
        table[spot.x][spot.y] = Field.X;
    }

    public void setO(Spot spot){
        table[spot.x][spot.y] = Field.O;
    }

    public void setNull(Spot spot){
        table[spot.x][spot.y] = Field.empty;
    }

    public Field[][] getBoard() { return table; }

    public int getSize() { return size; }

    public boolean checkOut(Spot spot){
        boolean temp = false;
        if( (spot.x<0) || (spot.y<0) || (spot.x>=size) || (spot.y>=size) ){
            temp = true;
        }
        return temp;
    }
    public Spot[] checkVertical(Spot spot, int s){
        Spot[] tab = new Spot[s];
        int t=1;
        int z=1;
        while(t<s){
            tab[0] = new Spot(spot.x,spot.y);
            Spot current = new Spot(spot.x, spot.y+t);
            if(checkOut(current)==false){
                if(table[spot.x][spot.y].getValue()==table[spot.x][spot.y+t].getValue()) {
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

    public Spot[] checkHorizontal(Spot spot, int s){
        Spot[] tab = new Spot[s];
        int t=1;
        int z=1;
        while(t<s){
            tab[0] = new Spot(spot.x,spot.y);
            Spot current = new Spot(spot.x+t, spot.y);
            if(checkOut(current)==false){
                if(table[spot.x][spot.y].getValue()==table[spot.x+t][spot.y].getValue()) {
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

    public Spot[] checkCrosswise(Spot spot, int s){
        Spot[] tab = new Spot[s];
        int t=1;
        int z=1;
        while(t<s){
            tab[0] = new Spot(spot.x,spot.y);
            Spot current = new Spot(spot.x+t, spot.y+t);
            if(checkOut(current)==false){
                if(table[spot.x][spot.y].getValue()==table[spot.x+t][spot.y+t].getValue()) {
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



    public Spot[] winner(int s){
        Spot[] tab = new Spot[s];
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table[i].length; j++){
                if(checkVertical(new Spot(i,j),s)!=null){
                    tab = checkCrosswise(new Spot(i,j),s);
                    return tab;
                }
                if(checkHorizontal(new Spot(i,j),s)!=null){
                    tab = checkHorizontal(new Spot(i,j),s);
                    return tab;
                }
                if(checkVertical(new Spot(i,j),s)!=null){
                    tab = checkVertical(new Spot(i,j),s);
                    return tab;
                }


            }
        }
        return null;
    }

    public void showSpots(Spot[] table){
        for(int i=0; i<table.length; i++){
            System.out.println(table[i].x +","+table[i].y);
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
                setX(new Spot(i,j));
            }
        }
    }

    public static void main(String[] args){
        Board b = new Board(3);
        b.test();
        b.show();
        b.showSpots( b.winner(3));
    }





}
