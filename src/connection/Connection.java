package connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by radiator on 2017-04-15.
 */
public class Connection {
    /**
    private void server() throws IOException {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = null;
        boolean start = false;
        try {
            welcomeSocket = new ServerSocket(6789);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
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
                    System.out.println(x + " " + y);
                }
            }
            //  capitalizedSentence = clientSentence.toUpperCase() + '\n';
            //  outToClient.writeBytes(capitalizedSentence);
        }

    }

    private void client() throws IOException {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
    }
    public static void main(String[] args) throws IOException {
        Connection c = new Connection();
        c.server();
        //c.client();

    }
**/
}