# Problem: Algospot, PALINDROMIZE
# Link: https://algospot.com/judge/problem/read/PALINDROMIZE
def isP(string, left, right):
    while left < right:
        if string[left] != string[right]:
            return False
        left, right = left+1, right-1
    return True

def checkMinPLength(string):
    left = 0
    right = len(string) - 1
    add = 0
    result = 0

    while left < right:
        if isP(string, left, right):
            break
        else:
            right -= 1
            add += 1
    result = len(string) + add

    add = 0
    left = 0
    right = len(string) - 1

    while left < right:
        if isP(string, left, right):
            break
        else:
            left += 1
            add += 1

    return min(result, len(string) + add)

import sys

count = int(sys.stdin.readline())

for n in range(count):
    print (checkMinPLength(sys.stdin.readline().strip()))
