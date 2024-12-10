import itertools


"""
Reports only counts as safe if both of the following are true:

1. The levels are either all increasing or all decreasing.
2. Any two adjacent levels differ by at least one and at most three.
"""

demo_input = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
"""

# reports = [report.split() for report in demo_input.splitlines()]
reports = []

with open('day02-input.txt', 'r') as file:
    for line in file:
        # convert each report to ints
        reports.append([int(x) for x in line.split()])

# Part 1
safe_reports = 0

def valid_report(report):
    increasing = report[0] < report[1]
    valid = True

    for a, b in itertools.pairwise(report):
        level_difference = abs(a-b)
        if not (1 <= level_difference <= 3):
            valid = False
        if not ((a < b) == increasing):
            valid = False
    
    return valid

for report in reports:
    if valid_report(report):
        safe_reports += 1

print("Part 1:", safe_reports)

# Part 2 - tolerate a single bad level

safe_reports = 0

for report in reports:
    if valid_report(report):
        safe_reports += 1
    else:
        # Test for sub-lists with bad level removed
        for i, _ in enumerate(report):
            remove_level = report.copy()
            remove_level.pop(i)
            if valid_report(remove_level):
                safe_reports += 1
                break

print("Part 2:", safe_reports)