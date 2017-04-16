package connection;

import game.Spot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-04-16
 */
public class Server {
    String clientSentence;
    String capitalizedSentence;
    ServerSocket welcomeSocket = null;
    Socket connectionSocket = null;
    boolean start = false;

    public Server(){
        start();
    }

    private void start() {
        try {
            welcomeSocket = new ServerSocket(6789);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Spot check() throws IOException {
        //System.out.println("uruchomienie check()");
        connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        clientSentence = inFromClient.readLine();
        if(clientSentence.equals("start") && !start){
            System.out.println("Połączono");
            start = true;
        }else{
            // System.out.println(clientSentence);
        }

        if(start){
            String[] str = clientSentence.split(";");
            if(str.length==2){
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                Spot s = new Spot(x,y);
                return s;
            }
        }
        return null;
    }

}


