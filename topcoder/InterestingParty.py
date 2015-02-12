class InterestingParty:
	def countHobby(self, hobbyCnt, hobbyList):
		for hobby in hobbyList:
			if hobbyCnt.__contains__(hobby):
				hobbyCnt[hobby] += 1
			else:
				hobbyCnt[hobby] = 1
	
	def bestInvitation(self, first, second):
		hobbyCnt = dict()
		self.countHobby(hobbyCnt, first)
		self.countHobby(hobbyCnt, second)
		
		maxCnt = 0
		for key, value in hobbyCnt.items():
			maxCnt = max(maxCnt, value)
		
		return maxCnt