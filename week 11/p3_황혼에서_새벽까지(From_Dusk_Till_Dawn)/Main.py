n = 0; e = 0; ne = 0; start = 0; finish = 0; rechable = False; front = 0; rear = 0;
edges = [[0, 0, 0, 0] for i in range(1000)]
check = [[0, 0] for i in range(1000)]
city = [0 for i in range(100)]
queue = [0 for i in range(10000)]

def bfs():
    global front, rear, rechable

    now = [0, 0, 0]
    front = 0
    rear = 0
    rechable = False
    for i in range(n):
        check[i][0] = 10000

    queue[rear] = start
    check[start][0] = 0
    check[start][1] = 0
    rear += 1

    while(front < rear):
        now[0] = queue[front]
        now[1] = check[now[0]][0]
        now[2] = check[now[0]][1]
        front += 1

        if now[0] == finish:
            rechable = True
            continue

        for i in range(ne):
            if edges[i][0] != now[0]:
                continue
            if now[2] <= edges[i][2] and (check[edges[i][1]][0] > now[1] or (check[edges[i][1]][0] == now[1] and check[edges[i][1]][1] > edges[i][2] + edges[i][3])):
                queue[rear] = edges[i][1]
                check[edges[i][1]][0] = now[1]
                check[edges[i][1]][1] = edges[i][2] + edges[i][3]
                rear += 1
            elif check[edges[i][1]][0] > now[1] + 1 or (check[edges[i][1]][0] == now[1] + 1 and check[edges[i][1]][1] > edges[i][2] + edges[i][3]):
                queue[rear] = edges[i][1]
                check[edges[i][1]][0] = now[1] + 1
                check[edges[i][1]][1] = edges[i][2] + edges[i][3]
                rear += 1

def get_city(name):
    global n
    for i in range(n):
        if name == city[i]:
            return i
    city[n] = name
    n += 1
    return n - 1

if __name__ == "__main__":
    T = int(input()) # 테스트 케이스 개수
    for t in range(1, T+1):
        n = 0; ne = 0
        e = int(input())
        for i in range(e):
            city1, city2, r1, r2 = input().split()
            r1 = int(r1)
            r2 = int(r2)
            r1 %= 24

            if (r1 >= 6 and r1 < 18) or (r1 < 6 and r2 > 6 - r1) or (r1 >= 18 and r2 > 30 - r1):
                continue

            edges[ne][0] = get_city(city1)
            edges[ne][1] = get_city(city2)
            edges[ne][2] = (r1 + 12) % 24
            edges[ne][3] = r2
            ne += 1
        city1, city2 = input().split()
        start = get_city(city1)
        finish = get_city(city2)

        bfs()

        print(f"Test Case {t}.")

        if rechable:
            print(f"Vladimir needs {check[finish][0]} litre(s) of blood.")
        else:
            print("There is no route Vladimir can take.")