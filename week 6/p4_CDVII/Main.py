# CDVII

def calcuate_cost(car_list, cost):
    isStartEnter = True # 리스트의 다음 사진에 enter가 있어야 하는지 exit가 있어야 하는지에 대한 정보를 담을 변수
    hasPair = False     # 특정 차량이 최소 한쌍의 enter/exit 짝을 가졌는지에 대한 정보를 담을 변수

    tempInfo = []           # 읽어온 줄이 enter에 대한 정보인지 exit에 대한 정보인지 판별 전에 임시로 정보를 저장할 배열
    enterInfo = []          # enter에 대한 정보를 저장할 배열
    exitInfo = []           # exit에 대한 정보를 저장할 배열
    current_car = ""     # 현재 처리중인 차 번호판

    total_bill = 2.0        # 총 이용 요금을 저장할 변수(default 2달러)

    for car in car_list:
        tempInfo = car.split()    # 차 번호판, 시간, enter or exit, 위치

        # 차 번호판 구분
        # 현재 처리중인 차 번호판이 없으면 tempInfo에서 번호판 정보를 읽어 저장
        if current_car == "":
            current_car = tempInfo[0]
        # 현재 처리중인 번호판에서 다른 번호판으로 대상이 바뀔 때
        elif current_car != tempInfo[0]:
            # 요금 계산 중이던 차의 이용 요금 출력 및 요금 초기화
            # hasPair를 통해 최소한 짝이 한 개가 있는지 검사한다
            if hasPair:
                print(current_car+" $", end='')
                print('{:.2f}'.format(total_bill))
                total_bill=2.0
                hasPair=False

            # 현재 처리중인 번호판 갱신 및 짝 검사용 변수 초기화
            current_car = tempInfo[0]
            isStartEnter = True

        # enter, exit 짝 구분
        if isStartEnter and tempInfo[2]=='enter':
            enterInfo = tempInfo.copy()
            isStartEnter = False
            continue

        # 최소한 짝이 한 쌍이라도 있는 경우에만 이 elif 구문을 통과함
        elif not isStartEnter and tempInfo[2] == 'exit':
            exitInfo = tempInfo.copy()
            isStartEnter = True
            hasPair = True

        # 짝이 잘못됐을 경우엔 검사용 변수 초기화
        else:
            isStartEnter = True
            continue

        # 킬로미터 수를 계산해서 더함
        start_time = int(enterInfo[1].split(":")[2])
        km = abs(int(enterInfo[3]) - int(exitInfo[3]))
        total_bill += km*cost[start_time]/100.0 + 1.0

    # while문을 빠져나오면 아직 출력하지 못한 리스트 마지막 차의 요금 출력
    # hasPair를 통해 최소한 짝이 하나는 있는지 검사함
    if hasPair:
        print(current_car + " $", end='')
        print('{:.2f}'.format(total_bill))
    print()

# testcase 수 입력받기
testcase = int(input())
_ = input()  # white space

for _ in range(testcase):

    # 시간당 가격 저장
    cost = input().split()
    cost = [int(i) for i in cost]

    s = None
    car_list = []
    try:
        # 자동차들을 car_list에 저장
        while True:
            s = input()
            if(s == ''):
                break
            car_list.append(s)

        # 자동차 이름으로 sort()후 가격 계산 후 출력
        car_list.sort()
        calcuate_cost(car_list, cost)
    except:
        car_list.sort()
        calcuate_cost(car_list, cost)
        break

