package display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                final Timer timer = new Timer(50, new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        panel.repaint();
                        panel.revalidate();

                    }

                });
                timer.start();
            }
        });
    }
}
