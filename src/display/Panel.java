package display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.ArrayList;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-04-11
 */
public class Panel extends JPanel implements MouseListener{
    List<Shape> shapes;
    Panel(){
        shapes = new ArrayList<>();
        setPreferredSize(new Dimension(1000,500));
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

       // Point2D x = new Point2D.Double(0,0);
        shapes.add(new Rectangle2D.Double(0,0,50,50));
        for(int i=0; i<200; i+=50){
            shapes.add(new Line2D.Double(100,100+i,400,100+i));
            shapes.add(new Line2D.Double(100+i,400,100+i,100));
        }


        for(Shape s : shapes){
            g2d.draw(s);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getPoint());
       if(shapes.get(0).contains(e.getPoint())){
           System.out.println("BINGO");
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
