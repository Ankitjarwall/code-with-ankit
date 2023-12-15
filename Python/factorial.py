user_input = int(input("Enter the input : "))


def facto(user_input):
    Sum = 1
    i = user_input
    for i in range(user_input, 1, -1):
        Sum = Sum*i
    print("The factorial is :", Sum)


if user_input > 1:
    facto(user_input)
else:
    print("Value should be greater than 1.")
