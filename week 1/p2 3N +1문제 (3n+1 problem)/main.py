# 입력이 안들어올 때까지 반복
while True:
	try:
		# i, j 입력 받기
		input_numbers = input()
		input_numbers = input_numbers.split()
		i = int(input_numbers[0]) # int 형으로 바꿔주기
		j = int(input_numbers[1])
		max_cycle_length = -1 # 최대 사이클 길이

		# i부터 j까지 사이클 길이 구하기
		for n in range(min(i,j), max(i,j)+1):
			count = 1
			while n != 1: # n이 1이 될 때까지 반복
				if n % 2 == 0: # n이 짝수이면
					n /= 2 # 반으로 나눠준다.
				else: # n이 홀수이면
					n = n * 3 + 1 # n에 3을 곱하고 1을 더해준다. => 무조건 짝수
					count += 1
					n /= 2
				count += 1 # cycle length 1 더하기
		# 리스트의 길이가 현재까지 cycle_length의 최대 길이보다 길다면 max_cycle_length를 갱신한다.
			if max_cycle_length < count:
				max_cycle_length = count

		# i, j, 최대 사이클 길이를 출력한다.
		print(i, j, max_cycle_length)
	except EOFError:
		break