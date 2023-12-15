userinput = int(input("Enter the number : "))
choice = input("Odd Or Even : ")

# for i in range(0, userinput):
#     for j in range(0, i):
#         print("*", end="")
#     print("")

# for i in range(userinput, 0, -1):
#     for j in range(0, i):
#         print("*", end="")
#     print("")

if choice == "odd":
    for i in range(userinput):
        if i % 2 != 0:
            print(i, ",", end="")
else:
    print("Even number are ...")
    for i in range(userinput):
        if i % 2 == 0:
            print(i, ",", end="")
