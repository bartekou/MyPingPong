import java.awt.*;
import java.util.Random;

public class Ball implements Runnable {

    private int x; //pozycja x piłki
    private int y; //pozycja y piłki
    private int xMove; //ruch piłki po x
    private int yMove; //ruch piłki po y
    private Rectangle ball; //
    public int player1score;
    public int player2score;
    private boolean trap_drawed[] = new boolean[8];

    Player player1 = new Player(10, 35, 1);
    Player player2 = new Player(670, 35, 2);
    Trap trap[] = new Trap[8];

    public Ball(int xBall, int yBall)
    {
        player1score = 0;
        player2score = 0;
        this.x = xBall;
        this.y = yBall;
        Random r = new Random();
        ballMove(r);
        ball = new Rectangle(this.x, this.y, 25,25);

        for(int i=0;i<=7;i++)
        {
            trap_drawed[i]=false;
            trap[i]= new Trap(x,y); //nadanie pozycji pułapek
        }

    }

    private void draw_trap()
    {
        for (int i=0;i<=7;i++)
        {
            if(trap_drawed[i]==false)
                trap[i].newPosition();
        }
    }

    private void ballMove(Random r) {
        int rXmove = r.nextInt(1);
        int rYmove = r.nextInt(1);
        if (rXmove == 0)
            rXmove++;
        if (rYmove == 0)
            rYmove++;
        setxMove(rXmove);
        setyMove(rYmove);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(ball.x, ball.y, 25, 25);
        g.setColor(Color.ORANGE);
        g.drawRect(8, 32, 683,559);
    }

    public void setxMove(int xMove) {
        this.xMove = xMove;
    }
    public int getxMove() { return xMove; }
    public void setyMove(int yMove) {
        this.yMove = yMove;
    }

    @Override
    public void run() {
        try{
            while(Game.isRun()){
                move();
                Thread.sleep(7);
            }

        }catch (Exception e) {
            Game.setRun(false);
        }
    }

    private void move() {
        collision();
        ball.x += xMove;
        ball.y += yMove;

        if(ball.x <= 15) {
            setxMove(+1);
            player2score++;
//            player1.setPlayerHeight(80);
//            player2.setPlayerHeight(80);
            ball.setLocation(x, y);
            for (int i=0;i<=7;i++)
            {
                trap[i].hide();
                trap_drawed[i]=false;
            }

        }
        if(ball.x >= 675) {
            setxMove(-1);
            player1score++;
//            player1.setPlayerHeight(80);
//            player2.setPlayerHeight(80);
            ball.setLocation(x, y);
            for (int i=0;i<=7;i++)
            {
                trap[i].hide();
                trap_drawed[i]=false;
            }

        }
        if(ball.y <= 25) {
            setyMove(+1);
        }
        if(ball.y >= 567) {
            setyMove(-1);
        }
    }


    private void collision() {
        if(ball.intersects(player1.player)) {
            setxMove(+2);
            draw_trap();
            for (int i=0;i<=7;i++) {
                trap_drawed[i] = true;
            }
        }

        if(ball.intersects(player2.player)) {
            setxMove(-2);
            draw_trap();
            for (int i=0;i<=7;i++) {
                trap_drawed[i] = true;
            }
        }

        for (int i=0;i<=7;i++){

            if (ball.intersects(trap[i].trap))
            {
                if (getxMove()<0) {
                    setxMove(+4);
                    trap[i].hide();
                    trap_drawed[i]=false;
                }
                else {
                    setxMove(-4);
                    trap[i].hide();
                    trap_drawed[i]=false;
                }
            }
        }
    }

}
