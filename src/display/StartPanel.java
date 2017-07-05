package display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by radiator on 2017-07-04.
 */
public class StartPanel extends JPanel implements MouseListener {
    private Graphics2D g2d;

    StartPanel(){
        setPreferredSize(new Dimension(500,400));
        addMouseListener(this);
        setBackground(new Color(82,81,93));
    }


    private void init(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void paintComponent(Graphics g){
        init(g);

        drawLogo();
        drawMenu();
    }

    private void drawMenu(){
        drawMainBar("      SINGLEPLAYER",0);
        drawMainBar("MULTIPLAYER ONLINE", 1);

        drawDivider(40,195,3);
        drawDivider(40,295,2);


        g2d.setFont(new Font("Calibri", Font.BOLD, 25));
        g2d.setColor(Color.WHITE);
        g2d.drawString("MEDIUM", 202,228);
        g2d.drawString("CREATE", 102,328);
        g2d.drawString("JOIN", 332,328);
    }



    private void drawMainBar(String text, int order){
        int space =  order * 100;
        g2d.setColor(new Color(44,42,60));
        g2d.fillRect(40,160+space, 420,35);

        g2d.drawRect(40,160+space, 420,85);
        g2d.drawRect(41,160+space, 418,84);



        g2d.setFont(new Font("Calibri", Font.BOLD, 30));
        g2d.setColor(new Color(200,4,82));
        g2d.drawString(text, 115,187+space);

    }

    private void drawDivider(int x, int y, int count){
        g2d.setColor(new Color(44,42,60));
        for(int i=1; i<count; i++){
          g2d.fillRect(x+420/count*i, y, 2,50);
        }


    }


    private void drawLogo(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("gomoku_logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.drawImage(image,0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)){

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
