'''
Note that this is an example of how you might use the ga to solve problems.
This file is not meant to be used as is.

This examples does not have some key requirements:
- no kwargs for the ga constructor
- not using command line arguments

You might call your selection strategy functions by a different name.
You might set up your problem differently.
Your ga might return just the best individual, but it can be useful to have more info.

IMPORTANT: to test your code, solve both the problem of swimming and scheduling
with your generalized ga framework.

'''

import swimmer
import ga
import selection_fns
import animate
import scheduling


def solve_swimmer():
    # set up 10 pairs of angles and an arm length of 5
    problem = swimmer.Problem()

    # the ga being used did not make use of kwargs
    ai = ga.GA(mrate=.01, gens=1000, pop=100, fselect="top_ninety_selection")

    # Now returns just the best individual, not a list of [fitness, individual]
    best_individual = ai.solve(problem)

    # Get the fitness of the best individual
    fitness = problem.evaluate_fitness(best_individual)
    print(f'Best Fitness: {fitness}')

    # Animate the best individual
    animate.animate(best_individual)  # the final best individual


def solve_schedule():
    '''
    This is the same scheduling problem that you previously solved with a ga.
    '''

    # if the students.csv file already exists, pass True
    problem = scheduling.Problem(True)

    ai = ga.GA()

    # Get just the best individual
    best_individual = ai.solve(problem)

    # Print fitness
    fitness = problem.evaluate_fitness(best_individual)
    print(f'Best Fitness: {fitness}')

    # Print roster
    problem.print_roster(best_individual)


# solve_schedule()

solve_swimmer()

