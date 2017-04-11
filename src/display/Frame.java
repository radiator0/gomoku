package display;

import javax.swing.*;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-04-11
 */
public class Frame extends JFrame {
    public Frame(){
        super("Kółko i krzyżyk");
        Panel panel = new Panel();
        add(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
