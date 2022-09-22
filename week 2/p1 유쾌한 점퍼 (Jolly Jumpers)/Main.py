# 입력받음
while True:
	try:
		input_list = input().split()
		n = int(input_list[0])
		n_list = list(map(int, input_list[1:]))
		jumper_list = []

		# 앞뒤에 있는 숫자 차의 절대값을 구함
		for i in range(n-1):
			jumper_list.append(abs(n_list[i] - n_list[i+1]))

		# Jolly인지 Not jolly인지 확인
		isJolly = True
		for jumper in range(len(jumper_list)):
			if jumper+1 not in jumper_list:
				print("Not jolly")
				isJolly = False
				break
		if isJolly:
			print("Jolly")
	except EOFError:
		break