try:
    while True:
        s1 = input()
        s2 = input()

        # 중복되지 않는 글자 저장
        s1_set = []
        s2_set = []
        # 중복되지 않는 글자 + 글자의 등장 빈도 수 저장
        s1_dic = {}
        s2_dic = {}

        for i in s1:
            s1_dic[i] = s1_dic.get(i, 0)+1
            s1_set.append(i)
        for i in s2:
            s2_dic[i] = s2_dic.get(i, 0)+1
            s2_set.append(i)

        s1_set = set(s1_set)
        s2_set = set(s2_set)


        result = ''
        # s1과 s2의 교집합을 사용
        for i in sorted(s1_set.intersection(s2_set)):
            # s1에 저장된 것과 s2에 저장된 것 중 짧은 것을 반복
            iter = min(s1_dic[i], s2_dic[i])
            for j in range(iter):
                result = result+i

        print(result)

except EOFError:
    exit()