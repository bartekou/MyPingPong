import java.awt.*;
import java.util.Random;
import java.awt.event.KeyEvent;

public class Player implements Runnable {

    private int x;
    private int y;
    private int yPlayerSpeed;
    private int identify;
    private int playerWidth=20;
    private int playerHeight=80;
    public Rectangle player;

    public Player(int xPlayer, int yPlayer, int identifyPlayer) {
        x = xPlayer;
        y = yPlayer;
        this.identify = identifyPlayer;
        player = new Rectangle(x, y, playerWidth, playerHeight);
    }

    public int getyPlayerSpeed() {
        return yPlayerSpeed;
    }

    public void setyPlayerSpeed(int yPlayerSpeed) {
        this.yPlayerSpeed = yPlayerSpeed;
    }

    @Override
    public void run() {
        try {
            while (true) {
                move();
                Thread.sleep(5);
            }
        }
        catch (Exception e) {
        }
    }

    private void move() {
        player.y += getyPlayerSpeed();
        if(player.y <= 34) {
            player.y = 34;
        }
        if(player.y >= 590-getPlayerHeight()) {
            player.y = 590-getPlayerHeight();
        }
    }

    public void draw(Graphics g) {
        switch(identify) {
            case 1:
            case 2:
                g.setColor(Color.WHITE);
                g.fillRect(player.x, player.y, playerWidth, playerHeight);
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch(identify){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W)
                    setyPlayerSpeed(-2);
                if(e.getKeyCode() == KeyEvent.VK_S)
                    setyPlayerSpeed(2);
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP)
                    setyPlayerSpeed(-2);
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    setyPlayerSpeed(2);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(identify){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W)
                    setyPlayerSpeed(0);
                if(e.getKeyCode() == KeyEvent.VK_S)
                    setyPlayerSpeed(0);
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP)
                    setyPlayerSpeed(0);
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    setyPlayerSpeed(0);
                break;
        }
    }
    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
        player = new Rectangle(player.x, player.y, playerWidth, playerHeight);
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

}
