class KiwiJuiceEasy:
	def thePouring(self, capacities, bottles, fromId, toId):
		bottles = list(bottles)
		for i in range(0,len(fromId)):
			remainTo = capacities[toId[i]] - bottles[toId[i]]
		    # from bottle empty
			if bottles[fromId[i]] < remainTo:
				bottles[toId[i]] += bottles[fromId[i]]
				bottles[fromId[i]] = 0
			else: # to bottle full
				bottles[fromId[i]] -= remainTo
				bottles[toId[i]] = capacities[toId[i]]
		return bottles