import ga
import random

def next_gen(pop,pop_fit):
    '''
    create the next generation based on the current population.
    Repeatedly choose parents, based on fitness, to create a child.
    '''
    # number of individual solutions at each iteration
    population_size = 100
    next_generation = []

    # every set of parents creates 2 children, so do this popsize/2
    for i in range(population_size//2):

        # choose parents based on fitness, which influences likelihood of selection
        parent1 = pop[ga.monte_carlo_selection(pop_fit)]
        parent2 = pop[ga.monte_carlo_selection(pop_fit)]

        # randomly choose a point to mix up their "dna"
        divide_at = random.randrange(1,len(parent1))
        next_generation.append(parent1[:divide_at]+parent2[divide_at:])
        next_generation.append(parent2[:divide_at]+parent1[divide_at:])

    return next_generation
