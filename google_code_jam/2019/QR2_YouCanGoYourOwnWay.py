# Problem Link: https://codingcompetitions.withgoogle.com/codejam/round/0000000000051705/00000000000881da

import sys

conv_map = {'S': 'E', 'E': 'S'}
def conv(ch):
    if conv_map.get(ch):
        return conv_map[ch]
    else:
        return ''


n_cases = int(sys.stdin.readline())

for n in range(n_cases):
    # just pass map size
    sys.stdin.readline()

    path = list(sys.stdin.readline())
    print('Case #' + str(n + 1) + ': ' + ''.join(map(conv, path)))

