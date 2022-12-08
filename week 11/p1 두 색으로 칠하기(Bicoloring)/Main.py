def BFS(graph):

    vertex_num = len(graph)

    #  색은 1과 2로 이루어져있다. 0이면 방문하지 않은 것
    color_of_vertex = [0 for _ in range(vertex_num)]

    # 현재 칠할 공간
    current_vertex = 0
    color_of_vertex[current_vertex] = 1
    queue = [0]

    # queue가 빌 때 까지 진행
    while len(queue)>0:

        current_vertex = queue.pop()
        next_color = 0

        # 현재 vertex와 색깔이 다르도록 다음 색깔을 지정
        if color_of_vertex[current_vertex] == 1:
            next_color = 2
        else:
            next_color = 1

        # 인접 vertex들의 색을 지정해주기
        for i in graph[current_vertex]:
            # 현재 vertex를 방문한적 없다면 색깔을 지정해주고 queue에 넣어주기
            if color_of_vertex[i] == 0:
                color_of_vertex[i] = next_color
                queue.append(i)
            # 방문한 적이 있다면 다음 색과 같은 지 확인. 다르다면 bicolorable하지 않은 것으로 False 반환
            else:
                if next_color != color_of_vertex[i]:
                    return False

    return True

try:
    while True:
        # 입력 받기
        vertex = int(input())
        edge = int(input())

        graph = [[] for i in range(vertex)]

        for i in range(edge):
            v1, v2 = map(int, input().split())
            graph[v1].append(v2)
            graph[v2].append(v1)

        # BFS를 통해 탐색 시작
        result = BFS(graph)

        if result:
            print("BICOLORABLE.")
        else:
            print("NOT BICOLORABLE.")


except EOFError:
    exit()