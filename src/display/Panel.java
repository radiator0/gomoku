package display;

import connection.Online;
import game.Board;
import game.Bot.BotHard;
import game.Field;
import game.Spot;
import game.Bot.BotEasy;
import game.Bot.BotMedium;

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
    private List<Shape> lines;
    private Shape[][] shapes;
    private Graphics2D g2d;
    private int count = 15;
    private Board board = new Board(count);
    private BotEasy easy = new BotEasy(board);
    private BotMedium medium = new BotMedium(board);
    private BotHard hard = new BotHard(board);
    private Online multi = null;

    Panel(){
        lines = new ArrayList<>();
        setPreferredSize(new Dimension(count*fieldSize,count*fieldSize));
        addMouseListener(this);
    }

    public int getCount(){
        return count;
    }

    Panel(Online multi){
        this();
        this.multi = multi;
    }

    public void outsideMove(Spot s){
        if(s!=null){
            board.setO(s);
            this.repaint();
        }
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

    private void drawWinLine(Spot a, Spot b){
        Point2D A = new Point2D.Double(a.getX()*fieldSize+25, a.getY()*fieldSize+25);
        Point2D B = new Point2D.Double(b.getX()*fieldSize+25, b.getY()*fieldSize+25);
        Line2D line = new Line2D.Double(A,B);
        lines.add(line);
    }

    private void drawField(Field f, int x, int y){
        if(f==null) return;
        g2d.setColor(new Color(200,4,82));//g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Calibri", Font.PLAIN, 55));
        if(f.equals(Field.O)){
            g2d.drawString("O", x*fieldSize+fieldSize/7, (y+1)*fieldSize-fieldSize/6);
        }else if(f.equals(Field.X)){
            g2d.drawString("X", x*fieldSize+fieldSize/4-1, (y+1)*fieldSize-fieldSize/6);
        }
    }

    @Override
    protected void paintComponent(Graphics g){

        init(g);
        genBoard(count);

        for(int i=0; i<shapes.length; i++){
            for(int j=0; j<shapes[i].length; j++) {

               // GradientPaint gp = new GradientPaint(25, 25, Color.DARK_GRAY, 50, 50, Color.BLACK, true);
                //g2d.setPaint(gp);
                g2d.setColor(new Color(82,81,93));
                g2d.fill(shapes[i][j]);
            }
        }

        g2d.setStroke(new BasicStroke(2f));
        for(Shape s : lines) {
            g2d.setColor(Color.WHITE);
            g2d.draw(s);
        }

        Field tab[][] = board.getBoard();
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
                    this.repaint();

                    // jesli lewym klik
                    if(SwingUtilities.isLeftMouseButton(e)){
                       //System.out.println("KLIIIIK");
                        if(multi != null ){
                            if(multi.isMyTurn()){
                                if(board.setX(new Spot(i,j))){
                                    multi.move(i,j);
                                }
                            }
                        }

                       // boolean temp = board.setX(new Spot(i,j));
                        //easy.levelEasy(0, temp);
                        //medium.levelMedium(1,temp);
                      //  board.showSpots( board.winner(5));
                        if( board.winner(5)!= null)
                            drawWinLine(board.winner(5)[0], board.winner(5)[4]);
                       // System.out.println("Lewy");
                    }else{
                        boolean temp = board.setO(new Spot(i,j));
                        hard.levelHard(new Spot(i,j),5, true);
                        //easy.levelEasy(1, temp);
                        //medium.levelMedium(0,temp);
                       // board.showSpots( board.winner(5));
                       // System.out.println("Prawy");
                        if( board.winner(5)!= null)
                            drawWinLine(board.winner(5)[0], board.winner(5)[4]);
                    }

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
