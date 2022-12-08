# 모든 주근깨가 연결되어있다고 생각하고
# 그 중 최단거리를 찾는다.

# 전역 변수 선언
n = 0       # 주근깨(노드) 개수
visit = []  # 방문한 노드인지
dist = []   # 최소 거리

# 연결된 노드에서 가장 적은 잉크를 가지고 갈 수 있는 노드를 찾는다.
def get_min():
    global n, visit, dist
    for i in range(n):      # 노드의 개수만큼 반복
        # 방문하지 않은 노드를 하나 찾는다.
        if not visit[i]:
            v = i
            break
    # 방문하지 않은 노드들 중 가장 적은 거리를 가지는 노드를 찾는다.
    for i in range(n):
        if not visit[i] and dist[i] < dist[v]:
            v = i
    return v

# 최소한의 거리(잉크)를 찾는다.
def find(graph):
    global n, visit, dist
    visit = [False for _ in range(n)]       # 방문한 노드인지 아닌지 확인하는 배열
    dist = [float('inf') for _ in range(n)] # 거리(잉크)
    dist[0] = 0     # 0번째 주근깨에서부터 시작
    # 노드 개수만큼 반복
    for i in range(n):
        # 현재 연결된 노드에서 갈 수 있는 가장 가까운 노드를 찾는다.
        node = get_min()
        visit[node] = True
        # 노드 개수만큼 반복
        for j in range(n):
            # 만약 지금 노드가 방문하지 않은 노드이고 현재 거리보다 짧은 거리라면
            if not visit[j] and graph[node][j] < dist[j]:
                # 현재 거리를 더 짧은 거리로 업데이트 해준다.
                dist[j] = graph[node][j]

T = int(input())    # 테스트 케이스 개수
# 테스트 케이스 개수만큼 반복한다.
for _ in range(T):
    _ = input()     # 빈 줄 입력
    n = int(input())    # 주근깨 개수
    freckles = {}   # 어느 위치에 있는 주근깨가 몇 번인지
    graph = [[0 for _ in range(n)] for _ in range(n)] # nxn 크기의 그래프
    # 주근깨 좌표를 입력받는다.
    for i in range(n):
        x, y = map(float, input().split())
        freckles[i] = (x, y)
        # 지금까지 입력된 주근깨 사이의 거리를 계산한다.
        for j in range(i):
            if i == j:
                continue
            else:
                # 두 주근깨 사이에 거리를 계산해서 소수점 둘째 자리까지
                d = ((freckles[i][0]-freckles[j][0])**2+(freckles[i][1]-freckles[j][1])**2)**0.5
                graph[i][j] = d
                graph[j][i] = d
    find(graph)
    # 소숫점 둘째자리까지 출력
    print("{:.2f}".format(sum(dist)))
    print()