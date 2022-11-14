# 입력 받을 n과 k를 전역변수로 선언해준다.
n = 0; k = 0

# 해당 위치에 비숍을 놓을 수 있는지 확인하는 함수
# bishop: 비솝이 놓인 좌표 리스트
# i: 비숍을 놓을 좌표
# j: 비숍을 놓을 좌표
def is_able(bishop, i, j):
    # 아직 비숍이 하나도 놓이지 않았다면
    # 어느 자리든 놓을 수 있으므로 True 반환
    if len(bishop) == 0:
        return True
    # 비숍들이 놓인 좌표와 현재 좌표를 비교해본다.
    for coor in bishop:
        # 두 점의 x축 거리와 y축의 거리가 같다면 대각선 방향에 있는 것이므로
        # False 반환
        if abs(coor[0] - i) == abs(coor[1] - j):
            return False
    return True

# 백트래킹을 수행할 함수
# bishop: 비숍이 놓인 좌표 리스트
# count: 비숍을 놓을 수 있는 개수
# curr: 현재 체스판에서의 좌표
def find(bishop, count, curr):
    # 비숍을 k개 놓았다면
    if len(bishop) == k:
        # 비숍을 다 놓은 것이므로 (지금까지 비숍을 놓은 경우의 수 + 1) 반환
        return count + 1
    # 체스판 탐색 시작
    for i in range(n):
        # 현재 자리 이전의 지나쳐온 자리들은 탐색할 필요 없다
        if i < curr[0]:
            continue
        for j in range(n):
            if i == curr[0] and j < curr[1]:
                continue
            # 비숍을 놓을 수 있는 자리이면
            if is_able(bishop, i, j):
                # 현재 좌표에 비숍을 놔준다
                bishop.append((i, j))
            # 비솝을 놓을 수 없는 자리면
            else:
                # 그 다음 좌표로 넘어간다.
                continue
            # 다음 비숍을 놓을 자리를 탐색한다.
            count = find(bishop, count, (i, j))
            # 지금 상태에서 탐색이 다 끝났으면 다음 비숍을 놓기 위해 좌표를 비워준다.
            bishop.remove((i, j))
    return count

# 입력을 받는다
while True:
    n, k = map(int, input().split())
    # n과 k가 둘 다 0이면
    if n == k == 0:
        # 멈춘다.
        break
    # 비숍의 개수를 찾아서 출력한다.
    print(find([], 0, (0, 0)))