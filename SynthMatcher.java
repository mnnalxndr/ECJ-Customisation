package ec.app.project.v02.src;

import ec.*;
import ec.vector.*;
import ec.simple.*;

/**
 * Created by alexmann on 29/07/2015.
 */
public class SynthMatcher extends Problem implements SimpleProblemForm {

    public void evaluate(final EvolutionState state,
                         final Individual ind,
                         final int subpopulation,
                         final int threadnum)
    {
        if (ind.evaluated) return;

        if (!(ind instanceof FMSynthVectorIndividual))
            state.output.fatal("Whoa!  It's not a SynthVectorIndividual!!!",null);

        if(!(ind.size() == 13))
            state.output.fatal("IntegerVectorIndividual wrong length", null);


        FMSynthVectorIndividual ind2 = (FMSynthVectorIndividual)ind;

//        float[] candidateSamples = Candidate.generateCandidate(ind2.genome[0], ind2.genome[1]);
        double fitness = Candidate.normaliseAndCompare(Evolve.targetSamples, ind2.candidateSamples);

        if (!(ind2.fitness instanceof SimpleFitness))
            state.output.fatal("Whoa!  It's not a SimpleFitness!!!",null);

        ((SimpleFitness)ind2.fitness).setFitness(state,
                /// ...the fitness...
//                sum/(double)ind2.genome.length,
                420-fitness,
                ///... is the individual ideal?  Indicate here...
                fitness>=400);
//                sum == ind2.genome.length);
        ind2.evaluated = true;
    }
}
