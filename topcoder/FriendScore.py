class FriendScore:
    def highestScore(self, friends):
        hScore = 0
        Len = len(friends)
        for i in range(Len):
            friend_2 = list(friends[i])
            for j in range(Len):
                if friend_2[j] == 'N' and j != i:
                    for k in range(Len):
                        if friends[k][i] == 'Y' and friends[k][j] == 'Y':
                            friend_2[j] = 'Y'
            hScore = max(hScore, "".join(friend_2).count('Y'))
        return hScore