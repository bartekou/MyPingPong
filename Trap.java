import java.awt.*;
import java.util.Random;

public class Trap {
    private int x;
    private int y;
    public Rectangle trap;

    int randomWithRange(int min, int max)
    {
        int range = (max-min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public Trap(int x, int y)
    {
        trap = new Rectangle(this.x, this.y, 1,55);
    }

    public void newPosition()
    {
        x = randomWithRange(150, 550);
        y = randomWithRange(40, 500);
        trap = new Rectangle(x, y, 1,55);
    }

    public void hide()
    {
        x = 1000;
        y = 1000;
        trap = new Rectangle(x, y, 1,55);
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, 4, 55);
    } //rysowanie pulapki

}
