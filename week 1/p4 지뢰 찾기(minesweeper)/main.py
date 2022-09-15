# 지뢰찾기

# 지뢰판의 범위를 벗어났는지 확인하는 함수
def rangeCheck(num, boundary):
    if -1 < num < boundary:
        return True
    else:
        return False

# 그 칸을 기준으로 지뢰가 몇 개 있는지 확인하는 함수
def count(result, row, col, m, n):
    for i in range(-1, 2):
        for j in range(-1, 2):
            tempRow = row + i
            tempCol = col + j
            if (rangeCheck(tempRow, m) and rangeCheck(tempCol, n)):
                result[tempRow][tempCol] += 1


gameCount = 0;

# input
m, n = input().split()
m = int(m)
n = int(n)

while m!=0 and n!=0:
    gameCount += 1

    # 지뢰판 저장
    field = []
    for i in range(m):
        field.append(input())

    # 초기 array설정은 0으로 한다(편한대로)
    result = []
    for i in range(m):
        result.append([])
        for j in range(n):
            result[i].append(0)

    # 지뢰 개수 저장하기
    for i in range(m):
        for j in range(n):
            if field[i][j] == '*':
                count(result, i, j, m, n)

    # 출력
    print("Field #{}:".format(gameCount))
    for i in range(m):
        for j in range(n):
            if field[i][j] == '*':
                print('*', end='')
            else:
                print(result[i][j], end='')
        print()

    print()

    m, n = input().split()
    m = int(m)
    n = int(n)