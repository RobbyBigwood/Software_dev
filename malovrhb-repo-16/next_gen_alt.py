import ga
import random

def next_gen(pop, pop_fit):
    """
    Create the next generation using uniform crossover.
    For each gene in the offspring, randomly select from either parent.
    """
    population_size = 100
    next_generation = []

    while len(next_generation) < population_size:
        # Select two parents based on fitness using Monte Carlo selection.
        parent1 = pop[ga.monte_carlo_selection(pop_fit)]
        parent2 = pop[ga.monte_carlo_selection(pop_fit)]
        
        # Create two children using uniform crossover.
        child1 = []
        child2 = []
        for gene1, gene2 in zip(parent1, parent2):
            # For each gene, flip a coin to decide from which parent to inherit.
            if random.random() < 0.5:
                child1.append(gene1)
                child2.append(gene2)
            else:
                child1.append(gene2)
                child2.append(gene1)
        
        next_generation.extend([child1, child2])
    
    # Ensure we return exactly population_size individuals.
    return next_generation[:population_size]

