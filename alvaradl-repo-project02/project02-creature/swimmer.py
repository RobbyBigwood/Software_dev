import random
import fitness
import animate


class Problem:

    # create a possible solution for moving "forwards" in the water
    # y is the first element in the nested arrays and z is the second
    def get_individual(self):
        # create 16 values that represent 8 points in time.
        individual = []
        for i in range(16):
            # Each time has 2 angles in them which represent the y and z value of a graph between - 90 and 90
            individual.append(random.randint(-90, 90))
        return individual

    # calculate the distance of the individual and return its score
    # optimal is for the creature to use the y-axis to move forwards and the z-axis to reset their arms
    def evaluate_fitness(self, individual):
        distance = 0
        # get the first position
        prev_pos = fitness.calc_position(individual[0], individual[1])
        # get current position
        for i in range(2, 16, 2):
            curr_pos = fitness.calc_position(individual[i], individual[i + 1])
            # get the change in each location
            distance += fitness.calculate_distance(prev_pos, curr_pos, 10)
        # The score is equal to the distance traveled
        score = distance
        return score
    
    def visualize(self, best_individual):
        # Using the animate module to see the movement of the best individual
        animate.animate(best_individual)
    
    def mutate(self, individual):
        # Selecting a random index from individual
        index = random.randint(0, len(individual) - 1)
        # Randomly modify the angle (within a range of -10 to 10 degrees)
        individual[index] += random.randint(-10, 10)
        # Ensure the angle stays within bounds of -90 to 90 degrees
        individual[index] = max(min(individual[index], 90), -90)
        return individual
