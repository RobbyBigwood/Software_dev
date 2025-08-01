import sys
import swimmer
import ga


'''
Please do not change the signature of the Problem and GA constructors.
If you think it needs to be changed, please let Dr. Larson know.
'''

mutation_rate = .01

# function for selecting parents
selection_function = 1
population_size = 100
max_generations = 1000

for i in range(len(sys.argv)):
    match sys.argv[i]:
        case("-g"):
            max_generations = int(sys.argv[i + 1])
        case ("-p"):
            population_size = int(sys.argv[i + 1])
        case ("-s"):
            selection_function = int(sys.argv[i + 1])
        # no default because it might be the value after the -x flag

# end for


# Create the "swimmer" problem.
# The problem is to learn a swimming stroke that maximizes forward motion.
problem = swimmer.Problem()

# Setting up simple test
print("Running quick test of get_individual and evaluate_fitness")
test_individual = problem.get_individual()
print("Individual:", test_individual)
print("Fitness:", problem.evaluate_fitness(test_individual))
# Create a genetic algorithm that can solve the problem
ai = ga.GA(
    mrate=mutation_rate,
    gens=max_generations,
    pop=population_size,
    fselect=selection_function
)

best = ai.solve(problem)

# Seeing the best individual
problem.visualize(best)
