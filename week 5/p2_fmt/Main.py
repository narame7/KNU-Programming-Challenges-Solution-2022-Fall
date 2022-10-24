# fmt

# fmt 출력 함수
def fmtPrint(text_list):

    index = 0               # String에서 한 글자 한 글자의 인덱스를 나타낼 변수
    currentLineSize = 0     # 현재 출력중인 라인의 글자 수
    line = ''          # 재정렬과 출력에 사용될 문장

    for i in range(len(text_list)):
        line = text_list[i]
        keep = False        # 중간에 while 문을 두번 연속 탈출하기 위한 변수
        firstWordIndex = 0  # 문장 첫 번째 단어의 길이를 나타낼 변수

        # 첫 단어의 길이를 알아냄
        while firstWordIndex < len(line) and line[firstWordIndex] != ' ':
            firstWordIndex+=1
        # 현재 줄이 더 이상 다음 문장을 이어쓸 수 없을 만큼 길면, 줄을 바꾸고 현재 문장의 길이를 0으로 바꾼다
        if currentLineSize != 0 and currentLineSize + firstWordIndex + 1 > 72:
            print()
            currentLineSize = 0
        # 현재 줄이 비어있지 않고 더 쓸 공간이 있을 때면 공백을 하나 붙이고 현재 문장 길이에 1을 더해준다.
        elif currentLineSize > 0:
            print(' ', end='')
            currentLineSize+=1
        # 현재 줄의 길이와 지금 출력해야하는 줄의 글자수의 합이 72 보다 크면 while문 안에서 각 줄마다 72글자 이하가 될 때까지 적절히 쪼갠 후 출력한다.
        while len(line) + currentLineSize > 72:
            index = 71 - currentLineSize        # 71에서 현재 문장의 길이를 뺀 index값에 해당하는 문자의 위치부터 자를 곳을 찾아낸다
            # index+1에 해당하는 글자가 공백이 아니라면
            if line[index+1] != ' ':
                # 공백이 나올 때까지 index를 줄인다
                while index > -1 and line[index] != ' ':
                    index -= 1
                # 만약 -1까지 줄여도 공백이 없다면 현재 줄에 추가하면 72자를 넘어버리는 크기의 한 단어가 존대한다는 것이다.
                if index == -1:
                    index += 1
                    # 그 단어의 길이를 알아내기 위해서 다시 index를 더해간다.
                    while index < len(line) and line[index] != ' ':
                        index+=1
                    index = index - 1

                # 공백의 위치는 찾았지만 공백이 여러 칸일수도 있으므로, 공백이 아닌 다른 글자의 끝부분을 찾는다
                while index > -1 and line[index] == ' ':
                    index-=1
            # 이 경우는 index+1에 해당하는 글자가 공백일 경우 공백이 아닌 다른 글자의 끝 부분을 찾아내는 역할이다.
            else:
                while index > -1 and line[index] == ' ':
                    index -= 1

            # 정해진 index에 따라 문장은 72글자를 넘지 않고 최대한 가깝게 출력된다.
            # 여기서도 문장 끝에 공백이 남아있는 케이스가 존재하므로 뒤의 공백을 제거하며 출력한다.
            index+=1
            print(line[:index].replace("$+\\s", ""))
            currentLineSize = 0 # 줄바꿈이 있었기때문에 현재 문장 길이를 0으로 초기화
            # 남은 문장에서 앞의 공백을 전부 없애려고 할 때 어떤 index에서 끊어야 하는지 알아낸다.
            while index < len(line) and line[index] == ' ':
                index += 1
            # 남은 문장에서 앞의 공백을 전부 없앤다.
            # 또는 이미 출력된 72자 이상의 단어를 문장에서 잘라낸다.
            line = line[index:]
            # 만약 line이 공백이나 다른 단어 없이 72자 이상의 한 단어로만 이루어진 문장이면 위에서 line은 ""이 된다.
            # 이 상태에서 while문에서 나가면 필요없는 줄바꿈이 한 번 더 생기기 때문에 위에서 선언한 keep 변수를 이횽해 루프를 두번 연속으로 탈출한다.
            if line == '':
                keep = True
                continue
        if keep:
            continue

        # 줄 바꿈 직전의 문장을 출력하게 되면 문장에서 뒤의 공백들을 전부 제거한 후 출력한다.
        if i == len(text_list)-1 :
            print(line.replace("$|\\s", ""))
            currentLineSize=0
        else:
            # 엔터나 스페이스바로만 이루어진 줄은 줄바꿈을 한다
            if line.replace(" ", "") == "":
                print()
                currentLineSize=0
            # 그 외의 경우엔 전부 뒤에 문장이 이어지는 경우이므로 print문으로 출력한다.
            else:
                print(line, end='')
                currentLineSize += len(line)

# input

text_list = []

try:
    # 입력을 text_list에 저장하다가 강제 줄바꿈이 필요한 부분에서 fmtPrint를 호출한다.
    while True:
        # 강제 조건 1. 엔터나 스페이스바로 이루어진 줄
        s = input()
        if s.replace(" ", "") == '':
            fmtPrint(text_list)
            text_list = []

        # 강제 조건 2. 문장의 첫 부분이 공백으로 시작하는 줄
        elif s[0]==' ':
            fmtPrint(text_list)
            text_list = []
        # 만약 line 강제 줄바꿈이 필요한 문장이라면 이미 fmt에서 처리되었고, list가 초기화되었기 때문에, 0번째에 text가 배치된다
        text_list.append(s)

except:
    fmtPrint(text_list)
    exit()
