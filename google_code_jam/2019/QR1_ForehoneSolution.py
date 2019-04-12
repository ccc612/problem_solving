# Google Code Jam 2019 QR Problem.1
# Problem Link: https://codingcompetitions.withgoogle.com/codejam/round/0000000000051705/0000000000088231

def divide_four(num):
    num1 = ''
    num2 = ''

    for ch in num:
        if ch is '4':
            num1 += '2'
            num2 += '2'
        else:
            num1 += ch
            if len(num2) != 0:
                num2 += '0'
    return num1 + ' ' + num2

case = int(input())
for c in range(1, case + 1):
    print('Case #{}: {}'.format(c, divide_four(input())))
