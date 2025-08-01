import random
import selection_fns
import matplotlib.pyplot as plt
import numpy as np


class GA:
    def __init__(self, **kwargs):
        self.mutation_rate = kwargs.get("mrate", 0.01)
        self.generations = kwargs.get("gens", 1000)
        self.population_size = kwargs.get("pop", 100)
        self.selection_function = kwargs.get("fselect", "monte_carlo")

    def solve(self, problem):
        print(f"Starting GA with {self.generations} generations, population size: {self.population_size}")

        # Initialize population
        population = [problem.get_individual() for _ in range(self.population_size)]

        # Track the best individual we've ever seen
        best_individual = None
        best_fitness = float('-inf')

        # Lists to track fitness progress for plotting
        best_fitness_history = []
        avg_fitness_history = []

        # Generation loop
        for generation in range(self.generations):
            # Evaluate all individuals
            fitness_scores = [problem.evaluate_fitness(ind) for ind in population]

            # Find this generation's best individual
            current_best_fitness = max(fitness_scores)
            current_best_index = fitness_scores.index(current_best_fitness)
            current_best_individual = population[current_best_index]

            # Store metrics for plotting
            best_fitness_history.append(max(fitness_scores))
            avg_fitness_history.append(sum(fitness_scores) / len(fitness_scores))

            # Update all-time best if needed
            if best_individual is None or current_best_fitness > best_fitness:
                best_fitness = current_best_fitness
                best_individual = current_best_individual.copy()  # Make a copy to be safe

            # Print progress every 50 generations
            if generation % 50 == 0 or generation == self.generations - 1:
                print(f"Generation {generation} | Best Fitness: {current_best_fitness:.4f}")

            if self.selection_function == 1:
                parents = selection_fns.monte_carlo_selection(population, fitness_scores, self.population_size)
            elif self.selection_function == 2:
                parents = selection_fns.top_ninety_selection(population, fitness_scores, self.population_size)
            elif self.selection_function == 3:
                parents = selection_fns.hybrid_selection(population, fitness_scores, self.population_size)
            else:
                # Default
                parents = selection_fns.monte_carlo_selection(population, fitness_scores, self.population_size)

            # Create the next generation
            next_generation = []

            # Fill the population with children
            while len(next_generation) < self.population_size:
                # Select two parents
                parent1 = random.choice(parents)
                parent2 = random.choice(parents)

                # Create child through single-point crossover
                child = self.crossover(parent1, parent2)

                # Mutate
                child = self.mutate(child)

                # Add to new population
                next_generation.append(child)

            # Replace the old population
            population = next_generation

        # Plot the learning progress after all generations
        self.plot_fitness(best_fitness_history, avg_fitness_history)

        return best_individual

    def crossover(self, parent1, parent2):
        # Single-point crossover
        point = random.randint(1, len(parent1) - 1)
        return parent1[:point] + parent2[point:]

    def mutate(self, individual):
        # Create a copy
        mutated = individual.copy()

        # Apply standard mutation
        for i in range(len(mutated)):
            if random.random() < self.mutation_rate:
                if isinstance(mutated[i], str) and mutated[i].isalpha():
                    mutated[i] = random.choice(['A', 'B', 'C', 'D', 'E'])
                else:
                    mutated[i] = random.randint(-90, 90)

        return mutated

    def plot_fitness(self, best_fitness_history, avg_fitness_history):
        """Plot the progress of fitness over generations"""
        plt.figure(figsize=(10, 6))
        generations = range(len(best_fitness_history))

        # Plot best and average fitness
        plt.plot(generations, best_fitness_history, 'r-', label='Best Fitness')
        plt.plot(generations, avg_fitness_history, 'b-', label='Average Fitness')

        # Add grid, legend, and labels
        plt.grid(True, linestyle='--', alpha=0.7)
        plt.legend(loc='best')
        plt.xlabel('Generation')
        plt.ylabel('Fitness')
        plt.title('Genetic Algorithm Learning Progress')

        # Show the plot (no save)
        plt.tight_layout()
        plt.show()
