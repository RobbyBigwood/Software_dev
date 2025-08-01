import random


def monte_carlo_selection(population, fitness_scores, population_size):
    """Selects parents using Monte Carlo selection from the entire population."""
    # Handle negative fitness values
    min_fitness = min(fitness_scores)
    if min_fitness < 0:
        adjusted_scores = [score - min_fitness + 0.1 for score in fitness_scores]
    else:
        adjusted_scores = fitness_scores

    # Select individuals based on their fitness probability
    return random.choices(population, weights=adjusted_scores, k=population_size)


def top_ninety_selection(population, fitness_scores, population_size):
    """Selects parents using Monte Carlo selection, excluding the bottom 10%."""
    # Create pairs of (individual, fitness)
    pairs = list(zip(population, fitness_scores))

    # Sort the pairs
    sorted_pairs = []
    for pair in pairs:
        sorted_pairs.append(pair)

    # Sort by fitness score
    for i in range(len(sorted_pairs)):
        for j in range(0, len(sorted_pairs) - i - 1):
            if sorted_pairs[j][1] > sorted_pairs[j + 1][1]:
                # Swap if the current fitness is greater than the next
                sorted_pairs[j], sorted_pairs[j + 1] = sorted_pairs[j + 1], sorted_pairs[j]

    # Set fitness of bottom 10% to 0
    bottom_count = int(len(sorted_pairs) * 0.1)
    for i in range(bottom_count):
        sorted_pairs[i] = (sorted_pairs[i][0], 0)

    # Extract individuals and adjusted scores
    adjusted_population = []
    adjusted_scores = []
    for pair in sorted_pairs:
        adjusted_population.append(pair[0])
        adjusted_scores.append(pair[1])

    # Randomly select individuals based on fitness score
    return random.choices(adjusted_population, weights=adjusted_scores, k=population_size)


def hybrid_selection(population, fitness_scores, population_size):
    """Hybrid selection: first parent from entire population, second from top 10%."""
    # Handle negative fitness values
    min_fitness = min(fitness_scores)
    if min_fitness < 0:
        adjusted_scores = [score - min_fitness + 0.1 for score in fitness_scores]
    else:
        adjusted_scores = fitness_scores

    # Create pairs of (individual, fitness)
    pairs = list(zip(population, fitness_scores))

    # Sort pairs by fitness (highest first)
    sorted_pairs = []
    for pair in pairs:
        sorted_pairs.append(pair)

    # Sort by fitness score
    for i in range(len(sorted_pairs)):
        for j in range(0, len(sorted_pairs) - i - 1):
            if sorted_pairs[j][1] < sorted_pairs[j + 1][1]:  # Note: < for descending order
                sorted_pairs[j], sorted_pairs[j + 1] = sorted_pairs[j + 1], sorted_pairs[j]

    # Get top 10% individuals
    top_count = max(1, int(len(sorted_pairs) * 0.1))
    top_individuals = []
    for i in range(top_count):
        top_individuals.append(sorted_pairs[i][0])

    # Select parents
    selected = []
    for _ in range(population_size // 2):
        # First parent from Monte Carlo selection
        parent1 = random.choices(population, weights=adjusted_scores, k=1)[0]
        # Second parent from top 10%
        parent2 = random.choice(top_individuals)
        selected.append(parent1)
        selected.append(parent2)

    # Add one more if needed
    if population_size % 2 != 0:
        selected.append(random.choices(population, weights=adjusted_scores, k=1)[0])

    return selected
