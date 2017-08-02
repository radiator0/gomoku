package display;

import connection.Online;
import game.Game;
import game.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by radiator on 2017-07-11.
 */
public class LobbyPanel extends JPanel implements MouseListener {

    private Frame frame;

    private Graphics2D g2d;
    private int rounds = Settings.DEFAULT_MAX_ROUND;
    private Shape minusButton;
    private Shape plusButton;
    private Shape mainButton;
    private Shape backButton;

    private Online multi;
    private boolean isCreator;
    private boolean isOpponentOnline = false;



    private String playerOne = Settings.DEFAULT_NICK;
    private String playerTwo = Settings.DEFAULT_NICK;
    public String gameKey = "A4RT54Z";

    LobbyPanel(Frame frame, boolean isCreator){
        setPreferredSize(new Dimension(490,375));
        addMouseListener(this);
        setBackground(new Color(82,81,93));

        this.frame = frame;
        this.isCreator = isCreator;

        if(isCreator) {
            playerOne = Settings.NICK;
            multi = new Online();
            gameKey = multi.getGameId();
            multi.setRounds(rounds);
        }else{
            playerTwo = Settings.NICK;
            gameKey = getGameIdFromUser();
            if(gameKey != null)
                multi = new Online(gameKey);
        }
    }


    private String getGameIdFromUser(){
        String input = "";
        while(input.length()!=7){
            input = JOptionPane.showInputDialog(null ,"ENTER GAME ID:");
            if(input == null) return null;
        }
        return input;
    }

    private void init(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void paintComponent(Graphics g){
        init(g);
        drawCanvas();
        drawTitle();
        drawAvatars();
        if(isCreator){drawStatus();}
        drawNicknames();
        if(!isCreator){getMaxRounds();}
        drawRounds();
        drawGameKey();
        drawMainButtion();
        if(!isCreator && multi.hasStarted()){  initGame(); }
        drawBackButton();
    }



    private void getMaxRounds(){
        rounds = multi.getMaxRounds();
    }

    private void drawTitle(){
        g2d.setColor(new Color(175,4,72));
        g2d.setFont(new Font("Calibri", Font.PLAIN, 60));
        g2d.drawString("LOBBY",172,55 );
    }

    private void drawStatus(){
        if(isOpponentOnline){
            g2d.setColor(new Color(101,252,0));
        }else{
            g2d.setColor(new Color(40,100,0));
        }
        g2d.fillOval(390,108, 26,26);

    }

    private void drawCanvas(){
        g2d.setColor(Color.black);
        g2d.fillRect(0,0,510,70);
        g2d.fillRect(0,310,510,70);
    }

    private void drawAvatars(){
        g2d.setColor(new Color(184,255,247));
        g2d.fillOval(70,110, 105,105);
        g2d.fillOval(317,110, 105,105);

        g2d.setColor(new Color(49,49,56));
    }

    private void drawNicknames(){
        String nick = multi.getPlayerTwo();
        if(nick != null) {
            playerTwo = nick;
            isOpponentOnline = true;
        }else if(multi.isCreator()){
            playerTwo = Settings.DEFAULT_NICK;
            isOpponentOnline = false;
        }
        nick = multi.getPlayerOne();
        if(nick != null) playerOne = nick;

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Calibri", Font.PLAIN, 20));
        g2d.drawString(playerOne, 85,242);
        g2d.drawString(playerTwo, 340,242);
    }

    private void drawRounds(){
        if(isCreator) {
            g2d.setColor(new Color(45, 43, 61));

            minusButton = new Ellipse2D.Double(205, 190, 24, 24);
            plusButton = new Ellipse2D.Double(266, 190, 24, 24);
            g2d.fill(minusButton);
            g2d.fill(plusButton);
            g2d.draw(minusButton);
            g2d.draw(plusButton);

            g2d.setColor(Color.white);

            g2d.drawString("-", 214, 207);
            g2d.drawString("+", 273, 208);
        }

        g2d.setColor(Color.white);

        g2d.setFont(new Font("Calibri", Font.BOLD, 20));
        g2d.drawString("Rounds", 217, 186);

        g2d.setFont(new Font("Calibri", Font.PLAIN, 20));
        g2d.drawString(String.format("%2d",rounds), 235, 208);



    }

    private void drawGameKey(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource("/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        g2d.setColor(new Color(82,81,93));
        g2d.fillRect(170,251,145,40);

        g2d.drawImage(image,174,252,null);

        g2d.setFont(new Font("Calibri", Font.PLAIN, 25));
        g2d.setColor(Color.white);
        g2d.drawString(gameKey, 202,280);
    }

    private void drawMainButtion(){
        if(isOpponentOnline){
            g2d.setColor(new Color(175,4,72));
        }else{
            g2d.setColor(new Color( 76,2,31));
        }

        mainButton = new Rectangle2D.Double(140,310,210,40);

        if(isCreator){
            g2d.fill(mainButton);
            g2d.draw(mainButton);
            g2d.setFont(new Font("Calibri", Font.PLAIN, 35));
            g2d.setColor(Color.white);
            g2d.drawString("START", 202,340);
        }


    }

    private void drawBackButton(){
        g2d.setColor(Color.white);

        GlyphVector vect = new Font("Calibri", Font.PLAIN, 80).createGlyphVector(g2d.getFontRenderContext(), "<<");
        backButton = vect.getOutline(40, 58);
        g2d.fill(backButton);
        g2d.draw(backButton);
    }

    private void initGame(){
        frame.panel = new GamePanel(frame,multi);
        Game game = frame.panel.getGame();
        game.setMaxRound(rounds);
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);

        if(isCreator)multi.startGame();
        frame.startOnline();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(backButton.contains(e.getPoint())){
            frame.backToStartpanel(this);
        }
        if(plusButton!= null && minusButton != null) {
            if (plusButton.contains(e.getPoint()) && rounds < 25) {
                rounds++;
                multi.setRounds(rounds);
                this.repaint();
            } else if (minusButton.contains(e.getPoint()) && rounds > 1) {
                rounds--;
                multi.setRounds(rounds);
                this.repaint();
            }
        }
        if(mainButton.contains(e.getPoint())){
            if(multi.isCreator() && isOpponentOnline){
                initGame();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
