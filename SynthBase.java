package ec.app.project.v02.src;

import com.jsyn.Synthesizer;
import com.jsyn.data.FloatSample;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.FixedRateMonoWriter;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.MixerMono;
import com.jsyn.util.WaveRecorder;
import com.softsynth.shared.time.TimeStamp;
import ec.Evolve;

import java.io.FileWriter;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by alexmann on 14/08/2015.
 */
public class SynthBase extends Circuit {

    public SynthBase() {

        lineOut = new LineOut();

    }

    public float[] getWaveform(Synthesizer synth, MixerMono mixer){
        FloatSample samples = new FloatSample((int) Math.floor(Evolve.lengthInS * 44100), 1);
        boolean realtime = false;
        if (realtime) {
            try {
                synth.add(lineOut);

                mixer.output.connect(0, lineOut.input, 0);
                mixer.output.connect(0, lineOut.input, 1);

                synth.start();
                lineOut.start();
                Thread.sleep((long)(Evolve.lengthInS * 1000));
                lineOut.stop();
                mixer.output.disconnect(lineOut.input);
                synth.stop();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        } else {
            try {

                FixedRateMonoWriter writer = new FixedRateMonoWriter();
                synth.add(writer);
                synth.setRealTime(false);
                mixer.output.connect(writer.input);
                writer.dataQueue.queue(samples);

                synth.start();
                writer.start();
                synth.sleepUntil(synth.getCurrentTime() + Evolve.lengthInS + 1);
                writer.stop();
                synth.stop();

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
                candidateSamples = new float[samples.getNumFrames() - 1];
                samples.read(0, candidateSamples, 0, candidateSamples.length);


        }
        return candidateSamples;
    }
    private LineOut lineOut;
    public float[] candidateSamples;
}
