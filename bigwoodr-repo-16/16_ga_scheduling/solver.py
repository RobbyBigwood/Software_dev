'''
A Genetic Algorithm is a search technique for solving a problem.
It explores a solution space by creating a population of "solutions".
It then calculates the fitness of each solution and uses that fitness
to create the next generation.

The more fit an individual is, the more likely they will be selected as a
parent for the next generation. To make a new individual for the next
generation, 2 people are chosen at random. Those with a higher fitness score
are more likely to be selected. The solutions are recombined to make 2
new individuals. On occasion, one of the individuals is subject to a "mutation" --
a random modification to the individual solution.

In this problem, which is a classic scheduling problem,
the goal is to enroll each student in 1 course.
Ideally, each student would be enrolled in their preferred course (ie. the one
ranked the highest), and every course would have the same number of students.

'''

from make_students import make_students

import student
import ga

student_count = 5
courses = ['A','B','C','D']

# the first time you run this, keep this line
# after that, comment it out -- you only need to make the file once
make_students(student_count, courses)

f = open('students.csv')
student_info = f.readlines()
f.close()

students = []
for line in student_info:
    # make into a list
    lister = line[:-1].split(',')
    for i in range(2,len(lister)):
        lister[i] = int(lister[i])

    students.append(student.Student(lister))

#for s in students:
#    print(s)

ga.solve(students,courses)
weighted = {0.1, 0.5, 0.3, 0.2}
count_zero = 0
count_ones = 0
count_twos = 0
count_threes = 0
for i in range(20):

    solved = ga.monte_carlo_selection(weighted)
    match solved:
        case 1:
            count_ones += 1
        case 2:
            count_twos +=1
        case 3:
            count_threes +=1
        case 0:
            count_zero += 1
print(count_zero)
print(count_ones)
print(count_twos)
print(count_threes)
