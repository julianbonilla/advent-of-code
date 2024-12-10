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

# sum all the mul instructions
sum = sum([int(x) * int(y) for x, y in valid_instructions])

print('Part 1:', sum)

# Part 2 - handle 'do()' and 'don't()' instructions which enable/disable mul instructions
regex_do_dont = 'mul\\((\\d{1,3}),(\\d{1,3})\\)|(do\\(\\))||(don\'t\\(\\))'
instructions = re.findall(regex_do_dont, memory)

do = True
valid_instructions_part_2 = []
for instruct in instructions:
    if instruct[2]:
        do = True
    if instruct[3]:
        do = False
    if do and instruct[0]:
        valid_instructions_part_2.append(instruct[:2])

# sum all the mul instructions
sum = 0
for x, y in valid_instructions_part_2:
    sum += int(x) * int(y)

print('Part 2:', sum)