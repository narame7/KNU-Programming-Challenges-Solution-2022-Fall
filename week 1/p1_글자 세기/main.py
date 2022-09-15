# 입력이 안들어올때까지 반복
while True:
	try:
		# 문자 입력 받음
		input_str = input()
		if len(input_str) == 0:
			exit(1)
		# 단어 수와 글자수 세기
		word = 0
		letter = 0
		word = input_str.split()
		for w in word:
			letter += len(w)
		print(letter, len(word))
	except EOFError:
		break