T = int(input()) # 테스트 케이스 개수

for _ in range(T):
    _ = input()  # 빈 줄 입력
    num = int(input())
    speed = []
    # 다리를 건너는 시간 입력받기
    for _ in range(num):
        speed.append(int(input()))
    # 다리를 건너는데 필요한 시간 계산
    # 정렬 한 후 속도가 가장 빠른 두 사람이 손전등을 들게 한다.
    speed = sorted(speed)
    time = 0
    while(len(speed) > 3):
        fast1 = speed[0]
        fast2 = speed[1]
        slow2 = speed[-2]
        slow1 = speed[-1]

        sol1 = 2*fast1 + slow2 + slow1 # 가장 빠른 한 명이 가장 느린 두 명을 데려다주는 방법
        sol2 = fast1 + 2*fast2 + slow1 # 가장 빠른 두 명과 가장 느린 두 명이 함께 움직이는 방법

        time += sol1 if sol1 < sol2 else sol2 # 두 방법 중 더 시간이 적게 걸리는 경우를 선택한다.

        # 가장 느린 두 명을 데려다줬으므로 리스트에서 제거한다.
        speed.remove(slow1)
        speed.remove(slow2)

    if len(speed) == 3: # 3명 남았다면 가장 빠른 사람이 모두를 데려다준다.
        time += sum(speed)
    else: # 2명이나 1명이 남았다면 그냥 건넌다
        time += speed[-1]

    print(time, '\n')