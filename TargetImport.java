package ec.app.project.v02.src;

import com.jsyn.data.FloatSample;
import com.jsyn.util.SampleLoader;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

/**
 * Created by alexmann on 06/07/2015.
 */
public class TargetImport {


    public static float getSoundLength(File targetFile) {

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(targetFile);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioFormat format = audioInputStream.getFormat();
        long audioFileLength = targetFile.length();
        int frameSize = format.getFrameSize();
        float frameRate = format.getFrameRate();
        float lengthInS = (audioFileLength  / (frameSize * frameRate));

        return lengthInS;
    }

    public static float[] getTargetSamples(File targetFile) {
        try {
//            File file = new File("440Hz-SAW.wav");
            FloatSample fileSamples = SampleLoader.loadFloatSample(targetFile);
            targetSamples = new float[fileSamples.getNumFrames()];
            fileSamples.read(targetSamples);


            FileWriter write = new FileWriter("targetOutput.txt", false);
            PrintWriter printLine = new PrintWriter(write);
            for (int i = 0; i < targetSamples.length; i++) {
                printLine.println(targetSamples[i]);
            }
            printLine.close();


        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return targetSamples;

    }

    private static float[] targetSamples = null;
    private static short[] candidateSamples = null;

}
