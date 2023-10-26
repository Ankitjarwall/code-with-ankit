try:
    num1 = int(input("Enter the input : "))
    num2 = int(input("Enter the input : "))

    try:
        result = num1/num2
        print("Result : ", result)
    except:
        print("This is the inner except...")
except:
    print("This is outter block")
