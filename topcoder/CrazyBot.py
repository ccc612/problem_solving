# CrazyBot SRM

plain = [[0 for x in range(30)] for y in range(30)]

mov_x = [1, -1, 0, 0]
mov_y = [0, 0, -1, 1]
p = []

class CrazyBot:
    def getProbability(self, n, east, west, south, north):
        p.append(east/100.0)
        p.append(west/100.0)
        p.append(south/100.0)
        p.append(north/100.0)

        return self.go(n, 15, 15)

    def go(self, n, x, y):
        if plain[x][y] == 1: return 0.0

        if n == 0 : return 1.0

        plain[x][y] = 1

        sum = 0.0

        for i in range(0,4):
            sum += p[i] * self.go(n-1, x + mov_x[i], y + mov_y[i])

        plain[x][y] = 0
        return sum
