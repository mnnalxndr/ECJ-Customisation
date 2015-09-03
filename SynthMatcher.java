package ec.app.project.v02.src;

import com.softsynth.jsyn.Synth;
import ec.*;
import ec.vector.*;
import ec.simple.*;

/**
 * Created by alexmann on 29/07/2015.
 */
public class SynthMatcher extends Problem implements SimpleProblemForm {

    @Override
    public void evaluate(final EvolutionState state,
                         final Individual ind,
                         final int subpopulation,
                         final int threadnum)
    {
        if (ind.evaluated) return;


        if (ind instanceof FMSynthVectorIndividual) {
            if (!(ind.size() == 18))
                state.output.fatal("FMSynthVectorIndividual wrong length!", null);

            FMSynthVectorIndividual ind2 = (FMSynthVectorIndividual) ind;

            System.out.println("Frequency:" + ind2.genome[0] +
                    " Amplitude:" + ind2.genome[1] + " DX7Preset:" + ind2.genome[2] +
                    " Op2Freq:" + ind2.genome[3] + " Op2Amp:" + ind2.genome[4] +
                    " Op2Freq:" + ind2.genome[5] + " Op2Amp:" + ind2.genome[6] +
                    " Op2Freq:" + ind2.genome[7] + " Op2Amp:" + ind2.genome[8] +
                    " Op2Freq:" + ind2.genome[9] + " Op2Amp:" + ind2.genome[10] +
                    " Op2Freq:" + ind2.genome[11] + " Op2Amp:" + ind2.genome[12]);

            double fitness = 421 - normaliseAndCompare(Evolve.targetSamples, ind2.candidateSamples);

            System.out.println("FMIndividual fitness = " + fitness + "\n");

            if (!(ind2.fitness instanceof SimpleFitness))
                state.output.fatal("Whoa!  It's not a SimpleFitness!!!", null);

            ((SimpleFitness) ind2.fitness).setFitness(state,
                    /// ...the fitness...
                    fitness,
                    ///... is the individual ideal?  Indicate here...
                    fitness >= 400);
            ind2.evaluated = true;
        }
        else if (ind instanceof SubtractiveSynthVectorIndividual){
            if (!(ind.size() == 14))
                state.output.fatal("SubSynthVectorIndividual wrong length", null);

            SubtractiveSynthVectorIndividual ind2 = (SubtractiveSynthVectorIndividual) ind;

            System.out.println("Frequency:" + ind2.genome[0] +
                    " o2On:" + ind2.genome[1] + " o3On:" + ind2.genome[2] + " filtType:" + ind2.genome[3] +
                    " o1Shape:" + ind2.genome[4] + " o2Shape:" + ind2.genome[5] + " o3Shape:" + ind2.genome[6] +
                    " o2Oct:" + ind2.genome[7] + " o3Oct:" + ind2.genome[8] +
                    " filtFreq:" + ind2.genome[9] + " filtQ" + ind2.genome[10] +
                    " o1Amp:" + ind2.genome[11] + " o2Amp:" + ind2.genome[12] + " o3Amp:" + ind2.genome[13]);

            double fitness = 421 - normaliseAndCompare(Evolve.targetSamples, ind2.candidateSamples);

            System.out.println("SubsynthIndividual fitness = " + fitness + "\n");

            if (!(ind2.fitness instanceof SimpleFitness))
                state.output.fatal("It's not a SimpleFitness", null);

            ((SimpleFitness) ind2.fitness).setFitness(state,
                    /// ...the fitness...
                    fitness,
                    ///... is the individual ideal?  Indicate here...
                    fitness >= 420);
            ind2.evaluated = true;
        }
        else
            state.output.fatal("Whoa!  It's not a SynthVectorIndividual!!!",null);
    }

