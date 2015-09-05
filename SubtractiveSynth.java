package ec.app.project.v02.src;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.*;

/**
 * Created by alexmann on 14/08/2015.
 */
public class SubtractiveSynth extends SynthBase {

    public SubtractiveSynth () {

        synth = JSyn.createSynthesizer();
        this.add(mixer = new MixerMono(1));
        this.add(preMixer = new MixerMono(3));
        this.add(sawOsc1 = new SawtoothOscillator());
        this.add(sawOsc2 = new SawtoothOscillator());
        this.add(sawOsc3 = new SawtoothOscillator());
        this.add(squOsc1 = new SquareOscillator());
        this.add(squOsc2 = new SquareOscillator());
        this.add(squOsc3 = new SquareOscillator());
        this.add(triOsc1 = new TriangleOscillator());
        this.add(triOsc2 = new TriangleOscillator());
        this.add(triOsc3 = new TriangleOscillator());
        this.add(filter4P = new FilterFourPoles());
        this.add(filterLP = new FilterLowPass());


    }

    /**
     *
     * osc2On, osc3On & filterType are effectively boolean values, with min & max genes are 0 and 1 respectively
     * (In the case of filterType, 0 signifies a simple LowPass filter, and 1 represents a Four Poles Filter)
     * osc1shape, osc2shape and os3shape are similar, but their genes range from 0 to 2 (.
     * os2/osc3relative octave are again confined to integers, but span -2 to 2, and represent the relative octaves of
     * oscillators 2 and 3 compared to the main frequency of oscillator 1
     * All of the above arguments' respective genes are confined to an integer-reset mutation
     *
     *
     *
     * @param frequency main frequency of the tone (and oscillator 1)
     * @param osc2On 'boolean' stating whether to include oscillator 2 in synthesis (either 0 or 1)
     * @param osc3On 'boolean' stating whether to include oscillator 3 in synthesis (either 0 or 1)
     * @param filterType 'boolean' determining whether a lowpass (0) or four poles (1) filter is used
     * @param osc1Shape integer value determining waveshape of osc1 0 = Sawtooth, 1 = Square, 2 = Triangle)
     * @param osc2Shape integer value determining waveshape of osc2 (values as above)
     * @param osc3Shape integer value determining waveshape of osc3 (values as above)
     * @param osc2RelativeOctave integer value determining the relative octave of osc2 compared to osc 1 (-2 <= n <= 2)
     * @param osc3RelativeOctave integer value determining the relative octave of osc2 compared to osc 1 (-2 <= n <= 2)
     *
     *                           Above arguments' respective genes are confined to integer-reset mutations.
     *                           Arguments below are not.
     *
     * @param filterCutoff Cutoff frequency for the filter
     * @param filterQ Q value for the filter
     * @param osc1Amp Amplitude of osc 1
     * @param osc2Amp Amplitude of osc 2
     * @param osc3Amp Amplitude of osc 3
     */
    public float[] getCandidateSamples(double frequency,
                                       double osc2On, double osc3On, double filterType,
                                       double osc1Shape, double osc2Shape, double osc3Shape,
                                       double osc2RelativeOctave, double osc3RelativeOctave,
                                       double filterCutoff, double filterQ,
                                       double osc1Amp, double osc2Amp, double osc3Amp) {

        sawOsc1.output.disconnectAll();
        sawOsc2.output.disconnectAll();
        sawOsc3.output.disconnectAll();
        squOsc1.output.disconnectAll();
        squOsc2.output.disconnectAll();
        squOsc3.output.disconnectAll();
        triOsc1.output.disconnectAll();
        triOsc2.output.disconnectAll();
        triOsc3.output.disconnectAll();
        preMixer.output.disconnectAll();
        filter4P.output.disconnectAll();
        filterLP.output.disconnectAll();
        mixer.output.disconnectAll();

        boolean useOsc2 = (osc2On == 1 ? true : false);
        boolean useOsc3 = (osc3On == 1 ? true : false);

        boolean filterFourPoles = (filterType == 1 ? true : false);

        int numberOfSynths = (int)(1+osc2On+osc3On);
        if (numberOfSynths == 3) {
            osc1Amp /= 3;
            osc2Amp /= 3;
            osc3Amp /= 3;
        }
        else if (numberOfSynths == 2) {
            osc1Amp /= 2;
            if (useOsc2)
                osc2Amp /= 2;
            else
                osc3Amp /= 2;
        }




        osc1Waveshape = (int) osc1Shape;
        osc2Waveshape = (int) osc2Shape;
        osc3Waveshape = (int) osc3Shape;
        osc2Octave = (int) osc2RelativeOctave;
        osc3Octave = (int) osc3RelativeOctave;



        switch (osc1Waveshape) {
            case 0:
                sawOsc1.output.connect(0, preMixer.input, 0);
                sawOsc1.frequency.set(frequency);
                sawOsc1.amplitude.set(osc1Amp);
                break;

            case 1:
                squOsc1.output.connect(0, preMixer.input, 0);
                squOsc1.frequency.set(frequency);
                squOsc1.amplitude.set(osc1Amp);
                break;

            case 2:
                triOsc1.output.connect(0, preMixer.input, 0);
                triOsc1.frequency.set(frequency);
                triOsc1.amplitude.set(osc1Amp);
                break;
        }

        if (useOsc2)
            switch (osc2Waveshape) {
                case 0:
                    sawOsc2.output.connect(0, preMixer.input, 1);
                    sawOsc2.frequency.set(frequency * Math.pow(2, osc2RelativeOctave));
                    sawOsc2.amplitude.set(osc2Amp);
                    break;

                case 1:
                    squOsc2.output.connect(0, preMixer.input, 1);
                    squOsc2.frequency.set(frequency * Math.pow(2, osc2RelativeOctave));
                    squOsc2.amplitude.set(osc2Amp);
                    break;

                case 2:
                    triOsc2.output.connect(0, preMixer.input, 1);
                    triOsc2.frequency.set(frequency * Math.pow(2, osc2RelativeOctave));
                    triOsc2.amplitude.set(osc2Amp);
                    break;
            }

        if (useOsc3)
            switch (osc3Waveshape) {
                case 0:
                    sawOsc3.output.connect(0, preMixer.input, 2);
                    sawOsc3.frequency.set(frequency * Math.pow(2, osc3RelativeOctave));
                    sawOsc3.amplitude.set(osc3Amp);
                    break;

                case 1:
                    squOsc3.output.connect(0, preMixer.input, 2);
                    squOsc3.frequency.set(frequency * Math.pow(2, osc3RelativeOctave));
                    squOsc3.amplitude.set(osc3Amp);
                    break;

                case 2:
                    triOsc3.output.connect(0, preMixer.input, 2);
                    triOsc3.frequency.set(frequency * Math.pow(2, osc3RelativeOctave));
                    triOsc3.amplitude.set(osc3Amp);
                    break;
            }


        if (filterFourPoles) {
            filter4P.frequency.set(filterCutoff);
            filter4P.Q.set(filterQ);
            preMixer.output.connect(filter4P.input);
            filter4P.output.connect(mixer.input);
        }


        else {
            filterLP.frequency.set(filterCutoff);
            filterLP.Q.set(filterQ);
            preMixer.output.connect(filterLP.input);
            filterLP.output.connect(mixer.input);
        }

        synth.add(this);


//        System.out.println("Before: " + candidateSamples);
        return getWaveform(synth, mixer);//, lineOut);
//        System.out.println("After: " + candidateSamples);


    }

    Synthesizer synth;
    MixerMono mixer, preMixer;
    int osc1Waveshape, osc2Waveshape, osc3Waveshape, osc2Octave, osc3Octave;
    SawtoothOscillator sawOsc1, sawOsc2, sawOsc3;
    SquareOscillator squOsc1, squOsc2, squOsc3;
    TriangleOscillator triOsc1, triOsc2, triOsc3;
    FilterFourPoles filter4P;
    FilterLowPass filterLP;
}
