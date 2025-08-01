from fitness_function import fitness_function

def alternative_fitness_function(population, students, courses):
    """
    An alternative fitness function that considers student preferences more heavily
    and penalizes over-enrollment more aggressively.
    """
    population_fitness = []
    
    for i in range(len(population)):
        fitness = 0
        enrollment_counts = {c: 0 for c in courses}
        
        for j in range(len(population[i])):
            course = population[i][j]
            fitness += students[j].get_preference(course) * 2  # Increase weight of preference
            enrollment_counts[course] += 1
        
        for c in courses:
            penalty = abs(enrollment_counts[c] - (len(students) // len(courses)))
            fitness -= penalty * 2  # Stronger penalty for imbalance
        
        population_fitness.append(fitness)
    
    return population_fitness