    @Override
    public void describe(
            final EvolutionState state,
            final Individual ind,
            final int subpopulation,
            final int threadnum,
            final int log)
    {



        if (ind instanceof FMSynthVectorIndividual) {

            FMSynthVectorIndividual ind2 = (FMSynthVectorIndividual) ind;

            System.out.println("\nThe best FMSynth individual (subpop 1) of the run is: ");
            System.out.println("Frequency:" + ind2.genome[0] +
                    "\tAmplitude:" + ind2.genome[1] + "\tDX7Preset:" + ind2.genome[2] +
                    "\nOp2On?:"+ ((ind2.genome[3]==1.0)? "Y":"N") + "\tOp2Freq:" + ind2.genome[8] + "\tOp2Amp:" + ind2.genome[13] +
                    "\nOp3On?:"+ ((ind2.genome[4]==1.0)? "Y":"N") + "\tOp3Freq:" + ind2.genome[9] + "\tOp3Amp:" + ind2.genome[14] +
                    "\nOp4On?:"+ ((ind2.genome[5]==1.0)? "Y":"N") + "\tOp4Freq:" + ind2.genome[10] + "\tOp4Amp:" + ind2.genome[15] +
                    "\nOp5On?:"+ ((ind2.genome[6]==1.0)? "Y":"N") + "\tOp5Freq:" + ind2.genome[11] + "\tOp5Amp:" + ind2.genome[16] +
                    "\nOp6On?:"+ ((ind2.genome[7]==1.0)? "Y":"N") + "\tOp6Freq:" + ind2.genome[12] + "\tOp6Amp:" + ind2.genome[17]);

            System.out.println("---");

            System.out.println(ind2.genome[0] + ",\n" +
                    ind2.genome[1] + ",\n" +
                    ind2.genome[2] + ",\n" +
                    ind2.genome[3] + ",\n" +
                    ind2.genome[4] + ",\n" +
                    ind2.genome[5] + ",\n" +
                    ind2.genome[6] + ",\n" +
                    ind2.genome[7] + ",\n" +
                    ind2.genome[8] + ",\n" +
                    ind2.genome[9] + ",\n" +
                    ind2.genome[10] + ",\n" +
                    ind2.genome[11] + ",\n" +
                    ind2.genome[12] + ",\n" +
                    ind2.genome[13] + ",\n" +
                    ind2.genome[14] + ",\n" +
                    ind2.genome[15] + ",\n" +
                    ind2.genome[16] + ",\n" +
                    ind2.genome[17]);
        }

        else if (ind instanceof SubtractiveSynthVectorIndividual) {

            SubtractiveSynthVectorIndividual ind2 = (SubtractiveSynthVectorIndividual) ind;

            System.out.println("\nThe best SubSynth individual (subpop 0) of the run is: ");
            System.out.println("Frequency:" + ind2.genome[0] +
                    "\tOsc1Shape:" + ind2.genome[4] + "\to1Amp:" + ind2.genome[11] +
                    "\nOsc2On?:" + ((ind2.genome[1]==1.0)? "Y":"N") + "\tOsc2Shape:" + ind2.genome[5] + "\tOsc2Oct:" + ind2.genome[7] + "\tOsc2Amp:" + ind2.genome[12] +
                    "\nOsc3On?:" + ((ind2.genome[2]==1.0)? "Y":"N") + "\tOsc3Shape:" + ind2.genome[6] + "\tOsc3Oct:" + ind2.genome[8] + "\tOsc3Amp:" + ind2.genome[13] +
                    "\nFilterType:"+ ((ind2.genome[3]==1.0)? "FourPoles":"LowPass") + "\tFilterFreq:" + ind2.genome[9] + "\tFilterQ" + ind2.genome[10]);

            System.out.println("---");

            System.out.println(ind2.genome[0] + ",\n" +
                    ind2.genome[1] + ",\n" +
                    ind2.genome[2] + ",\n" +
                    ind2.genome[3] + ",\n" +
                    ind2.genome[4] + ",\n" +
                    ind2.genome[5] + ",\n" +
                    ind2.genome[6] + ",\n" +
                    ind2.genome[7] + ",\n" +
                    ind2.genome[8] + ",\n" +
                    ind2.genome[9] + ",\n" +
                    ind2.genome[10] + ",\n" +
                    ind2.genome[11] + ",\n" +
                    ind2.genome[12] + ",\n" +
                    ind2.genome[13]);
        }
        return;
    }

    public static double normaliseAndCompare(float[] targetSamples, float[] candidateSamples) {

        // Iterate through both arrays and set each MaxFloat value to the highest value in each array
        float targetMaxFloat = 0;
        float candidateMaxFloat = 0;
        for (int i = 0; i<targetSamples.length && i<candidateSamples.length; i++) {

            if (targetSamples[i] > targetMaxFloat)
                targetMaxFloat = targetSamples[i];

            if (candidateSamples[i] > candidateMaxFloat)
                candidateMaxFloat = candidateSamples[i];
        }


        double runningTotal = 0;
        for (int i = 0; i < targetSamples.length && i < candidateSamples.length; i++) {

            // Normalise each float value as it is passed in the iteration
            float candidateSample = (candidateSamples[i]/candidateMaxFloat);
            float targetSample = (targetSamples[i]/targetMaxFloat);

            // Comparison function
            runningTotal += (((candidateSample) - targetSample) * (candidateSample - targetSample));

        }
        double rawFitness = Math.sqrt(runningTotal)/Math.sqrt(Evolve.lengthInS);

        return rawFitness;
    }
}
