package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by radiator on 2017-07-13.
 */
public class Settings {

    public static String NICK = "MojNick";
    public static String BOT_NICK = "BotJanusz";
    public static String DEFAULT_NICK = "Player";
    public static int DEFAULT_MAX_ROUND = 10;


    /// POZNIEJ MOZNA TO WCZYTYWAC Z PLIKU np
    public static void readFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        NICK = line.split(":")[1];

        line = bufferedReader.readLine();
        BOT_NICK = line.split(":")[1];

        line = bufferedReader.readLine();
        DEFAULT_NICK = line.split(":")[1];

        line = bufferedReader.readLine();
        DEFAULT_MAX_ROUND = Integer.valueOf(line.split(":")[1]);

        bufferedReader.close();
    }

    public static void main(String[] args) {
        new Settings();
    }
}
