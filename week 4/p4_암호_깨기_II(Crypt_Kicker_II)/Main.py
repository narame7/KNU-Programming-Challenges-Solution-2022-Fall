key = 'the quick brown fox jumps over the lazy dog' # 해독을 도와줄 키
T = int(input()) # 테스트 케이스 개수
empty = input() # 빈 줄 입력

# 테스트케이스 개수만큼 반복
for _ in range(T):
    input_crypt = [] # 입력 암호들
    # 암호를 입력받는다
    while(True):
        try:
            input_string = input()
            if input_string == '':
                break
            input_crypt.append(input_string)
        except:
            break
    # 암호 깨기 시작
    mapping = {}
    for string in input_crypt:
        # 입력받은 암호들 중에서 키가 변한게 있는지 확인
        if len(string) == len(key):
            for index in range(len(string)):
                # 띄어쓰기는 무시
                if key[index] == ' ' and string[index] == ' ':
                    continue
                # 띄어쓰기가 아니면 바뀐문자를 원래 문자로 매핑
                elif key[index] != ' ' and string[index] != ' ':
                    mapping[string[index]] = key[index]
                # 하나는 띄어쓰기이고 하나는 아니라면 키가 변환된 게 아니므로
                # 지금까지 넣어준 매핑은 지우고 다음 문자열 검색
                else:
                    mapping = {}
                    break
    # 잘 매핑이 되었다면 a부터 z까지 총 26개가 매핑이 되어야함
    # 잘 매핑이 안되었다면 'No solution' 출력
    if len(mapping.keys()) != 26:
        print('No solution.')
    # 잘 매핑이 되었다면 암호 변환해서 출력
    else:
        for crypt in input_crypt:
            original = ''
            for c in crypt:
                if c == ' ':
                    original += ' '
                else:
                    original += mapping[c]
            print(original)
    print()