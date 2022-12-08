# 최소 회전을 구하는 함수
def DFS(current_state, final_state, graph):

    # DFS를 하기위해 사용할 queue들
    queue = [current_state]

    while len(queue)!=0:
        n1, n2, n3, n4 = queue.pop(0)

        # 현재 상태와 최종 상태가 같다면 지금가지 계산한 횟수를 return
        if [n1, n2, n3, n4] == final_state:
            return graph[n1][n2][n3][n4]

        # 숫자가 4개이기 떄문에 4개를 각각 돌려줘야함

        # 천의 자리 수를 오른쪽 버튼을 눌러서 1 증가시킨다.
        if -1 < graph[(n1+1)%10][n2][n3][n4] < 1 and [(n1+1)%10, n2, n3, n4] not in queue:
            graph[(n1+1)%10][n2][n3][n4] = graph[n1][n2][n3][n4] + 1
            queue.append([(n1+1)%10, n2, n3, n4])
        # 천의 자리 수를 왼쪽 버튼을 눌러서 1 감소시킨다.
        if -1 < graph[(n1-1)%10][n2][n3][n4] < 1 and [(n1-1)%10, n2, n3, n4] not in queue:
            queue.append([(n1-1)%10, n2, n3, n4])
            graph[(n1-1)%10][n2][n3][n4] = graph[n1][n2][n3][n4] + 1

        # 백의 자리 수를 오른쪽 버튼을 눌러서 1 증가시킨다.
        if -1 < graph[n1][(n2+1)%10][n3][n4] < 1 and [n1, (n2+1)%10, n3, n4] not in queue:
            queue.append([n1, (n2+1)%10, n3, n4])
            graph[n1][(n2 + 1) % 10][n3][n4] = graph[n1][n2][n3][n4] + 1
        # 백의 자리 수를 왼쪽 버튼을 눌러서 1 감소시킨다.
        if -1 < graph[n1][(n2-1)%10][n3][n4] < 1 and [n1, (n2-1)%10, n3, n4] not in queue:
            queue.append([n1, (n2-1)%10, n3, n4])
            graph[n1][(n2-1)%10][n3][n4] = graph[n1][n2][n3][n4] + 1

        # 십의 자리 수를 오른쪽 버튼을 눌러서 1 증가시킨다.
        if -1 < graph[n1][n2][(n3+1)%10][n4] < 1 and [n1, n2, (n3+1)%10, n4] not in queue:
            queue.append([n1, n2, (n3+1)%10, n4])
            graph[n1][n2][(n3+1)%10][n4] = graph[n1][n2][n3][n4] + 1
        # 십의 자리 수를 왼쪽 버튼을 눌러서 1 감소시킨다.
        if -1 < graph[n1][n2][(n3-1)%10][n4] < 1 and [n1, n2, (n3-1)%10, n4] not in queue:
            queue.append([n1, n2, (n3-1)%10, n4])
            graph[n1][n2][(n3-1)%10][n4] = graph[n1][n2][n3][n4] + 1

        # 일의 자리 수를 오른쪽 버튼을 눌러서 1 증가시킨다.
        if -1 < graph[n1][n2][n3][(n4+1)%10] < 1 and [n1, n2, n3, (n4+1)%10] not in queue:
            queue.append([n1, n2, n3, (n4+1)%10])
            graph[n1][n2][n3][(n4+1)%10] = graph[n1][n2][n3][n4] + 1
        # 일의 자리 수를 왼쪽 버튼을 눌러서 1 감소시킨다.
        if -1 < graph[n1][n2][n3][(n4-1)%10] < 1 and [n1, n2, n3, (n4-1)%10] not in queue:
            queue.append([n1, n2, n3, (n4-1)%10])
            graph[n1][n2][n3][(n4-1)%10] = graph[n1][n2][n3][n4] + 1


    # DFS를 끝날때까지 최종 상태로 가지 못했기 때문에 -1를 반환함
    return -1

# input
testcase = int(input())

for _ in range(testcase):

    _ = input()  # white space

    start_state = list(map(int, input().split()))
    final_state = list(map(int, input().split()))
    forbid = []

    n = int(input())

    # 4차원 graph를 만들어준다.
    # 0726이면 graph[0][7][2][6]처럼 표현할 수 있다.
    graph = [[[[0 for _ in range(10)] for _ in range(10)] for _ in range(10)] for _ in range(10)]


    for _ in range(n):
        n1, n2, n3, n4 = map(int, input().split())
        # 가면 안되는 곳은 -1로 채워준다.
        graph[n1][n2][n3][n4] = -1

    cnt = DFS(start_state, final_state, graph)

    print(cnt)
