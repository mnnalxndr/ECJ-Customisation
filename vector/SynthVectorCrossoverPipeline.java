package ec.vector.breed;

import ec.BreedingPipeline;
import ec.EvolutionState;
import ec.Evolve;
import ec.Individual;
import ec.vector.FMSynthVectorIndividual;
import ec.vector.SubtractiveSynthVectorIndividual;
import ec.vector.VectorIndividual;

/**
 * Created by alexmann on 17/08/2015.
 */
public class SynthVectorCrossoverPipeline extends VectorCrossoverPipeline {

    @Override
    public int produce(final int min,
                       final int max,
                       final int start,
                       final int subpopulation,
                       final Individual[] inds,
                       final EvolutionState state,
                       final int thread)

    {
        // how many individuals should we make?
        int n = typicalIndsProduced();
        if (n < min) n = min;
        if (n > max) n = max;

        // should we bother?
        if (!state.random[thread].nextBoolean(likelihood))
            return reproduce(n, start, subpopulation, inds, state, thread, true);  // DO produce children from source -- we've not done so already

        for(int q=start;q<n+start; /* no increment */)  // keep on going until we're filled up
        {
            // grab two individuals from our sources
            if (sources[0]==sources[1])  // grab from the same source
            {
                sources[0].produce(2,2,0,subpopulation,parents,state,thread);
                if (!(sources[0] instanceof BreedingPipeline))  // it's a selection method probably
                {
                    parents[0] = (VectorIndividual)(parents[0].clone());
                    parents[1] = (VectorIndividual)(parents[1].clone());
                }
            }
            else // grab from different sources
            {
                sources[0].produce(1,1,0,subpopulation,parents,state,thread);
                sources[1].produce(1,1,1,subpopulation,parents,state,thread);
                if (!(sources[0] instanceof BreedingPipeline))  // it's a selection method probably
                    parents[0] = (VectorIndividual)(parents[0].clone());
                if (!(sources[1] instanceof BreedingPipeline)) // it's a selection method probably
                    parents[1] = (VectorIndividual)(parents[1].clone());
            }

            // at this point, parents[] contains our two selected individuals,
            // AND they're copied so we own them and can make whatever modifications
            // we like on them.

            // so we'll cross them over now.  Since this is the default pipeline,
            // we'll just do it by calling defaultCrossover on the first child

            parents[0].defaultCrossover(state,thread,parents[1]);

            // Addition to class

            if (parents[0] instanceof FMSynthVectorIndividual) {
                FMSynthVectorIndividual parent = (FMSynthVectorIndividual) parents[0];
                System.out.println("Crossover Parent 1 FM Creation");
                ((FMSynthVectorIndividual) parents[0]).candidateSamples = Evolve.fmSynth.makeWaveform(parent.genome[0], parent.genome[1], parent.genome[2],
                        parent.genome[3], parent.genome[4], parent.genome[5], parent.genome[6], parent.genome[7],
                        parent.genome[8], parent.genome[9], parent.genome[10], parent.genome[11], parent.genome[12],
                        parent.genome[13], parent.genome[14], parent.genome[15], parent.genome[16], parent.genome[17]);
            } else if (parents[0] instanceof SubtractiveSynthVectorIndividual) {
                SubtractiveSynthVectorIndividual parent = (SubtractiveSynthVectorIndividual) parents[0];
                System.out.println("Crossover Parent 1 SubSynth Creation");
                ((SubtractiveSynthVectorIndividual) parents[1]).candidateSamples = Evolve.subtractiveSynth.makeWaveform(parent.genome[0], parent.genome[1], parent.genome[2], parent.genome[3], parent.genome[4],
                        parent.genome[5], parent.genome[6], parent.genome[7], parent.genome[8],
                        parent.genome[9], parent.genome[10], parent.genome[11], parent.genome[12], parent.genome[13]);
            }

            parents[0].evaluated=false;
            parents[1].evaluated=false;

            // add 'em to the population
            inds[q] = parents[0];
            q++;
            if (q<n+start && !tossSecondParent)
            {
                // Second addition to the class
                if (parents[1] instanceof FMSynthVectorIndividual) {
                    FMSynthVectorIndividual parent = (FMSynthVectorIndividual) parents[1];
                    System.out.println("Crossover Parent 2 FM Creation");
                    ((FMSynthVectorIndividual) parents[1]).candidateSamples = Evolve.fmSynth.makeWaveform(parent.genome[0], parent.genome[1], parent.genome[2],
                            parent.genome[3], parent.genome[4], parent.genome[5], parent.genome[6], parent.genome[7],
                            parent.genome[8], parent.genome[9], parent.genome[10], parent.genome[11], parent.genome[12],
                            parent.genome[13], parent.genome[14], parent.genome[15], parent.genome[16], parent.genome[17]);
                }
                else if (parents[1] instanceof SubtractiveSynthVectorIndividual) {
                    SubtractiveSynthVectorIndividual parent = (SubtractiveSynthVectorIndividual) parents[1];
                    System.out.println("Crossover Parent 2 SubSynth Creation");
                    ((SubtractiveSynthVectorIndividual) parents[1]).candidateSamples = Evolve.subtractiveSynth.makeWaveform(parent.genome[0], parent.genome[1], parent.genome[2], parent.genome[3], parent.genome[4],
                            parent.genome[5], parent.genome[6], parent.genome[7], parent.genome[8],
                            parent.genome[9], parent.genome[10], parent.genome[11], parent.genome[12], parent.genome[13]);
                }

                inds[q] = parents[1];
                q++;
            }
        }
        return n;
    }
}
