package ec.vector;

import ec.EvolutionState;
import ec.Evolve;
import ec.util.MersenneTwisterFast;

/**
 * Created by alexmann on 17/08/2015.
 */
public class SubtractiveSynthVectorIndividual extends DoubleVectorIndividual {

    @Override
    public void reset(EvolutionState state, int thread) {

        FloatVectorSpecies s = (FloatVectorSpecies) species;
        MersenneTwisterFast random = state.random[thread];
        for (int x = 0; x < genome.length; x++)
        {
            int type = s.mutationType(x);
            if (type == FloatVectorSpecies.C_INTEGER_RESET_MUTATION ||
                    type == FloatVectorSpecies.C_INTEGER_RANDOM_WALK_MUTATION)  // integer type
            {
                long minGene = (long)Math.floor(s.minGene(x));
                long maxGene = (long)Math.floor(s.maxGene(x));
                genome[x] = randomValueFromClosedInterval(minGene, maxGene, random); //minGene + random.nextLong(maxGene - minGene + 1);
            }
            else
            {
                genome[x] = (s.minGene(x) + random.nextDouble(true, true) * (s.maxGene(x) - s.minGene(x)));
            }
        }

        System.out.println("SubSynth Individual Creation");

//        System.out.println("Frequency:" + genome[0] +
//                "\tOsc1Shape:" + genome[4] + "\to1Amp:" + genome[11] +
//                "\nOsc2On?:" + ((genome[1]==1.0)? "Y":"N") + "\tOsc2Shape:" + genome[5] + "\tOsc2Oct:" + genome[7] + "\tOsc2Amp:" + genome[12] +
//                "\nOsc3On?:" + ((genome[2]==1.0)? "Y":"N") + "\tOsc3Shape:" + genome[6] + "\tOsc3Oct:" + genome[8] + "\tOsc3Amp:" + genome[13] +
//                "\nFilterType:"+ ((genome[3]==1.0)? "FourPoles":"LowPass") + "\tFilterFreq:" + genome[9] + "\tFilterQ" + genome[10]);
//
//        System.out.println("---");
//
//        System.out.println(genome[0] + ",\n" +
//                genome[1] + ",\n" +
//                genome[2] + ",\n" +
//                genome[3] + ",\n" +
//                genome[4] + ",\n" +
//                genome[5] + ",\n" +
//                genome[6] + ",\n" +
//                genome[7] + ",\n" +
//                genome[8] + ",\n" +
//                genome[9] + ",\n" +
//                genome[10] + ",\n" +
//                genome[11] + ",\n" +
//                genome[12] + ",\n" +
//                genome[13]);


        candidateSamples = Evolve.subtractiveSynth.makeWaveform(genome[0], genome[1], genome[2], genome[3], genome[4],
                genome[5], genome[6], genome[7], genome[8],
                genome[9], genome[10], genome[11], genome[12], genome[13]);


    }




    public float[] candidateSamples;
}
