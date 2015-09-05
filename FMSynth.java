package ec.app.project.v02.src;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.MixerMono;
import com.jsyn.unitgen.SineOscillatorPhaseModulated;


/**
 * Created by alexmann on 14/08/2015.
 */
public class FMSynth extends SynthBase {

    public FMSynth () {

        synth = JSyn.createSynthesizer();
        mixer = new MixerMono(6);
        this.add(mixer);
        this.add(operator1 = new SineOscillatorPhaseModulated());
        this.add(operator2 = new SineOscillatorPhaseModulated());
        this.add(operator3 = new SineOscillatorPhaseModulated());
        this.add(operator4 = new SineOscillatorPhaseModulated());
        this.add(operator5 = new SineOscillatorPhaseModulated());
        this.add(operator6 = new SineOscillatorPhaseModulated());
    }

    public float[] getCandidateSamples(double mainFrequency, double operator1Amp, double preset,
                                       double operator2On, double operator3On, double operator4On, double operator5On, double operator6On,
                                       double operator2Freq, double operator3Freq, double operator4Freq, double operator5Freq, double operator6Freq,
                                       double operator2Amp, double operator3Amp, double operator4Amp, double operator5Amp, double operator6Amp) {

        operator1.output.disconnectAll();
        operator2.output.disconnectAll();
        operator3.output.disconnectAll();
        operator4.output.disconnectAll();
        operator5.output.disconnectAll();
        operator6.output.disconnectAll();
        mixer.output.disconnectAll();

        if (operator2On == 0)
            operator2Amp = 0;
        if (operator3On == 0)
            operator3Amp = 0;
        if (operator4On == 0)
            operator4Amp = 0;
        if (operator5On == 0)
            operator5Amp = 0;
        if (operator6On == 0)
            operator6Amp = 0;

        boolean integers = false;

        operator1.frequency.set(mainFrequency);
        operator1.amplitude.set(operator1Amp);
        operator2.amplitude.set(operator2Amp);
        operator3.amplitude.set(operator3Amp);
        operator4.amplitude.set(operator4Amp);
        operator5.amplitude.set(operator5Amp);
        operator6.amplitude.set(operator6Amp);

        if (integers) {
            operator2.frequency.set(operator2Freq * mainFrequency);
            operator3.frequency.set(operator3Freq * mainFrequency);
            operator4.frequency.set(operator4Freq * mainFrequency);
            operator5.frequency.set(operator5Freq * mainFrequency);
            operator6.frequency.set(operator6Freq * mainFrequency);
        }
        else {

            operator2.frequency.set(operator2Freq);
            operator3.frequency.set(operator3Freq);
            operator4.frequency.set(operator4Freq);
            operator5.frequency.set(operator5Freq);
            operator6.frequency.set(operator6Freq);
        }

        dx7Preset = (int) (preset + 1);

        switch (dx7Preset) {
            case 1:
                // Carriers other than operator 1 assigned note frequency
                operator3.frequency.set(mainFrequency);

                //
                operator1.amplitude.set(operator1Amp / 2);
                operator3.amplitude.set(operator3Amp / 2);

                // Modulation connections
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator4.modulation);
                operator4.output.connect(operator3.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                break;

            case 2:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 2);

                // Modulation connections
                operator2.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator4.modulation);
                operator4.output.connect(operator3.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                break;

            case 3:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 2);

                // Modulation connections
                operator3.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator4.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                break;

