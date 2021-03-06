package display;


import connection.Online;
import game.*;
import game.Bot.Bot;
import game.Bot.BotHard;
import game.Bot.BotMedium;
import game.Bot.BotEasy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.GlyphVector;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.ArrayList;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-04-11
 */
public class GamePanel extends JPanel implements MouseListener{
    private int fieldSize = 45;
    private List<Shape> lines;
    private Shape[][] shapes;
    private Shape backButton;
    private Graphics2D g2d;
    private int boardSize = 15;
    private int winSize = 5;
    private Game game = new Game(boardSize);
    private Board board = game.getBoard();
    private Bot bot = null;
    private Online multi = null;
    private Frame frame;

    GamePanel(Frame frame){
        this.frame = frame;
        lines = new ArrayList<>();
        setPreferredSize(new Dimension(boardSize*fieldSize+150,boardSize*fieldSize));
        System.out.println(boardSize*fieldSize+150);
        addMouseListener(this);
        setBackground(Color.black);
    }

    GamePanel(Frame f, Online multi){
        this(f);
        this.multi = multi;
    }

    GamePanel(Frame f, String botLevel){
        this(f);
        if(botLevel.equals("easy")){
            bot = new BotEasy(board);
        }else if(botLevel.equals("medium")){
            bot = new BotMedium(board);
        }else if(botLevel.equals("hard")){
            bot = new BotHard(board);
        }
        game.setPlayerOne(Settings.NICK);
        game.setPlayerTwo(Settings.BOT_NICK);
    }

    public Game getGame(){
        return game;
    }

    public void opponentMove(){
        if(multi.isMyTurn() && multi.getLastNumber()>0 && multi.getLastNumber() == game.getMovesCount()+1){
            outsideMove(multi.getLastSpot());
            game.incrementMovesCount();
        }
    }

    public void outsideMove(Spot s){
        if(s!=null){
            board.setO(s);
            checkIsWin();
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
        g2d.setColor(new Color(72,187,32));//g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Calibri", Font.PLAIN, 55));
        if(f.equals(Field.O)){
            g2d.drawString("O", x*fieldSize+fieldSize/7 - 1, (y+1)*fieldSize-fieldSize/6 + 2);
        }else if(f.equals(Field.X)){
            g2d.setColor(new Color(32,187,174));
            g2d.drawString("X", x*fieldSize+fieldSize/4 - 2, (y+1)*fieldSize-fieldSize/6 + 2);
        }
    }

    private void drawRightPanel(){
        drawWhoTurn();

        g2d.setColor(new Color(32,187,174));
        g2d.fillOval(701,54, 91,91);

        g2d.setColor(new Color(72,187,32));
        g2d.fillOval(701,503, 91,91);



        g2d.setColor(Color.white);
        g2d.setFont(new Font("Calibri", Font.PLAIN, 24));
        g2d.drawString(game.getPlayerOne(),702,168);
        g2d.drawString(game.getPlayerTwo(),702,494);

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Calibri", Font.PLAIN, 100));
        g2d.drawString(Integer.toString(game.getPlayerOneScore()),720,270);
        g2d.drawString(":",730,350);
        g2d.drawString(Integer.toString(game.getPlayerTwoScore()),720,440);

        g2d.setFont(new Font("Calibri", Font.PLAIN, 24));
        g2d.drawString("Round:"+game.getRound(), 710, 625);
        g2d.drawString("Max round:"+game.getMaxRound(),683,655);

    }

    private void drawBackButton(){
        g2d.setColor(Color.white);

        GlyphVector vect = new Font("Calibri", Font.PLAIN, 37).createGlyphVector(g2d.getFontRenderContext(), "<<");
        backButton = vect.getOutline(730, 30);
        g2d.fill(backButton);
        g2d.draw(backButton);
    }

    private void drawWhoTurn(){
        if(multi != null){
            String s = "";
            if(multi.isMyTurn()){
                s = "     Your turn";
            }else{
                s = "Opponent turn";
            }
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Calibri", Font.PLAIN, 20));
            g2d.drawString(s,685,43);
        }
    }

    @Override
    protected void paintComponent(Graphics g){

        init(g);
        genBoard(boardSize);

        for(int i=0; i<shapes.length; i++){
            for(int j=0; j<shapes[i].length; j++) {
                g2d.setColor(new Color(82,81,93));
                g2d.fill(shapes[i][j]);
            }
        }

        g2d.setStroke(new BasicStroke(2f));
        for(Shape s : lines) {
            g2d.setColor(Color.black);
            g2d.draw(s);
        }

        Field tab[][] = board.getBoard();
        for(int i=0; i<tab.length; i++){
            for(int j=0; j<tab[i].length; j++){
                drawField(tab[i][j], i,j);
            }
        }

        drawRightPanel();
        drawBackButton();
    }


    private void checkIsWin(){
        if(!game.isEnd()) {
            if (board.winner(winSize) != null) {
                drawWinLine(board.winner(winSize)[0], board.winner(winSize)[4]);
                if (Field.X.getValue() == board.whoWon(board.winner(winSize))) {
                    game.incrementOneScore();
                } else {
                    game.incrementTwoScore();
                }
                if (!game.isLastRound()) {
                    board.clear();
                    game.nextRound();
                    lines.remove(lines.size() - 1);
                } else {
                    game.setEnd();
                }
            } else if (board.isFull()) {
                if (!game.isLastRound()) {
                    game.nextRound();
                    board.clear();
                }
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(backButton.contains(e.getPoint())){
            frame.backToStartpanel(this);
        }

        for(int i=0; i<shapes.length; i++){
            for(int j=0; j<shapes[i].length; j++) {
                if(shapes[i][j].contains(e.getPoint())){
                    this.repaint();

                    if(SwingUtilities.isLeftMouseButton(e)) {
                        if(board.winner(winSize)== null ) {
                            if (multi != null) {
                                if (multi.isMyTurn()) {
                                    if (board.setX(new Spot(i, j))) {
                                        game.incrementMovesCount();
                                        multi.move(i, j);
                                    }
                                }
                            } else {
                                if(board.setX(new Spot(i, j))){
                                    bot.step(new Spot(i, j), winSize, true);
                                }
                            }
                        }
                        checkIsWin();
                    }
                }
            }
        }
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
