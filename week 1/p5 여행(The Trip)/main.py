# n이 입력으로 주어지고, 달러와 센트가 들어옴 마지막줄에 00이 들어옴
import math

n = input()
n = int(n)
result = []

while n != 0:

    student = []

    # 사용된 요금 저장
    for i in range(n):
        dol = input()
        dol = float(dol)

        student.append(dol)

    # 사용한 요금 평균
    avg_expense = sum(student)/n

    positive_spend=0
    negative_spend=0

    # 사용한 요금 양수는 양수끼리 음수는 음수끼리 저장
    for i in student:
        temp = i - avg_expense
        if temp>0:
            positive_spend += temp
        else:
            negative_spend += temp

    # 사용된 요금이 만약 다르다면! 작은 값을 기준으로 1/n하기
    min_spend = min(positive_spend, abs(negative_spend))

    # 소수점 둘째자리까지만 출력되도록 하는 과정
    min_spend = min_spend*100
    min_spend = math.trunc(min_spend)
    min_spend = min_spend/100
    result.append(min_spend)

    n = input()
    n = int(n)


# 출력
for i, spend in enumerate(result):
    if i+1==len(result):
        print("${:.2f}".format(spend), end='')
    else:
        print("${:.2f}".format(spend))
