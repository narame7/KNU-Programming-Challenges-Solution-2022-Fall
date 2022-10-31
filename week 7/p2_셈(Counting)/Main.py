dp = [2, 5, 13]

# count에 대한 점화식, 최대 input까지 미리 만들어둔다.
for i in range(3, 1001):
    dp.append(2 * dp[i - 1] + dp[i - 2] + dp[i - 3])

try:
    while True:
        n = int(input())
        print(dp[n-1])
except:
    exit()