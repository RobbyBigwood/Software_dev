Git Manager - Luis
Team Coordinator - Robby
Code Manager - Keenan

Issue #1: (Robby) create ga with __init__

Issue #2(Keenan, Robby): create swimmer file with class

Issue #3(Keenan, Luis, Robby): create and finish get_individual, and evaluate_fitness() add any other functions as needed

Issue #4(Keenan, Luis, Robby): create solver.py file and add example code

Issue #5: (Keenan) create default values for each ga variable

Issue #6: (Keenan, Luis, Robby): create selections_fns.py file and fill it with 3 method headers for default monte_carlo, exclude bottom 10% monte carlo, and where 1 parent is from the top 10%

Issue #7(Keenan): create default monte carlo function

Issue #8(Luis, Keenan, Robby): create a monte carlo function that excludes the bottom 10% from consideration

Issue #9(Luis, Robby): create a monte carlo function that takes one parent from the default monte carlo and 1 parent from the top 10% of the population

Issue #10(Keenan): add a plot graph to see how the generations learn over time

Issue #11(Robby): create flags to alter the number of generations, the population size, and the selection method used

Issue #12(Luis): add a way to visualize the creature using animate.py

Issue #13(Keenan): allow for the code to be used on a scheduling problem

Communication: We used google chat to communicate with each other and used excess class time to show each other problems with our code more directly. We communicated with each other every day whether it be a question about a bug in the code/how it operates, to full conversations about the structure of the code and the problems that needed solving.

Testing: Our process for testing code was to have a test individual or population of between 1-10 and to execute the methods that the solve method calls like evaluate_fitness(), or a monte_carlo method. If it returned the proper results (that we calculated beforehand) then it worked.

Tags: 
First tag:
https://gitlab.com/augsburg/csc321_s25/project-02-evolving/teamx/alvaradl-repo-project02/-/tree/tag-04-11-main?ref_type=tags

Second tag:
https://gitlab.com/augsburg/csc321_s25/project-02-evolving/teamx/alvaradl-repo-project02/-/tree/tag-04-11-add-feature-swimmer?ref_type=tags

Experiments: In our project, we evaluated the fitness of each individual by calculating the distance traveled based on the arm movements. 
             After testing different mutation strategies, we found that the most effective approach was to randomly modify one angle at a time within a range of -90 to 90 degrees. We determined the best strategy by testing the algorithm over multiple generations and tracking the improvement in the distance traveled by the swimmer. The solution that resulted in the longest travel distance was considered the best, making this mutation strategy the most effective.

Generations: When increasing the number of generations you allow for more chances to improve but after a while it becomes difficult for populations to become better which is why if you want to increase fitness as much as possible, as we have found; more than 100 generations will not drastically improve the fitness.

Population: When the population increases, not only are generations better (because there are more chances to be better) but also they take more time to calculate and will sometimes cause crashes if the population is over 100,000 per generation but better computers can probably manage more individuals.

Selection Method: When getting new generations the selection method matters almost as much as population size. If you use the general selection method you will have to rely much more on mutations because it is highly unlikely you will get a good individual.
