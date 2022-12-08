turtle = []
try:
    while True:
        weight, strength = input().split()
        weight = int(weight)
        strength = int(strength)
        # (체력, 몸무게)를 tutle에 저장해주자
        turtle.append((strength, weight))

except:
    # 체력순으로 정렬
    turtle = sorted(turtle)

    # 무게를 저장하는 dp, dp[n]은 n개의 거북이들의 최소 무게이다
    dp = [float('inf') for _ in range(len(turtle))]
    dp[0] = 0
    # 가장 높이 쌓을 수 있는 거북이 수
    max_turtle = 0

    for i in range(len(turtle)):
        for j in reversed(range(max_turtle+1)):
            # i번째 거북이는 자신의 무게와 j마리의 거북이를 들 수 있어야하고,
            # j+1번째 거북이를 위해 총 무게를 최대한 줄여야한다
            if turtle[i][0] >= dp[j]+turtle[i][1] and turtle[i][1]+dp[j] < dp[j+1]:
                dp[j+1] = '''코드 작성'''
                max_turtle = max(max_turtle, j+1)

    print(max_turtle)
    exit()
