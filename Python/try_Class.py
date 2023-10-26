class mybca(Exception):
    print("Class called")
    pass


number = 18
try:
    userinput = input("Enter the number : ")
    if userinput < number:
        raise mybca
    else:
        print("valid age.")
except:
    print("this is the except block error.")
finally:
    print("Thank you for showing intrest.")
