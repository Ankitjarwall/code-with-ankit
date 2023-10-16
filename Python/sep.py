text1 = "This is a sample text document ."
text2 = "A text document may contain multiple words ."

newtext = set(text1.split())
newtext2 = set(text2.split())
new_output = list()

for i in newtext:
    if i in newtext2:
        new_output.append(i)

print(set(new_output))