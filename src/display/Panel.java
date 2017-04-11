package display;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-04-11
 */
public class Panel extends JPanel {
    Panel(){
        setPreferredSize(new Dimension(1000,500));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
