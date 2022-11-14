# 식별자, 첫 상태, 목표상태
def reachable(identifier, state, cell):
    state_list = [] # 상태들을 저장할 list
    state_list.append(state)

    while True:
        cell_temp = '' # 만들어진 상태를 임시로 저장할 변수

        for i in range(len(state)):

            # 양 옆의 값으로 현재상태를 예측
            temp = '0b'+str(state[i-1]+state[i]+state[(i+1)%len(state)])
            temp = int(temp, 2)

            # 예측한 상태를 cell_temp에 저장
            # identifier의 길이보다 길면, 0을 append
            if temp >= len(identifier):
                cell_temp += '0'
            else:
                cell_temp += identifier[temp]

        # 목표하는 상태와 도달한 상태가 같으면 True를 반환
        if cell_temp == cell:
            return True

        # 현재 만든 state가 저장한 state에 없다면, list에 저장
        # list에 있다면, cell에 도달할 수 없음을 의미하기에 False를 반환
        if cell_temp not in state_list:
            state_list.append(cell_temp)
        else:
            return False

# 셀의 길이만큼 모든 state의 길이를 맞춰줌
def make_same_length(state, num):
    while len(state) < num:
        state = '0' + state
    return state


try:
    while True:
        identifier, num, cell = input().split() # 식별자, 셀의 개수, 상태
        num = int(num)

        # 식별자를 셀의 개수에 맞게 바꿔줌
        identifier = bin(int(identifier))[2:]
        identifier = make_same_length(identifier, num)

        is_reachable = False

        # 서로다른 오토마타들을 만들어내고, Reachable인지 확인함
        for i in range(0, pow(2, num)):
            state = bin(i)[2:]
            state = make_same_length(state, num)

            is_reachable = reachable(identifier[::-1], state, cell)

            # Rechable이면 멈추고 출력을 진행함
            if is_reachable:
                break

        if is_reachable:
            print("REACHABLE")
        else:
            print("GARDEN OF EDEN")

except EOFError:
    exit()