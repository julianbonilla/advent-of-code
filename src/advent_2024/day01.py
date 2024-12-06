"""
Pair up the smallest number in the left list with the smallest number in the right list,
then the second-smallest left number with the second-smallest right number, and so on.
Within each pair, figure out how far apart the two numbers are; 
you'll need to add up all of those distances.
"""

demo_input = [
    [79440, 44767, 86871, 66575, 26386],
    [47531, 73309, 26386, 90774, 86871]
]

# Initialize the two location lists
locations = [[],[]]

with open('day01-input.txt', 'r') as file:
    for line in file:
        # Remove trailing newline and split the line
        a, b = line.rstrip().split('   ')
        # Add each location to the location lists
        locations[0].append(int(a))
        locations[1].append(int(b))

location_list_a = sorted(locations[0])
location_list_b = sorted(locations[1])

# Part 1
# The sum of distances between smallest pairs
total_distance = 0

for index, value in enumerate(location_list_a):
    total_distance += abs(value - location_list_b[index])

print(f"Part 1: {total_distance}")

# Part 2
# Figure out exactly how often each number from the left list appears in the right list. 
# Calculate a total similarity score by adding up each number in the left list 
# after multiplying it by the number of times that number appears in the right list.

demo_input2 = [
    [3, 4, 2, 1, 3, 3],
    [4, 3, 5, 3, 9, 3]
]

similarity_score = 0

# Build a frequency list for the right list
frequecy_list = {}
for x in location_list_b:
    if x in frequecy_list:
        frequecy_list[x] += 1
    else:
        frequecy_list[x] = 1

# Calculate the similarity score
for x in location_list_a:
    if x in frequecy_list:
        similarity_score += x * frequecy_list[x]

print(f"Part 2: {similarity_score}")