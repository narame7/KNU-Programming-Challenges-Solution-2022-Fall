input_Ns = [] # 입력받는 모든 N
try:
    while True:
        # 입력을 받는다.
        input_Ns.append(int(input())) # 옮겨야 할 원반 수
except:
    # 횟수 계산
    hanoi3 = [0] # 막대가 3개인 하노이탑 횟수 = 2^n - 1
    for i in range(1, max(input_Ns)+1):
        hanoi3.append(2**i - 1)

    hanoi4 = [0] # 막대가 4개인 하노이탑 횟수
    for N in range(1, max(input_Ns)+1):
        min_count = float('inf')
        # 만약 N이 1이라면 원판을 옮기는 횟수는 무조건 1이다.
        if N == 1:
            hanoi4.append(1)
            continue
        # 최적의 k를 찾아서 계산한다.
        for k in range(1, N):
            # 먼저 k개를 한 막대로 옮기고 남은 N-k개를 다른 막대로 옮기고 다시 k개를 N-k개 위로 옮긴다.
            # 먼저 k개를 옮길 땐 막대 4개에서 k개를 옮기는 횟수와 같고
            # 남은 N-k개를 옮길 땐 막대 3개에서 N-k개를 옮기는 횟수와 같다.
            # 마지막으로 처음에 옮겨두었던 k개를 N-k개 위로 옮길땐 처음과 같이 막대 4개에서 k개를 옮기는 횟수와 같다.
            # k를 1부터 N-1까지의 수로 바꿔주면서 횟수가 가장 적게 걸리는 k를 찾아서 옮겨준다.
            min_count = min(min_count, hanoi4[k]*2 + hanoi3[N-k])
        hanoi4.append(min_count) # 가장 적은 횟수

    for i in input_Ns:
        print(hanoi4[i])