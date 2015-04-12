# Google Code jam 2015 QR
# Problem B. Infinite House of Pancakes
# https://code.google.com/codejam/contest/dashboard?c=6224486

def getMinBreakfast(d, p):
	p = sorted(p, reverse=True)
	minTime = p[0]
	spTime = 0

	while True:
		print(p, spTime)
		org = p[0]
		if org < 4: break
		for n in range(len(p)):
			if p[n] == org:
				p[n] = (org / 2) + (org % 2)
				p.append(org / 2)
				spTime += 1

		p = sorted(p, reverse=True)
		if minTime >= (p[0] + spTime):
			minTime = p[0] + spTime
		else:
			break

	return minTime



def infiniteHouseOfPancakes():
	d = int(rl().strip())
	p = map(int, rl().strip().split())
	print(d)
	return getMinBreakfast(d, p)

import sys

rl = lambda: sys.stdin.readline()

n = int(rl())

for i in range(n):
	print("Case #%d: %d" % (i+1, infiniteHouseOfPancakes()))


