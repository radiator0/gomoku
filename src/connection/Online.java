package connection;

import game.Settings;
import game.Spot;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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
    public Online(String gameId){isCreator = false; this.gameId = gameId; joinGame();}

    private void createGame(){
        try {
            gameId = sendGet("a=create" + and + "nick=" + Settings.NICK);

            // copy gameid to clipboard
            StringSelection stringSelection = new StringSelection(gameId);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void joinGame(){
        try {
            sendGet("a=join" + and + "nick=" + Settings.NICK + and + "gameid=" + gameId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isCreator(){
        return isCreator;
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

    public String getPlayerOne(){
        if(!isCreator()){
            return getSettings(0);
        }
        return null;
    }

    public String getPlayerTwo(){
        if(isCreator()){
            return getSettings(3);
        }
        return null;
    }

    /**
     * Pobiera string z ustawieniami
     * Nick1;MaxIloscRund;CzyWystartowal;Nick2;
     * @param id
     * @return
     */
    private String getSettings(int id){
        String s = null;
        try {
            s = sendGet("gameid=" + gameId + and + "a=settings");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String tmp[] = s.split(";");
        if(id<tmp.length){
            return tmp[id];
        }
        return null;
    }

    public void startGame(){
        try {
            sendGet("gameid=" + gameId + and + "a=start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRounds(int rounds){
        try {
            sendGet("gameid=" + gameId + and + "a=setrounds" + and + "rounds=" + rounds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMaxRounds(){
        return Integer.valueOf(getSettings(1));
    }

    public boolean hasStarted(){
        return "1".equals(getSettings(2));
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
/**
        sp.move(9,3);

        System.out.println(sp.getLastNumber());
      //  System.out.println(sp.getLastSpot());
        System.out.println(sp.isMyTurn());
 **/
/**
        System.out.println(sp.getGameId());
        while(true){
            System.out.println(sp.getPlayersNicks());
            System.out.println(sp.getPlayerTwo()  + "      +++");
            TimeUnit.SECONDS.sleep(1);
        }
**/
        String a = "ABC;CDE";
        String tmp[] = a.split(";");
        if(tmp.length>1 && tmp[1].length()>0){
            System.out.println( tmp[1]);
        }
    }
}
