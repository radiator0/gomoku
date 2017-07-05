package connection;

import game.Spot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by radiator on 2017-07-03.
 */
public class Online implements  Multiplayer{
    boolean isCreator;
    String and = "&";
    String gameId;

    public Online(){isCreator = true; createGame();}
    public Online(String gameId){isCreator = false; this.gameId = gameId;}

    private void createGame(){
        try {
            gameId = sendGet("a=create");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public boolean isMyTurn(){
        if((isCreator && getLastNumber()%2==0) || (!isCreator && getLastNumber()%2==1)){
          return true;
        }
        return false;
    }

    public void move(int x, int y){
        try {
            sendGet("gameid=" + gameId + and + "a=move" + and + "x=" + x + and + "y=" + y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getLastNumber(){
        try {
            String tmp[] = getLastMove().split(";");
            return Integer.valueOf(tmp[0]);
        }catch(NumberFormatException e){
            return 0;
        }
    }

    public Spot getLastSpot(){
        String tmp[] = getLastMove().split(";");
        Integer x = Integer.valueOf(tmp[1]);
        Integer y = Integer.valueOf(tmp[2]);
        return new Spot(x,y);
    }

    public String getLastMove(){
        String s = null;
        try {
            s = sendGet("gameid=" + gameId + and + "a=list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public String getGameId(){
        return gameId;
    }

    private String sendGet(String get) throws Exception {

        String url = "http://www.ierer.pl/TTT/game.php?" + get;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent","Mozilla/5.0");

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //System.out.println(url);
        //System.out.println(response.toString());
        return response.toString();
    }



    public static void main(String[] args) throws Exception {
        Online sp = new Online();

       // ServerPHP sp = new ServerPHP("X4WUSI8");


        // teraz robimy dla przykladowych danych X4WUSI8
       //sp.gameId = "X4WUSI8";

       // sp.move(1,2);
       // sp.move(3,5);
        sp.move(9,3);

        System.out.println(sp.getLastNumber());
      //  System.out.println(sp.getLastSpot());
        System.out.println(sp.isMyTurn());
    }
}
