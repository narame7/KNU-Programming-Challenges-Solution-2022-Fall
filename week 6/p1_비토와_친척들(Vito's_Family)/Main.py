T = int(input()) # 테스트 케이스 개수

for _ in range(T):
    house = list(map(int, input().split())) # 친척집들의 위치
    # 정렬해서 가운데 있는 수를 찾는다.
    count = house[0] # 총 친척 집의 개수
    house = sorted(house[1:]) # 친척집 번지수
    vito = house[count//2] # 가운데에 있는 집으로 결정
    distance = 0 # 거리 계산
    for i in house:
        distance += abs(i - vito)
    print(distance)