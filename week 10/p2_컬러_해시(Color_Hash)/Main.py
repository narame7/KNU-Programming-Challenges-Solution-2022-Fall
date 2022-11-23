from collections import deque

# final state
FINAL_STATE = (0, 3, 4, 3, 0, 5, 6, 5, 0, 1, 2, 1, 0, 7, 8, 7, 0, 9, 10, 9, 0, 1, 2, 1)

# 미리 만들어놓을 정답 깊이
CACHE_DEPTH = 8
# 만들어야 할 정답 깊이
MOVE_LIMIT = 16 - CACHE_DEPTH

REVERSE_MOVE_TABLE = [0, 3, 4, 1, 2]

# 왼쪽 바퀴를 시계방향으로 돌린다.
def turn1(puzzle):
    p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23 = puzzle
    return (p22, p23, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p12, p13, p14, p15, p16, p17, p18, p19, p20, p7, p8, p9)

# 오른쪽 바퀴를 시계방향으로 돌린다.
def turn2(puzzle):
    p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23 = puzzle
    return (p0, p1, p2, p3, p4, p5, p6, p7, p8, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p12, p13)

# 왼쪽 바퀴를 반시계방향으로 돌린다.
def turn3(puzzle):
    p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23 = puzzle
    return (p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p0, p1, p12, p13, p14, p15, p16, p17, p18, p19, p20, p11, p0, p1)

# 오른쪽 바퀴를 반시계방향으로 돌린다.
def turn4(puzzle):
    p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23 = puzzle
    return (p0, p1, p2, p3, p4, p5, p6, p7, p8, p19, p20, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21)

# 미리 정답에 도달할 수 있는 후보들을 만들어놓는다.
def generate_candidates(puzzle, moves):
    candidates = []
    # 이전 움직임을 가져온다. 없으면 0
    prev_move = moves[-1] if moves else 0

    # 이전에 돌렸던 방향과 반대방향으로 돌리는 것은 의미가 없다.
    if prev_move != 3:
        candidates.append((turn1(puzzle), moves + (1,)))
    if prev_move != 4:
        candidates.append((turn2(puzzle), moves + (2,)))
    if prev_move != 1:
        candidates.append((turn3(puzzle), moves + (3,)))
    if prev_move != 2:
        candidates.append((turn4(puzzle), moves + (4,)))

    return candidates

# 미리 만들어놓은 것과 지금의 길을 확인
def store_in_cache(cache, puzzle, moves):
    # 지금 puzzle이 이미 저장되어있는 길이고 더 짧은 경로를 가지고 있다면
    if puzzle in cache and len(cache[puzzle]) <= len(moves):
        pass # 계속 한다.
    else:
        cache[puzzle] = moves

def generate_solution_cache(puzzle, depth):
    cache = {}
    stack = generate_candidates(puzzle, ())

    while stack:
        puzzle, moves = stack.pop()
        store_in_cache(cache, puzzle, moves)
        if len(moves) < depth:
            stack.extend(generate_candidates(puzzle, moves))

    return cache

# 뒤집어준다.
def reverse_moves(moves):
    return tuple(REVERSE_MOVE_TABLE[m] for m in moves)

# 퍼즐을 푼다.
def solve(cache, puzzle, moves=()):
    # 이미 만들어진것이라면
    if puzzle in cache:
        return list(moves) + list(reversed(reverse_moves(cache[puzzle])))

    fifo = deque(generate_candidates(puzzle, ()))

    while fifo:
        puzzle, moves = fifo.popleft()
        if puzzle in cache:
            return list(moves) + list(reversed(list(reverse_moves(cache[puzzle]))))
        if len(moves) < MOVE_LIMIT:
            fifo.extend(generate_candidates(puzzle, moves))

    return None

# 시작
if __name__ == '__main__':
    ncases = int(input())
    cache = generate_solution_cache(FINAL_STATE, CACHE_DEPTH)
    # 테스트케이스 개수만큼 반복한다.
    for c in range(ncases):
        puzzle = tuple(map(int, input().split()))
        
        # 들어온 숫자가 이미 final state라면
        if puzzle == FINAL_STATE:
            # 이미 풀린 퍼즐
            print('PUZZLE ALREADY SOLVED')
        # final state가 아니라면
        else:
            # 퍼즐을 푼다.
            solution = solve(cache, puzzle)
            # solution을 찾지 못하면
            if not solution:
                # 찾을 수 없다고 출력
                print('NO SOLUTION WAS FOUND IN 16 STEPS')
            # solution을 찾았다면
            else:
                # solution 출력
                print("".join(map(str, solution)))