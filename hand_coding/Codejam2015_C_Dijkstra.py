# Google Code jam 2015 QR
# Problem C. Dijkstra

# 1, i, j, k by 1, i, j, k calculate table
calTable = (('1', 'i', 'j', 'k'),
			('i', '1', 'k', 'j'), 
			('j', 'k', '1', 'i'),
			('k', 'j', 'i', '1'))
signTable = ((1, 1, 1, 1),
			(1, -1, 1, -1), 
			(1, -1, -1, 1),
			(1, 1, -1, -1))
i_v = ord('i')

def calculateIJK(l_v, r_v):
	if l_v == '1': l_v = 0
	else: l_v = ord(l_v) - i_v + 1

	if r_v == '1': r_v = 0
	else: r_v = ord(r_v) - i_v + 1

	return (calTable[l_v][r_v], signTable[l_v][r_v])

def check_i(c_f, string, last, lastSign):
	for w in string:
		last, sign = calculateIJK(last, w)
		lastSign *= sign
		c_f += 1
		if last == 'i':
			return c_f, lastSign

	return -1, lastSign

def check_j(c_f, c_b, string):
	if c_f >= c_b:
		return False, 1
	last = '1'
	lastSign = 1
	for i in range(c_f, c_b):
		last, sign = calculateIJK(last, string[i])
		lastSign *= sign

	if last == 'j': 
		return True, lastSign
	else: 
		return False, lastSign

def check_k(c_f, c_b, string, last, lastSign):
	while c_f < c_b:
		last, sign = calculateIJK(string[c_b], last)
		lastSign *= sign
		if last == 'k':
			return c_b-1, lastSign
		c_b -= 1

	return -1, lastSign

def isPossibleToConvert(l, x, string):
	string = string * x
	if l * x < 3:
		return False
	c_f, c_b = 0, (l * x) - 1
	temp_f, temp_b = 0, 0
	last_f, last_b = '1', '1'
	sign_i, sign_j, sign_k = 1, 1, 1
	f_stop, b_stop = False, False
	lastSign = 1

	while c_f <= c_b:
		#print(c_f, c_b)
		t_sign_i, t_sign_k = 1, 1
		if not f_stop:
			temp_f, t_sign_i = check_i(c_f, string, last_f, sign_i)
			if temp_f == -1:
				if last_f != 'i': 
					return False 
				f_stop = True
			else:
				last_f = 'i'
		if not b_stop:
			temp_b, t_sign_k = check_k(c_f, c_b, string, last_b, sign_k)
			if temp_b == -1:
				if last_b != 'k':
					return False
				f_stop = True
			else:
				last_b = 'k'

		if not f_stop:
			possible, sign_j = check_j(temp_f, c_b+1, string)
			if possible:
				sign_i *= t_sign_i
				break
		if not b_stop:
			possible, sign_j = check_j(c_f, temp_b, string)
			if possible:
				sign_k *= t_sign_k
				break
		if not f_stop and not b_stop:
			possible, sign_j = check_j(temp_f, temp_b, string)
			if possible:
				sign_i *= t_sign_i
				sign_k *= t_sign_k	
				break

		if f_stop and b_stop:
			break;

		if not f_stop:
			c_f = temp_f
			sign_i *= t_sign_i
		if not b_stop:
			c_b = temp_b
			sign_k *= t_sign_k

	if c_f <= c_b and sign_i * sign_j * sign_k > 0 and last_f == 'i' and last_b == 'k':
		return True

	return False


def Dijkstra():
	l, x = rl().strip().split()
	string = rl().strip()

	if(isPossibleToConvert(int(l), int(x), string)):
		return "YES"
	else:
		return "NO"

import sys

rl = lambda: sys.stdin.readline()

n = int(rl())

for i in range(n):
	print("Case #%d: %s" % (i+1, Dijkstra()))


