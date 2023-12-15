text = input("Enter the input : ")

UpperCase = 0
lowerCase = 0


def capital(ftext):
    global UpperCase
    global lowerCase
    for i in ftext:
        if (i.isupper()):
            UpperCase = UpperCase+1
        else:
            if (i.isnumeric() or i == ' '):
                continue
            else:
                lowerCase = lowerCase+1


capital(text)

print("Upper case in ", text, " : ", UpperCase)
print("Lower case in ", text, " : ", lowerCase)
