import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame {

    private int windowWidth = 700;
    private int windowHeight = 622;
    Dimension windowSize = new Dimension(windowWidth, windowHeight);
    private Image aImage;
    private Graphics aGraphics;
    private static boolean game = true;
    private boolean gameOver;
    private boolean key_space = true;

    static Ball b = new Ball(350,300);

    public Game(){
        this.setTitle("PingPong_by_bartekou");
        this.setSize(windowSize);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new XYZ());
    }

    public static void main(String[] args) {
        new Game();
        Thread player1 = new Thread(b.player1);
        Thread player2 = new Thread(b.player2);
        player1.start();
        player2.start();
    }

    public void paint(Graphics g) {
        aImage = createImage(getWidth(), getHeight());
        aGraphics = aImage.getGraphics();
        draw(aGraphics);
        g.drawImage(aImage, 0,0 , this);
    }

    public void draw(Graphics g){
        b.draw(g);
        b.player1.draw(g);
        b.player2.draw(g);

        for (int i=0;i<=7;i++)
        {
            b.trap[i].draw(g);
        }

        score(g);
        repaint();
    }

    private void score(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Player 1:   " + b.player1score, 40, 607);
        g.drawString("Player 2:   " + b.player2score, 585, 607);
        g.drawString("Designed by BJ in LowerSilesia :))", 250, 607);
        g.setColor(Color.WHITE);
        if(b.player1score >= 7 || b.player2score >= 7) {
            setRun(false);
            gameOver = true;
        }
        if(gameOver)
            g.drawString("Game Over!!!", 305, 250);
        g.setColor(Color.WHITE);
    }
    class XYZ extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            b.player1.keyPressed(e);
            b.player2.keyPressed(e);

            if (e.getKeyCode() == KeyEvent.VK_SPACE && key_space == true) {
                Thread ball = new Thread(b);
                ball.start();
                key_space = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            b.player1.keyReleased(e);
            b.player2.keyReleased(e);
        }
    }

    public static boolean isRun(){
        return game;
    }
    public static void setRun(boolean game){
        Game.game = game;
    }
}
