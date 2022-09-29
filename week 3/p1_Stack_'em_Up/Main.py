# 2장문제 Stack 'em Up

# 카드의 위치를 바꿔주는 함수
def change_card_position(card, positionList):
    reposition = [0 for _ in range(len(card))]

    for j_card in range(len(card)):
        i_card = int(positionList[j_card]) - 1  # 이동할 카드, list의 index가 0부터 51이기에, -1해줌
        reposition[j_card] = card[i_card]

    return reposition


# testCase 받기
testCase = int(input())
_ = input()  # white space

# 결과출력을 위한 result list
result = []

for i in range(testCase):
    # input
    skill = input()
    skill = int(skill)  # 섞기 기술의 개수

    skillList = []  # 기술을 저장할 list
    for _ in range(skill):
        temp = input().split()
        skillList.append(temp)

    # default card
    literalNumber = ['Jack', 'Queen', 'King', 'Ace']
    values = list(range(2, 11)) + literalNumber
    pattern = ['Clubs', 'Diamonds', 'Hearts', 'Spades']

    card = []
    for p in pattern:
        for v in values:
            card.append((v, p))

    try:
        # 딜러의 어떤 기술 쓸 지 input받고 그에 맞게 카드 섞기
        num = input()
        while num != '':
            num = int(num) - 1
            card = change_card_position(card, skillList[num])
            num = input()

        # 마지막 결과만 result에 저장
        result.append(card)
    except:
        result.append(card)
        break

# 결과 출력
for i in range(len(result)):
    for j in range(52):
        print(result[i][j][0], "of", result[i][j][1])
    print()
