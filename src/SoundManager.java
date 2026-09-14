import javax.sound.sampled.*;
import java.net.URL;

public class SoundManager {

    private static Clip musicaFundo;

    public static void tocarMusicaFundo(String caminho, float volume) {
        try {
            URL url = SoundManager.class.getResource(caminho);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            musicaFundo = AudioSystem.getClip();
            musicaFundo.open(audioIn);
            ajustarVolume(musicaFundo, volume);
            musicaFundo.loop(Clip.LOOP_CONTINUOUSLY);
            musicaFundo.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pararMusicaFundo() {
        if (musicaFundo != null && musicaFundo.isRunning()) {
            musicaFundo.stop();
        }
    }

    public static void tocarEfeito(String caminho, float volume) {
        try {
            URL url = SoundManager.class.getResource(caminho);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            ajustarVolume(clip, volume);

            clip.addLineListener(evento -> {
                if (evento.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ajustarVolume(Clip clip, float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl controle = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float minimo = controle.getMinimum();
            float maximo = controle.getMaximum();

            volume = Math.max(0.0001f, Math.min(1f, volume));
            float dB = (float) (Math.log10(volume) * 20);
            dB = Math.max(minimo, Math.min(maximo, dB));

            controle.setValue(dB);
        }
    }
}