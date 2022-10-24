# doublets

# doublet의 index를 저장하는 array를 만든다
def get_graph(word_dict):

    # 빈 행렬 만들기
    graph=[]
    for s in range(len(word_dict)):
        graph.append([0]*len(word_dict))

    # 단어들의 doblets을 저장하는 array생성
    for i in range(len(word_dict)):
        idx = 0

        for j in range(len(word_dict)):
            # 자신과 다른단어이고, 길이가 같으면 진행
            if i != j and len(word_dict[i]) == len(word_dict[j]):
                diff=0
                for t in range(len(word_dict[i])):
                    if word_dict[i][t] != word_dict[j][t]:
                        diff+=1
                    # doublets은 자신과 한단어만 달라야하기때문에 2보다 크면 멈추기
                    if diff >=2:
                        break
                if (diff == 1):
                    graph[i][idx] = j+1
                    idx+=1
    return graph

def search_dobulet_path(start,end,graph,visited,answer):
    idx=0
    visited[start]=True

    while (graph[start][idx] !=0):
        if graph[start][idx] == end +1:
            answer.append(end)
            answer.append(start)
            return True
        else:
            if(not visited[graph[start][idx]-1]):
                path = search_dobulet_path(graph[start][idx]-1, end, graph, visited,answer)
                if path:
                    answer.append(start)
                    return True
            idx +=1
    return False

# word들 list에 저장하기
words = dict()
idx=0
while True:
    s = input()
    if s =="":
        break
    else:
        words[idx]=s
        idx+=1


# 시작단어와 끝단어 받기
try:
    while True:
        start_word, end_word = input().split()

        # doublet array 생성
        graph=get_graph(words)

        visited=[False]*len(words)
        # key랑 value를 바꿔줌
        inv_words = {v: k for k, v in words.items()}
        path=[]

        # doublets path가 있는지 확인
        has_path = search_dobulet_path(inv_words[start_word], inv_words[end_word], graph, visited, path)

        if has_path:
            for i in reversed(path):
                print(words[i])
        else:
            print('No solution.')

        print()
except:
    exit()