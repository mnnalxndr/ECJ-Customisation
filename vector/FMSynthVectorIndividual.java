package ec.vector;

import ec.EvolutionState;
import ec.app.project.v02.src.FMSynth;
import ec.util.MersenneTwisterFast;
import ec.vector.DoubleVectorIndividual;
import ec.vector.FloatVectorSpecies;

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
        FMSynth fmSynth = new FMSynth(genome[0], genome[1], genome[2], genome[3], genome[4],
                genome[5], genome[6], genome[7], genome[8],
                genome[9], genome[10], genome[11], genome[12]);
        candidateSamples = fmSynth.makeWaveform();
    }




    public float[] candidateSamples;
}
