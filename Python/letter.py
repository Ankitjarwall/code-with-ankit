word1 = "hello"
word2 = "world"

for i in range(len(word1)):
    for j in range(len(word2)):
        if i == j:
            print(word1[i])
            print(word2[i])
