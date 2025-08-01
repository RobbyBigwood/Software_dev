def fitness_function(population, students, courses):
    population_fitness = []
    for i in range(len(population)):
            fitness = 0

            for j in range(len(population[i])):
                # fitness based on the preference/rank of the course
                # that this student was enrolled in
                fitness += students[j].get_preference(population[i][j])
            for c in courses:
                # trying to maximize fitness.
                # if a course is overenrolled, decrease fitness
                fitness += min(0,ideal_enrollment-population[i].count(c))
                #fitness += 0

            # fitness is an integer that corresponds to the quality of
            # the solution for individual at population[j]
            population_fitness.append(fitness)
    return population_fitness
            