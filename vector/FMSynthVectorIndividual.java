package ec.vector;

import ec.EvolutionState;
import ec.Evolve;
import ec.util.MersenneTwisterFast;

/**
 * Created by alexmann on 17/08/2015.
 */
public class FMSynthVectorIndividual extends DoubleVectorIndividual {

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

        System.out.println("FM Individual Creation");
//
//        System.out.println("Frequency:" + genome[0] +
//                "\tAmplitude:" + genome[1] + "\tDX7Preset:" + genome[2]+1 +
//                "\nOp2On?:"+ ((genome[3]==1.0)? "Y":"N") + "\tOp2Freq:" + genome[8] + "\tOp2Amp:" + genome[13] +
//                "\nOp3On?:"+ ((genome[4]==1.0)? "Y":"N") + "\tOp3Freq:" + genome[9] + "\tOp3Amp:" + genome[14] +
//                "\nOp4On?:"+ ((genome[5]==1.0)? "Y":"N") + "\tOp4Freq:" + genome[10] + "\tOp4Amp:" + genome[15] +
//                "\nOp5On?:"+ ((genome[6]==1.0)? "Y":"N") + "\tOp5Freq:" + genome[11] + "\tOp5Amp:" + genome[16] +
//                "\nOp6On?:"+ ((genome[7]==1.0)? "Y":"N") + "\tOp6Freq:" + genome[12] + "\tOp6Amp:" + genome[17]);
//
//        System.out.println("---");
//
//        System.out.println(genome[0] + ",\n" +
//                        genome[1] + ",\n" +
//                        genome[2] + ",\n" +
//                        genome[3] + ",\n" +
//                        genome[4] + ",\n" +
//                        genome[5] + ",\n" +
//                        genome[6] + ",\n" +
//                        genome[7] + ",\n" +
//                        genome[8] + ",\n" +
//                        genome[9] + ",\n" +
//                        genome[10] + ",\n" +
//                        genome[11] + ",\n" +
//                        genome[12] + ",\n" +
//                        genome[13] + ",\n" +
//                        genome[14] + ",\n" +
//                        genome[15] + ",\n" +
//                        genome[16] + ",\n" +
//                        genome[17]);

        candidateSamples = Evolve.fmSynth.makeWaveform(genome[0], genome[1], genome[2],
                genome[3], genome[4], genome[5], genome[6], genome[7],
                genome[8], genome[9], genome[10], genome[11], genome[12],
                genome[13], genome[14], genome[15], genome[16], genome[17]);
    }




    public float[] candidateSamples;
}
