package mimis.util;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import wiiusej.Wiimote;
import mimis.device.wiimote.WiimoteDevice;
import mimis.device.wiimote.WiimoteService;

public class Sound {
    public static final byte PCM = 64;
    public static final byte ADPCM = 0;
    public static final byte BLOCK_SIZE = 20;

    public Object object = new Object();

    public static void main(String[] args) {
        new Sound().start();
    }

    public void start() {
        WiimoteService wiimoteService = new WiimoteService();
        try {
            WiimoteDevice wiimoteDevice = new WiimoteDevice();
            Wiimote wiimote = wiimoteService.getDevice(wiimoteDevice);

            File file = new File("sound2.wav");

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            BufferedSound bufferedSound = bufferSound(audioInputStream);

            wiimote.activateSpeaker();

            wiimote.setSpeakerConfig(ADPCM, bufferedSound.getSampleRate(), 0.1);

            
            AudioFormat audioFormat = audioInputStream.getFormat();
            double sampleSizeInBytes = audioFormat.getSampleSizeInBits() / 8D;
            double samplesPerBlock = BLOCK_SIZE / sampleSizeInBytes;
            int step = (int) Math.round(1000 * samplesPerBlock / audioFormat.getSampleRate());

            playBufferedSound(wiimote, bufferedSound, step);

            object.wait();
            wiimoteService.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playBufferedSound(final Wiimote wiimote, final BufferedSound bufferedSound, int step) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int i = 0;
            public void run() {
                wiimote.streamSpeakerData(bufferedSound.getReport(i));
                if (++i > bufferedSound.numReports()) {
                    cancel();
                }                
            }            
        };
        timer.scheduleAtFixedRate(timerTask, 0, step);
        object.notifyAll();
    }

    public BufferedSound bufferSound(AudioInputStream audioInputStream) throws IOException {
        AudioFormat audioFormat = audioInputStream.getFormat();
        int size = (int) (audioInputStream.getFrameLength() * audioFormat.getFrameSize());
        byte[][] sound = new byte[size / BLOCK_SIZE + 1][BLOCK_SIZE + 1];
        for (int i = 0; i < sound.length; ++i) {
            byte[] block = new byte[BLOCK_SIZE];
            int j = 0;
            do {
                int read = audioInputStream.read(block, j, BLOCK_SIZE - j);
                if (read == -1) {
                    break;
                }
                j += read;
            } while (j < BLOCK_SIZE);
            int length = BLOCK_SIZE;
            for (j = 0; j < BLOCK_SIZE; ++j) {
                if ((i * 20 + j) > size) {
                    length = j;
                    break;
                }
                sound[i][j + 1] = (byte) (Math.random() * 0xff) ;//block[j];
            }
            sound[i][0] = (byte) (length << 3); // Todo: add later
        }
        audioInputStream.close();
        return new BufferedSound(sound, (int) audioFormat.getSampleRate(), audioFormat.getSampleSizeInBits());
    }
}
