package ec.vector.breed;

import ec.BreedingPipeline;
import ec.EvolutionState;
import ec.Evolve;
import ec.Individual;
import ec.vector.*;
import ec.vector.breed.VectorMutationPipeline;

/**
 * Created by alexmann on 17/08/2015.
 */
public class SynthVectorMutationPipeline extends VectorMutationPipeline {

    @Override
    public int produce(final int min,
                       final int max,
                       final int start,
                       final int subpopulation,
                       final Individual[] inds,
                       final EvolutionState state,
                       final int thread)
    {
        // grab individuals from our source and stick 'em right into inds.
        // we'll modify them from there
        int n = sources[0].produce(min,max,start,subpopulation,inds,state,thread);

        // should we bother?
        if (!state.random[thread].nextBoolean(likelihood))
            return reproduce(n, start, subpopulation, inds, state, thread, false);  // DON'T produce children from source -- we already did

        // clone the individuals if necessary
        if (!(sources[0] instanceof BreedingPipeline))
            for(int q=start;q<n+start;q++)
                inds[q] = (Individual)(inds[q].clone());

        // mutate 'em
        for(int q=start;q<n+start;q++)
        {
            ((VectorIndividual)inds[q]).defaultMutate(state,thread);

            // Addition to class
            if (inds[q] instanceof FMSynthVectorIndividual) {
                FMSynthVectorIndividual ind = (FMSynthVectorIndividual) inds[q];
                System.out.println("Mutation FM Creation");
                ((FMSynthVectorIndividual) inds[q]).candidateSamples = Evolve.fmSynth.makeWaveform(ind.genome[0], ind.genome[1], ind.genome[2],
                        ind.genome[3], ind.genome[4], ind.genome[5], ind.genome[6], ind.genome[7],
                        ind.genome[8], ind.genome[9], ind.genome[10], ind.genome[11], ind.genome[12],
                        ind.genome[13], ind.genome[14], ind.genome[15], ind.genome[16], ind.genome[17]);
            }
            else if (inds[q] instanceof ec.vector.SubtractiveSynthVectorIndividual) {
                SubtractiveSynthVectorIndividual ind = (SubtractiveSynthVectorIndividual) inds[q];
                System.out.println("Mutation SubSynth Creation");
//                SubtractiveSynth subtractiveSynth = new SubtractiveSynth();
                ((SubtractiveSynthVectorIndividual) inds[q]).candidateSamples = Evolve.subtractiveSynth.makeWaveform(ind.genome[0], ind.genome[1], ind.genome[2], ind.genome[3], ind.genome[4],
                        ind.genome[5], ind.genome[6], ind.genome[7], ind.genome[8],
                        ind.genome[9], ind.genome[10], ind.genome[11], ind.genome[12], ind.genome[13]);
//                subtractiveSynth = null;
            }


            ((VectorIndividual)inds[q]).evaluated=false;
        }

        return n;
    }
}