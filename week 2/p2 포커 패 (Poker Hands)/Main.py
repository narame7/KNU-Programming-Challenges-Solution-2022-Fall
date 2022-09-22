value_dict = {'T':10, 'J':11, 'Q':12, 'K':13, 'A':14}

def poker(card):

    values = sorted([c[0] for c in card])
    pattern = [c[1] for c in card]
    straight = (values == list(range(min(values), max(values)+1)))
    flush = all(p == pattern[0] for p in pattern)

    if straight and flush:
        return 8, max(values), values

    pairs = []
    three_card = False
    pair_present = False
    three_card_value = -1

    for v in set(values):
        # four card
        if values.count(v) == 4:
            return 7, v, values
        if values.count(v) == 3:
            three_card = True
            three_card_value = v
        if values.count(v) == 2:
            pair_present = True
            pairs.append(v)

    if three_card and pair_present:
        return 6, three_card_value, values
    if flush:
        return 5, max(values), values
    if straight:
        return 4, max(values), values
    if three_card:
        values.remove(three_card_value)
        return 3, three_card_value, values
    if len(pairs) == 2:
        pair1 = pairs[0]
        pair2 = pairs[1]

        values.remove(pair1)
        values.remove(pair2)
        values.remove(pair1)
        values.remove(pair2)

        if pair1>=pair2:
            return 2, pair1, [pair2, values[0]]
        elif pair1<pair2:
            return 2, pair2, [pair1, values[0]]
    if len(pairs) == 1:
        values.remove(pairs[0])
        values.remove(pairs[0])
        return 1, pairs[0], values


    return 0, max(values), values


def checkHighCard(black_card, white_card):
    for i in range(len(black_card)).__reversed__():
        if black_card[i] == white_card[i]:
            continue
        elif black_card[i] > white_card[i]:
            print("Black wins.")
            return
        elif black_card[i] < white_card[i]:
            print('White wins.')
            return

    print('Tie.')


while True:
    try:
        # 카드 input받기
        temp = input().split()
        numbering = []

        # T, K, J, Q를 정수로 바꾼 후, 카드 타입과 함께 튜플로 저장
        for card in temp:
            if card[0] not in value_dict:
                value = int(card[0])
            else:
                value = value_dict[card[0]]
            numbering.append((value, card[1]))

        black_card = numbering[:5]
        white_card = numbering[5:]

        # poker check
        blackClass, blackNum, bRemainderCard = poker(black_card)
        whiteClass, whiteNum, wReaminderCard = poker(white_card)

        if blackClass > whiteClass:
            print("Black wins.")
        elif whiteClass > blackClass:
            print("White wins.")
        else:
            # high card같은 경우는 따로 계산
            if blackNum > whiteNum:
                print("Black wins.")
            elif whiteNum > blackNum:
                print("White wins.")
            else:
                if blackClass < 3:
                    checkHighCard(bRemainderCard, wReaminderCard)
                else:
                    print('Tie.')
    except:
        break
