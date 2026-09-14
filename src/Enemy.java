import java.awt.*;

public class Enemy {

    public double x, y;
    public int width, height;
    public double speed = 1.5;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 10;
    }

    public void tick() {
        double alvo = Game.ball.x - width / 2.0;

        if (x < alvo) {
            x += speed;
            if (x > alvo) x = alvo;
        } else if (x > alvo) {
            x -= speed;
            if (x < alvo) x = alvo;
        }

        if (x + width > Game.WIDTH) {
            x = Game.WIDTH - width;
        } else if (x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x,(int) y, width, height);
    }

}
