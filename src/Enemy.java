import java.awt.*;
import java.util.Random;

public class Enemy {

    public double x, y;
    public int width, height;
    public double speed = 1.1;

    private double alvo;
    private int contadorReacao = 0;
    private static final int TEMPO_REACAO = 12;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
        this.alvo = x;
    }

    public void tick() {
        contadorReacao++;
        if (contadorReacao >= TEMPO_REACAO) {
            contadorReacao = 0;

            double erro = (new Random().nextDouble() - 0.5) * width;
            alvo = Game.ball.x - width / 2.0 + erro;
        }

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
        g.fillRect((int) x, (int) y, width, height);
    }
}