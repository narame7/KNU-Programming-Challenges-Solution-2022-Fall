# 전역변수로 사용할 변수

# 최대 이동 횟수
MAXMOVE = 50
# 정답을 만들기 위해 필요한 이동 횟수
MAXDEPTH = 0
# 위 오른쪽 아래 왼쪽으로 이동하게 하는 list
move = [(-1, 0), (0, 1), (1, 0), (0, -1)]
# 위 오른쪽 아래 왼쪽으로 이동했다고 출력에 사용할 것
move_str = ['U', 'R', 'D', 'L']
# 문제 해결이 되었는지 확인하는 전역변수
solved = False
# 퍼즐
puzzle = []
# 어디로 움직였는지 저장하는 list
movestack = [0 for _ in range(MAXMOVE)]

# backtraking prunning을 위해 사용하는 함수
# 아래의 계산 값이 MAXMOVE보다 큰지 계산하기위해 사용함
def cost():
    global puzzle

    row_move_cnt = 0
    col_move_cnt = 0

    # 각 퍼즐들이 원래 위치로 가기 위해서 위나 아래로 몇 칸 움직여아하는지 계산
    for i in range(4):
        for j in range(4):
            if puzzle[i][j] != 0:
                row_move_cnt += abs(i - ((puzzle[i][j]-1)//4))

    # 각 퍼즐들이 원래 위치로 가기 위해 오른쪽이나 왼쪽으로 몇 칸 움직여야하는지 계산
    for i in range(4):
        for j in range(4):
            if puzzle[i][j] != 0:
                col_move_cnt += abs(j - ((puzzle[i][j] - 1) % 4))

    return row_move_cnt + col_move_cnt

# current_depth는 현재 이동의 진행된 횟수, current_x, y는 현재 0이 있는 위치를 의미한다.
def backtrack(current_depth, current_x, current_y):
    global move
    global solved
    global puzzle
    global movestack
    global MAXDEPTH

    # 정답을 찾기위해 얼마나 이동을 해야하는지 계산
    c = cost()

    # c = 0이라는건 이미 해결이 되었다는 뜻이니까 True로 만들고 종료한다
    if c == 0:
        solved = True
        return
    # current_depth에 c를 더했을 때 MAXDEPTH보다 더 크다면 이 일을 더이상 진행할 필요가 없음
    if current_depth + c > MAXDEPTH:
        return

    # 위 오른쪽 아래 왼쪽으로 퍼즐을 이동해서 진행시키자
    for i in range(4):
        # movestack에 움직임이 저장이 되어있고(크기가 0보다 크면 무언가 저장이 된 상태)
        #이 저장된 것이 오른쪽으로 이동한 것이면 왼쪽으로는 이동하지 못하도록, 위로 이동한거면 아래로는 이동하지 못하도록 한다
        # 위로 이동했는데 아래로 움직인다면 예전 모습으로 돌아온 것이니 의미없는 행위를 하는 것이다.
        if len(movestack) > 0 and (movestack[-1] + 2) % 4 == i:
            continue
        next_x = current_x + move[i][0]
        next_y = current_y + move[i][1]

        # 다음좌표가 array를 벗어난다면 다른 이동을 진행시키기 위해 continue
        if next_x < 0 or next_x >= 4 or next_y < 0 or next_y >= 4:
            continue

        # 퍼즐을 이동시킴
        puzzle[current_x][current_y] = puzzle[next_x][next_y]
        puzzle[next_x][next_y] = 0

        # 어디로 이동했는지 저장해줌
        movestack.append(i)
        # 그 후 backtracking 진행
        backtrack(current_depth+1, next_x, next_y)

        # 위 backtrack을 하면서 문제가 해결되었다면 다른 이동이 필요없으니 return을 한다.
        if solved:
            return

        # 그렇지 않으면 movestack에 넣은 값을 빼주고 퍼즐위치도 원래대로 북구한다
        movestack.pop()
        puzzle[next_x][next_y] = puzzle[current_x][current_y]
        puzzle[current_x][current_y] = 0

def solve():
    global MAXMOVE
    global MAXDEPTH
    global puzzle

    x = 0
    y = 0
    value = 0

    # 퍼즐을 못 푸는 경우를 계산한다.
    # https://post.naver.com/viewer/postView.nhn?volumeNo=17980703&memberNo=16868720 를 참고하시길 바랍니다.
    for i in range(4):
        for j in range(4):
            if puzzle[i][j] == 0:
                value += i
                x = i
                y = j

            for k in range(i, 4):
                if k == i:
                    l = j + 1
                else:
                    l = 0

                while l < 4:
                    if puzzle[k][l] != 0 and puzzle[i][j] > puzzle[k][l]:
                        value += 1
                    l += 1

    if value % 2 == 0:
        return

    # 이 퍼즐을 풀기 위해 몇번 이동해야하는지 계산
    MAXDEPTH = cost()
    
    # 문제를 해결하지 못했고, MAXMOVE보다 MAXDEPTH가 더 작다면 backtrack 실행
    while not solved and MAXDEPTH <= MAXMOVE:
        backtrack(0, x, y)
        MAXDEPTH += 2

# 퍼즐을 풀었는지 못풀었는지 확인하는 코드
def output():
    if solved:
        for i in range(len(movestack)):
            print(move_str[movestack[i]], end='')
        print()
    else:
        print("This puzzle is not solvable.")

# testcase 입력받기
testcase = int(input())

for _ in range(testcase):

    solved = False
    movestack = []
    # puzzle 입력받기
    puzzle = []
    for _ in range(4):
        puzzle.append(list(map(int, input().split())))

    solve()
    output()
