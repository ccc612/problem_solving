class ThePalindrome:
	def find(self, s):
		if len(s) == 1: return 1
		
		for idx, char in enumerate(s):
			if self.isP(s[idx:]):
				return len(s) + idx
		return len(s) * 2
		
	def isP(self, s):
		if len(s) == 0: return False
		
		f, b = 0, len(s) - 1
		while f < b:
			if s[f] != s[b]:
				return False
			f+=1
			b-=1
		return True