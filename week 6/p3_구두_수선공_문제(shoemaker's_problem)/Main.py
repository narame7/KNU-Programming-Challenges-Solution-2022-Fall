# shoemaker's problem

# 스케줄링을 위한 함수
def scheduling(tasks):
    # 벌금/시간이 큰 순으로 스케줄링
    # 만약에 비율이 같으면 걸리는 시간이 적은 순으로 정렬
    tasks = sorted(tasks, key = lambda x:x[2]/x[1], reverse=True)

    return [t[0] for t in tasks]


testcase = int(input())

for _ in range(testcase):
    _ = input() # white space

    tasks = []
    task_num = int(input())
    for index in range(task_num):
        time, fine = input().split()
        time = int(time)
        fine = int(fine)
        # 일의 index, 소요시간, 벌금 순으로 튜플에 넣은 후 리스트에 저장
        tasks.append((index+1, time, fine))

    result_index = scheduling(tasks)

    result = ''
    for i in result_index:
        result = result+str(i)+' '

    print(result[:-1])
    print()