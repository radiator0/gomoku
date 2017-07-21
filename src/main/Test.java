package main;

import display.Frame;
import game.Settings;

import java.io.IOException;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-04-11
 */
public class Test {



    public static void main(String[] args) {
        try {
            Settings.readFile("settings.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Frame(300);
    }
}
