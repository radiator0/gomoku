package display;

import game.Field;
import game.Spot;
import sun.font.Font2D;

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
    private int fieldSize = 50;
    List<Shape> lines;
    Shape[][] shapes;
    Graphics2D g2d;
    Panel(){
        lines = new ArrayList<>();
        setPreferredSize(new Dimension(1000,500));
        addMouseListener(this);
    }

    private void init(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void genBoard(int count){
        shapes = new Shape[count][count];
        for(int i=0; i<count; i++){
            for(int j=0; j<count; j++) {
                shapes[i][j] = new Rectangle2D.Double(i*fieldSize, j*fieldSize, fieldSize, fieldSize);
            }
        }

        for(int i=0; i<=fieldSize*count; i+=fieldSize){
          lines.add(new Line2D.Double(0,i,fieldSize*count,i));
          lines.add(new Line2D.Double(i, fieldSize*count, i, 0));
        }


    }

    private void drawField(Field f, int x, int y){
        if(f==null) return;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        if(f.equals(Field.O)){
            g2d.drawString("O", x*fieldSize, (y+1)*fieldSize);
        }else if(f.equals(Field.X)){
            g2d.drawString("X", x*fieldSize, (y+1)*fieldSize);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        init(g);
        genBoard(6);

        for(int i=0; i<shapes.length; i++){
            for(int j=0; j<shapes[i].length; j++) {
                g2d.setColor(Color.BLACK);
                g2d.fill(shapes[i][j]);
            }
        }

        for(Shape s : lines) {
            g2d.setColor(Color.RED);
            g2d.draw(s);
        }

        Field tab[][] = new Field[6][6];
        tab[0][1] = Field.O;
        tab[3][4] = Field.X;
        for(int i=0; i<tab.length; i++){
            for(int j=0; j<tab[i].length; j++){
                drawField(tab[i][j], i,j);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i=0; i<shapes.length; i++){
            for(int j=0; j<shapes[i].length; j++) {
                if(shapes[i][j].contains(e.getPoint())){
                    System.out.println(i + "" + j);
                    // jakas metoda wywolujaca akcje dla x y
                }
            }
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
