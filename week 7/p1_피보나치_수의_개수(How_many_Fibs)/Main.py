# how many fibos?

# input
a, b = input().split()
a = int(a)
b = int(b)

# 피보나치 수열을 저장할 배열, 피보나치수열을 1002개 정도 만들어놓자
fibo = [1, 2]
for i in range(1000):
    fibo.append(fibo[i]+fibo[i+1])

# a,b가 0일때까지 계속 수를 받는다
while a != 0 and b != 0:
    count = 0

    # fibo에 담겨있는 수 중 조건에 만족하는 수만 count
    for temp in fibo:
        if a <= temp <= b:
            count+=1

    # 결과 프린트
    print(count)

    # 반복적으로 input받기
    a, b = input().split()
    a = int(a)
    b = int(b)