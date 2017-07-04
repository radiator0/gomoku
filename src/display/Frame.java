package display;

import connection.Multiplayer;
import connection.Online;
import connection.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-04-11
 */
public class Frame extends JFrame {
    Panel panel = new Panel();

    public Frame(int x){
        super("Kółko i krzyżyk");
        Online multi = new Online("7NM06KZ");
        panel = new Panel(multi);
        add(this.panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
       // Server server = new Server();


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final Timer timer = new Timer(x, new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        // panel.repaint();

                        final SwingWorker worker = new SwingWorker() {
                            @Override
                            protected Object doInBackground() throws Exception {
                                /**
                                try {
                                   // System.out.println("asdsz");

                                    // odbiera TCP pakiety na ruchy
                                   // panel.outsideMove(server.check());

                                } catch (IOException e1) {
                                    //System.out.println("ERROR");
                                    e1.printStackTrace();
                                }
                                **/


                                // pobiera z neta ruchy
                                if(multi.isMyTurn() && multi.getLastNumber()>0){
                                    panel.outsideMove(multi.getLastSpot());
                                }


                                return null;
                            }
                        };
                        worker.execute();

                    }
                });
                timer.start();
            }
        });

    }


    public Frame(){
        super("Kółko i krzyżyk");
        add(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
