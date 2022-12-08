m = 0; n = 0

def best_path(matrix):
    dir = [[None for _ in range(n)] for _ in range(m)] # 빈 행렬
    for r in range(m):
        dir[r][0] = (matrix[r][0], r + 1) # 시작점은 항상 첫 번째 열

    # Construct minimal weight matrix
    for c in range(1, n): # 열의 개수만큼 반복
        for r in range(m): # 행의 개수만큼 반복
            # 위, 옆, 아래 방향 중에 가장 작은 가중치를 가지는 곳을 찾는다.
            top = dir[(m + r - 1) % m][c - 1]   # 위쪽 방향으로 향할때의 가중치 계산
            mid = dir[r][c - 1]                 # 옆쪽 방향으로 향할때의 가중치 계산
            bot = dir[(r + 1) % m][c - 1]       # 아래쪽 방향으로 향할때의 가중치 계싼

            best = min(top, mid, bot)

            # 가장 작은 가중치를 가지는 방향으로 한 칸 전진
            dir[r][c] = (best[0] + matrix[r][c],) + best[1:] + (r + 1,)
    # 끝까지 도착했을 때, 가중치가 가장 적은 경로를 반환한다.
    min_path = min(dir[r][-1] for r in range(m))

    return min_path[0], min_path[1:]

while(True):
    try:
        m, n = map(int, input().split())
        matrix = []
        for _ in range(m):
            matrix.append(list(map(int, input().split())))
        weight, path = best_path(matrix)
        print(' '.join(map(str, path)))
        print(weight)
    except:
        break