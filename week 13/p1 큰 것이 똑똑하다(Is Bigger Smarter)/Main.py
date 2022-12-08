elephants = []

# 더 짧은 sequence를 반환하는 함수
def compare_sequenc(str1, str2):
    for i, j in zip(str1, str2):
        if i>j:
            return str2
        elif j>i:
            return str1

    return str1


try:
    cnt = 1
    while True:
        weight, IQ = input().split()
        weight = int(weight)
        IQ = int(IQ)
        elephants.append((weight, IQ, cnt))
        cnt+=1

except:
    # 무게순으로 정렬
    elephants = sorted(elephants)

    # 큰 무게를 저장할 dp
    dp = [0 for _ in range(len(elephants))]
    dp[0] = 1

    # 코끼리를 추적할 dp
    tracking = [[] for _ in range(len(elephants))]
    result_tracking = []

    for i in range(1, len(elephants)):
        dp[i] = max(1, dp[i])
        tracking[i] = [elephants[i][2]]

        for j in range(i):
            # elephants[i]보다 elephants[j]의 무게가 덜나가고, 더 IQ가 높으면 진행
            if elephants[i][0] > elephants[j][0] and elephants[i][1] < elephants[j][1]:
                dp[i] = max(dp[j]+1, dp[i])

                # tracking길이가 같으면 sequence가 더 작은 쪽을 선택하도록 한다
                if len(tracking[j])+1 == len(tracking[i]):
                    tracking[i] = compare_sequenc(tracking[j]+[elephants[i][2]], tracking[i])
                elif len(tracking[j])+1 > len(tracking[i]):
                    tracking[i] = tracking[j]+[elephants[i][2]]

                # 최종 tracking이 더 짧으면 최종트랙킹을 tracking[i]로 바꿔줌
                if len(result_tracking) < len(tracking[i]):
                    result_tracking = tracking[i]
                # 만약 길이가 같다면 sequence가 더 작은 것으로 바꾸어준다
                elif len(result_tracking) == len(tracking[i]):
                    result_tracking = compare_sequenc(result_tracking, tracking[i])

    # 출력
    print(max(dp))
    for i in result_tracking:
        print(i)

    exit()
