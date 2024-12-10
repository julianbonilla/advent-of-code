import re

"""
Scan the corrupted memory for uncorrupted mul instructions.
What do you get if you add up all of the results of the multiplications?
Valid instructions are mul(X,Y), where X and Y are each 1-3 digit numbers
"""

demo_input = [
    'mul(44,46)', 'mul(123,4)', 'mul (1 , 2 )'
]

memory = ''

with open('day03-input.txt', 'r') as file:
    # Get a continuous line of memory
    memory = file.read().replace('\n', '')

# Capture the digits to apply multiplication
regex = 'mul\\((\\d{1,3}),(\\d{1,3})\\)'
valid_instructions = re.findall(regex, memory)

# print(valid_instructions)

# sum all the mul instructions
sum = sum([int(x) * int(y) for x, y in valid_instructions])

print('Part 1:', sum)