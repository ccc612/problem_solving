class Cryptography:
	def encrypt(self, numbers):
		numList= sorted(list(numbers))
		numList[0] += 1
		mul = reduce((lambda x, y: x * y), numList)
		return mul
