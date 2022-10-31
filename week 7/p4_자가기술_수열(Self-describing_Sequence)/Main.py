input_N = []
while True:
    # 입력을 받는다.
    test_case = int(input())
    if test_case == 0:
        break
    else:
        input_N.append(test_case)
f_N = [0] # f 함수
# 1부터 가장 큰 입력까지 for문을 돌려서 f(n)의 값을 저장한다.
for n in range(1, max(input_N)+1):
    # 1은 1번, 2는 2번 들어간다.
    if n == 1 or n == 2:
        for _ in range(n):
            f_N.append(n)
        continue
    # 만약 1이나 2가 아니라면 f 함수의 값에 따라 해당 수를 넣어준다.
    for _ in range(f_N[n]):
        f_N.append(n)
        # f 함수의 길이가 가장 큰 수보다 크다면
        # 더 이상 함수의 값을 계산할 필요 없다.
        if len(f_N) > max(input_N):
            break

# f(n)의 값을 출력해준다.
for i in input_N:
    print(f_N[i])