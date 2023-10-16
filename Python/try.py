word = "ankitmeena"
newword = sorted(list(set(word)))
word = sorted(word)
word_dic = {}

for i in range(len(newword)):
    cout = 0
    for j in range(len(word)):
        if (newword[i] == word[j]):
            cout += 1
    word_dic.update({newword[i]: cout})
print(word_dic)

for i, j in word_dic.items():
    print(f'{i} : {j}')
    # print(f'{j}')
