# Hartals

testCase = input()
testCase = int(testCase)

# testCase만큼 반복
for i in range(testCase):
    day = input()
    day = int(day)

    classNum = input()
    classNum = int(classNum)

    classList = []

    hatal = 0

    # 각 정당의 쉬는 날을 list에 저장
    for j in range(classNum):
        temp = input()
        temp = int(temp)

        classList.append(temp)

    # 각 날마다 정당마다 쉬는 날인지 확인
    for j in range(1, day+1):
        if j%7 == 6 or j%7 == 0:
            continue

        for k in classList:
            if j%k==0:
                hatal += 1
                break


    print(hatal)
