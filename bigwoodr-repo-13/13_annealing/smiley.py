import math
import random
#not necessary
max_value = 15
class Smiley:
    '''
    Toy problem to get practice with simulated annealing.
    If the AI wanders about randomly rearranging numbers, how
    long until it happens upon an ordering that is sorted?
    '''
    goal = [['O', ' ', 'O'],
            [' ', '-', ' '],
            [' ', 'V', ' ']]

    characters = [' ', 'O', '-', 'V']

    row_count = 3
    col_count = 3

    def __init__(self):
        self.make_random_starter()
        print('In sorting starting with ', self.starter)

    def make_random_starter(self):
        # create a list with 0 through max_value randomly ordered
        starter = [i for i in range(4)]

        # mix it up
        for _ in range(max_value):
            i = random.randint(0, max_value)
            j = random.randint(0, max_value)
            temp = starter[i]
            starter[i] = starter[j]
            starter[j] = temp

        # set it to the instance variable
        self.starter = starter

    def cost(self, solution):
        cost = 0
        for i in range(max_value):
            if i != solution[i]:
                cost += 1
        return cost

    def generate_neighbor(self, solution):
        neighbor = solution.copy()
        i = random.randint(0, max_value)
        j = random.randint(0, max_value)
        temp = neighbor[i]
        neighbor[i] = neighbor[j]
        neighbor[j] = temp
        return neighbor

    def print(self,pic):
        for row in pic:
            for char in row:
                print(char,end='')
            print()

