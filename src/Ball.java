import java.awt.*;
import java.util.Random;

public class Ball {

    public double x, y;
    public int width, height;

    public double dx, dy;
    public double speed = 1.6;

    private static final double MIN_DY = 0.35;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 4;
        this.height = 4;
        double dxRaw = new Random().nextGaussian();
        double dyRaw = new Random().nextGaussian();
        double magnitude = Math.sqrt(dxRaw * dxRaw + dyRaw * dyRaw);

        this.dx = dxRaw / magnitude;
        this.dy = dyRaw / magnitude;
    }

    public void tick() {

        if(x + (dx * speed) + width >= Game.WIDTH) {
            dx *= -1;
        }else if(x + (dx * speed) + width < 0) {
            dx *= -1;
        }
        x += dx * speed;
        y += dy * speed;

        if(y >= Game.HEIGHT) {
            Game.placarInimigo++;
            new Game();
            System.out.println(Game.placarInimigo);
            return;
        }else if(y < 0) {
            Game.placarJogador++;
            new Game();
        }

        Rectangle bounds = new Rectangle((int) x, (int) y, width, height);

        Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
        Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width, Game.enemy.height);

        if(bounds.intersects(boundsPlayer)){
            dy *= -1;
            y = Game.player.y - height;

            double novoDx = calcularAnguloSaida(x, Game.player.x, Game.player.width);
            double[] direcao = calcularNovaDirecao(novoDx, dy);
            dx = direcao[0];
            dy = direcao[1];
        }else if(bounds.intersects(boundsEnemy)){
            dy *= -1;
            y = Game.enemy.y + Game.enemy.height;

            double novoDx = calcularAnguloSaida(x, (int) Game.enemy.x, Game.enemy.width);
            double[] direcao = calcularNovaDirecao(novoDx, dy);
            dx = direcao[0];
            dy = direcao[1];
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)x,(int) y, width, height);
    }

    private double calcularAnguloSaida(double ballX, int paddleX, int paddleWidth) {
        double centroRaquete = paddleX + paddleWidth / 2.0;
        double centroBola = ballX + width / 2.0;
        double distancia = centroBola - centroRaquete;

        double fator = distancia / (paddleWidth / 2.0);

        fator = Math.max(-1, Math.min(1, fator));

        return fator;
    }

    private double[] calcularNovaDirecao(double dxNovo, double dyAtual) {
        double sinalDy = Math.signum(dyAtual);
        double dyNovo = sinalDy * Math.sqrt(1 - dxNovo * dxNovo);

        if (Math.abs(dyNovo) < MIN_DY) {
            dyNovo = sinalDy * MIN_DY;
            double sinalDx = Math.signum(dxNovo);
            dxNovo = sinalDx * Math.sqrt(1 - dyNovo * dyNovo);
        }

        return new double[]{dxNovo, dyNovo};
    }

}
