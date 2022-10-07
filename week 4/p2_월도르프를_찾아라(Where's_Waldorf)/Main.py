# array의 boundary보다 크거나 작지 않은지 확인
def boundary_check(n, m, temp_row, temp_col):
    if 0 <= temp_row < n and 0 <= temp_col < m:
        return True
    else:
        return False

# field에서 word가 어디있는지 확인
def check_word(field, start_row, start_col, word):
    # 왼쪽 위 대각선, 위, 오른쪽 위 대각선, 왼쪽, 오른쪽, 왼쪽 아래 대각선, 아래, 오른쪽 아래 대각선 순으로 확인
    check_position = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]

    for position in check_position:
        change_row = position[0]
        change_col = position[1]

        # 단어의 글자만큼 word가 같은지 확인
        check_count = 0

        temp_row = start_row
        temp_col = start_col

        for char in word:
            if boundary_check(len(field), len(field[0]), temp_row, temp_col):
                # 다음 포지션 (상하좌우 대각선 등등)을 확인하러감
                if char != field[temp_row][temp_col]:
                    break
                else:
                    temp_row += change_row
                    temp_col += change_col
                    check_count+=1
            else:
                # 글씨길이가 arr밖으로 삐져나간다면 break
                # 다음 포지션 (상하좌우 대각선 등등)을 확인하러감
                break

        # 단어의 글자만큼 word가 같으면 True
        if check_count==len(word):
            return True

    return False

# word의 시작 위치 찾기
def find_word_position(word, field):
    n = len(field)
    m = len(field[0])

    found = False

    for i in range(n):
        for j in range(m):

            found = check_word(field, i, j, word)

            if found:
                return i, j

    return -1, -1

testcase = int(input())

for _ in range(testcase):
    _ = input() # white space

    n, m = input().split()
    n = int(n)
    m = int(m)

    # field 생성
    field = [[] for _ in range(n)]

    for i in range(n):
        temp = input()
        for j in range(m):
            field[i].append(temp[j].lower())

    # 몇개의 단어가 들어오는지 저장
    word_num = int(input())

    for i in range(word_num):
        word = input().lower()
        row, col = find_word_position(word, field)

        print(row+1, col+1)

    print()