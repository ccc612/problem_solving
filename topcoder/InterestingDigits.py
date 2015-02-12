class InterestingDigits:
	def checkNum(self, num, base):
		idx = 2
		tNum = num * idx
		while tNum < base**3:
			checkNum = tNum // base**2 
			checkNum += (tNum % base**2) // base 
			checkNum += tNum % base
			if checkNum % num != 0:
				return False
			idx += 1
			tNum = num * idx
		return True
	
	def digits(self, base):
		iDigits = list()
		for num in range(2,base):
			if self.checkNum(num, base):
				iDigits.append(num)
		return iDigits