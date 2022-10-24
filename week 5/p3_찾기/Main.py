T = input() # Text 입력 받음
P = input() # Pattern 입력 받음

match = [] # 일치하는 인덱스 저장
k = [0 for _ in P] # k를 찾는다

# 각 자리별로 k를 찾는다.
idx = 0
for i in range(1, len(P)):
    while(idx > 0 and P[i] != P[idx]):
        idx = k[idx-1]
    if P[i] == P[idx]:
        idx += 1
        k[i] = idx

# 매칭 시작!
j = 0
for i in range(len(T)):
    while(j > 0 and T[i] != P[j]):
        j = k[j-1]
    if T[i] == P[j]:
        if j == len(P)-1:
            # 인덱스는 0번부터 시작하니까 +1해서 넣는다.
            # i-len(P)+1은 패턴 시작 인덱스
            match.append(i-len(P)+1+1)
            j = k[j]
        else:
            j += 1

# 나타난 패턴의 개수 출력
print(len(match))
# 패턴이 시작하는 위치 출력
for m in match:
    print(m, end=" ")