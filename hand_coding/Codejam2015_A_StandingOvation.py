# Google Code jam 2015 QR
# Problem A. Standing Ovation 
# https://code.google.com/codejam/contest/dashboard?c=6224486

def getMinFriends(maxS, pInLevel):
	standing = int(pInLevel[0])
	result = 0

	for level in range(1,int(maxS)+1):
		temp = 0
		if standing < level:
			temp = level - standing
#		print("level %d, standing %d, temp %d, result %d" % (level, standing, temp, result))
		result += temp
		standing += int(pInLevel[level]) + temp 
	return result

def standingOvation(inputString):
	inputs = inputString.split()
	return getMinFriends(inputs[0], inputs[1])

import sys

rl = lambda: sys.stdin.readline()

n = int(rl())

for i in range(n):
	print("Case #%d: %d" % (i+1, standingOvation(rl().strip())))


