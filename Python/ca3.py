# (Program to count the no of uppercase and lowercase characters in file)
def capital_small_calculator(fuserinput):

    capital = 0
    small = 0

    for char in fuserinput:
        if char.isupper():
            capital += 1
        elif char.islower():
            small += 1

    return capital, small


userinput = input("\n\tEnter the Text : ")
upperword, lowerword = capital_small_calculator(userinput)

filetext = "\n\n\tText : "+str(userinput)+"\n\tUppercase letters count: " + \
    str(upperword) + "\n\tLowercase letters count:" + str(lowerword)+"\n\n"

f = open("ca3.txt", "w")
f.write(filetext)
f.close()

f = open("ca3.txt", "r")
print(f.read())
f.close()