            case 4:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 2);

                // Modulation connections
                operator3.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator4.modulation);
                operator4.output.connect(operator6.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                break;

            case 5:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 3);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 3);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 3);

                // Modulation connections
                operator2.output.connect(operator1.modulation);
                operator4.output.connect(operator3.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator5.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 6:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 3);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 3);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 3);

                // Modulation connections
                operator2.output.connect(operator1.modulation);
                operator4.output.connect(operator3.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator6.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 7:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 2);

                // Modulation connections
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator5.modulation);
                operator4.output.connect(operator3.modulation);
                operator5.output.connect(operator3.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                break;

            case 8:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 2);

                // Modulation connections
                operator2.output.connect(operator1.modulation);
                operator4.output.connect(operator4.modulation);
                operator4.output.connect(operator3.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator3.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                break;

            case 9:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 2);

                // Modulation connections
                operator2.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator4.output.connect(operator3.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator3.modulation);

                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                break;

            case 10:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 2);

                // Modulation connections
                operator3.output.connect(operator3.modulation);
                operator3.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator4.modulation);
                operator5.output.connect(operator4.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                break;

            case 11:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 2);

                // Modulation connections
                operator3.output.connect(operator3.modulation);
                operator3.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator4.modulation);
                operator5.output.connect(operator4.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                break;

            case 12:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 2);

                // Modulation connections
                operator2.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator4.output.connect(operator3.modulation);
                operator5.output.connect(operator3.modulation);
                operator6.output.connect(operator3.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                break;

            case 13:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 2);

                // Modulation connections
                operator2.output.connect(operator1.modulation);
                operator4.output.connect(operator3.modulation);
                operator5.output.connect(operator3.modulation);
                operator6.output.connect(operator3.modulation);
                operator6.output.connect(operator6.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                break;

            case 14:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 2);

                // Modulation connections
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator4.modulation);
                operator5.output.connect(operator4.modulation);
                operator4.output.connect(operator3.modulation);


                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                break;

            case 15:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 2);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 2);

                // Modulation connections
                operator2.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator4.modulation);
                operator5.output.connect(operator4.modulation);
                operator4.output.connect(operator3.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                break;

            case 16:
                operator1.amplitude.set(operator1Amp);

                // Operator 1 only carrier, so straight to modulation connections
                operator2.output.connect(operator1.modulation);
                operator4.output.connect(operator3.modulation);
                operator3.output.connect(operator1.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator1.modulation);

                // Connect carrier to mixer
                operator1.output.connect(mixer.input);
                break;

            case 17:
                operator1.amplitude.set(operator1Amp);

                // Operator 1 only carrier, so straight to modulation connections
                operator2.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator4.output.connect(operator3.modulation);
                operator3.output.connect(operator1.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator1.modulation);

                // Connect carrier to mixer
                operator1.output.connect(mixer.input);
                break;

            case 18:
                operator1.amplitude.set(operator1Amp);

                // Operator 1 only carrier, so straight to modulation connections
                operator2.output.connect(operator1.modulation);
                operator3.output.connect(operator3.modulation);
                operator3.output.connect(operator1.modulation);
                operator6.output.connect(operator5.modulation);
                operator5.output.connect(operator4.modulation);
                operator4.output.connect(operator1.modulation);

                // Connect carrier to mixer
                operator1.output.connect(mixer.input);
                break;

            case 19:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 3);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 3);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 3);

                // Modulation connections
                operator3.output.connect(operator2.modulation);
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator4.modulation);
                operator6.output.connect(operator5.modulation);


                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 20:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 3);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 3);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 3);

                // Modulation connections
                operator3.output.connect(operator3.modulation);
                operator3.output.connect(operator1.modulation);
                operator3.output.connect(operator2.modulation);
                operator5.output.connect(operator4.modulation);
                operator6.output.connect(operator4.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                break;

            case 21:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 4);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 4);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 4);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 4);

                // Modulation connections
                operator3.output.connect(operator3.modulation);
                operator3.output.connect(operator1.modulation);
                operator3.output.connect(operator2.modulation);
                operator6.output.connect(operator4.modulation);
                operator6.output.connect(operator5.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 22:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 4);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 4);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 4);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 4);

                // Modulation connections
                operator2.output.connect(operator1.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator3.modulation);
                operator6.output.connect(operator4.modulation);
                operator6.output.connect(operator5.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 23:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 4);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 4);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 4);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 4);

                // Modulation connections
                operator3.output.connect(operator2.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator4.modulation);
                operator6.output.connect(operator5.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 24:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 5);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 5);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 5);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 5);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 5);

                // Modulation connections
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator3.modulation);
                operator6.output.connect(operator4.modulation);
                operator6.output.connect(operator5.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 25:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 5);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 5);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 5);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 5);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 5);

                // Modulation connections
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator4.modulation);
                operator6.output.connect(operator5.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 26:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 3);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 3);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 3);

                // Modulation connections
                operator3.output.connect(operator2.modulation);
                operator5.output.connect(operator4.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator4.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                break;

            case 27:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 3);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 3);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 3);

                // Modulation connections
                operator3.output.connect(operator3.modulation);
                operator3.output.connect(operator2.modulation);
                operator5.output.connect(operator4.modulation);
                operator6.output.connect(operator4.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                break;

            case 28:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 3);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 3);
                operator6.frequency.set(mainFrequency);
                operator6.amplitude.set(operator6Amp / 3);

                // Modulation connections
                operator2.output.connect(operator1.modulation);
                operator5.output.connect(operator5.modulation);
                operator5.output.connect(operator4.modulation);
                operator4.output.connect(operator3.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator6.output.connect(mixer.input);
                break;

            case 29:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 4);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 4);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 4);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 4);

                // Modulation connections
                operator4.output.connect(operator3.modulation);
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator5.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 30:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 4);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 4);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 4);
                operator6.frequency.set(mainFrequency);
                operator6.amplitude.set(operator6Amp / 4);

                // Modulation connections
                operator5.output.connect(operator5.modulation);
                operator5.output.connect(operator4.modulation);
                operator4.output.connect(operator3.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator6.output.connect(mixer.input);
                break;

            case 31:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 5);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 5);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 5);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 5);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 5);

                // Modulation
                operator6.output.connect(operator6.modulation);
                operator6.output.connect(operator5.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                break;

            case 32:
                // Carriers other than operator 1 assigned note frequency
                operator1.amplitude.set(operator1Amp / 6);
                operator2.frequency.set(mainFrequency);
                operator2.amplitude.set(operator2Amp / 6);
                operator3.frequency.set(mainFrequency);
                operator3.amplitude.set(operator3Amp / 6);
                operator4.frequency.set(mainFrequency);
                operator4.amplitude.set(operator4Amp / 6);
                operator5.frequency.set(mainFrequency);
                operator5.amplitude.set(operator5Amp / 6);
                operator6.frequency.set(mainFrequency);
                operator6.amplitude.set(operator6Amp / 6);

                // Modulation connections
                operator6.output.connect(operator6.modulation);

                // Connect carriers to mixer
                operator1.output.connect(mixer.input);
                operator2.output.connect(mixer.input);
                operator3.output.connect(mixer.input);
                operator4.output.connect(mixer.input);
                operator5.output.connect(mixer.input);
                operator6.output.connect(mixer.input);
                break;

            default:
                System.err.println("Incorrect DX7 preset inputted");
                System.exit(1);

        }

        synth.add(this);

        return getWaveform(synth, mixer);

    }
    Synthesizer synth;
    MixerMono mixer;
    int dx7Preset;
    SineOscillatorPhaseModulated operator1, operator2, operator3, operator4, operator5, operator6;
}
