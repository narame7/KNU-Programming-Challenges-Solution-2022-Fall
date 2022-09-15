# 입력이 안들어올때까지 반복
while True:
	try:
		# 10진수, 16진수 입력받기
		input_number = input()
		# 입력받은 수의 길이가 2보다 작다면 0x가 안붙은 10진수
		if len(input_number) < 2:
			print(hex(int(input_number)))
			continue
		# 10진수인지 16진수인지 판별
		if input_number[1] == 'x': # 16진수이면
			print(int(input_number[2:], 16))
		else: # 10진수이면
			print('0x'+format(int(input_number), 'X'))
	except EOFError:
		break