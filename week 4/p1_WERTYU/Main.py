original = 'QWERTYUIOP[]ASDFGHJKL;ZXCVBNM,.1234567890-'
changed = "WERTYUIOP[]\SDFGHJKL;'XCVBNM,./234567890-="
mapping = {} # 변환 매핑
for i in range(len(original)):
    mapping[changed[i]] = original[i]
# 입력받은 문자열
input_str = []
while(True):
    try:
        input_str.append(input())
    except:
        break
# 원래 문자열로 바꿔준다.
for string in input_str:
    output_temp = ''
    for c in string:
        if c == ' ': # 띄어쓰기는 그대로
            output_temp += ' '
        else: # 띄어쓰기가 아닌 것은 바꿔준다.
            output_temp += mapping[c]
    print(output_temp)