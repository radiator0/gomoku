package game;

/**
 * Author: Mateusz Rokosa
 * Date: 2017-04-14
 */
public class Board {
    int size;
    Field table[][];
    Board(int size){
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

    public boolean checkOut(Spot spot){
        boolean temp = false;
        if( (spot.x<0) || (spot.y<0) || (spot.x>size) || (spot.y>size) ){
            temp = true;
        }
        return temp;
    }

    public Spot[] winner(int s){
        Spot[] tab = new Spot[s];
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table[i].length; j++){
               int t=1;
               while(t<=s){
                   int z=0;
                   if(checkOut(new Spot(i,j+t))==false){
                       if(table[i][j].getValue()==table[i][j+t].getValue()) {
                           tab[z] = new Spot(i, j + t);
                           z++;
                       }
                   }
                   if(z==s){
                       return tab;
                   }
                   t++;
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
