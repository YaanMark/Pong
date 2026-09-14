import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    public enum Estado { MENU, JOGANDO }
    public static Estado estado = Estado.MENU;

    public static final int WIDTH = 240;
    public static final int HEIGHT = 120;
    public static final int SCALE = 3;
    public static final int PONTOS_PARA_VENCER = 10;
    public static String vencedor = null;

    public static int placarJogador = 0;
    public static int placarInimigo = 0;

    public BufferedImage layer = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public Game() {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        addKeyListener(this);
        setFocusable(true);
    }

    public static void main(String args[]) {
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.toFront();
        frame.requestFocus();
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        SoundManager.tocarMusicaFundo("/res/musica.wav", 0.8f);

        new Thread(game).start();
    }

    public void tick() {
        if (estado == Estado.MENU || vencedor != null) {
            return;
        }
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = layer.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);

        if (estado == Estado.JOGANDO) {
            player.render(g);
            enemy.render(g);
            ball.render(g);
        }

        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);

        if (estado == Estado.MENU) {
            desenhaMenu(g);
        } else {
            desenhaPlacar(g);
            if (vencedor != null) {
                desenhaVencedor(g);
            }
        }
        bs.show();
    }

    public static void iniciarPartida() {
        placarJogador = 0;
        placarInimigo = 0;
        vencedor = null;
        player = new Player(100, HEIGHT - 10);
        enemy = new Enemy(100, 0);
        ball = new Ball(100, HEIGHT / 2 - 1);
        estado = Estado.JOGANDO;
    }

    private void desenhaMenu(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 40));
        String titulo = "PONG";
        FontMetrics fmTitulo = g.getFontMetrics();
        int largTitulo = fmTitulo.stringWidth(titulo);
        g.drawString(titulo, (WIDTH * SCALE - largTitulo) / 2, (HEIGHT * SCALE) / 2 - 30);

        g.setFont(new Font("Monospaced", Font.PLAIN, 18));
        String instrucao = "Pressione ENTER para jogar";
        FontMetrics fmInstrucao = g.getFontMetrics();
        int largInstrucao = fmInstrucao.stringWidth(instrucao);
        g.drawString(instrucao, (WIDTH * SCALE - largInstrucao) / 2, (HEIGHT * SCALE) / 2 + 20);
    }

    private void desenhaPlacar(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        String texto = placarJogador + "  x  " + placarInimigo;
        FontMetrics fm = g.getFontMetrics();
        int larguraTexto = fm.stringWidth(texto);
        g.drawString(texto, (WIDTH * SCALE - larguraTexto) / 2, 30);
    }

    private void desenhaVencedor(Graphics g) {
        g.setFont(new Font("Monospaced", Font.BOLD, 30));
        String textoVencedor = vencedor + " venceu!";
        FontMetrics fmVencedor = g.getFontMetrics();
        int largVencedor = fmVencedor.stringWidth(textoVencedor);
        g.drawString(textoVencedor, (WIDTH * SCALE - largVencedor) / 2, (HEIGHT * SCALE) / 2);

        g.setFont(new Font("Monospaced", Font.PLAIN, 16));
        String instrucao = "Pressione ENTER para voltar ao menu";
        FontMetrics fmInstrucao = g.getFontMetrics();
        int largInstrucao = fmInstrucao.stringWidth(instrucao);
        g.drawString(instrucao, (WIDTH * SCALE - largInstrucao) / 2, (HEIGHT * SCALE) / 2 + 40);
    }

    @Override
    public void run() {
        while(true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (estado == Estado.MENU) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                iniciarPartida();
            }
            return;
        }

        if (vencedor != null) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                estado = Estado.MENU;
            }
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (estado != Estado.JOGANDO || player == null) return;

        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        }
    }
}
