initial_balance = 10000
maintain_balance = 1000

total_balance = 0
deposit_balance = 0


def withdraw():
    global total_balance
    withdraw_amount = int(input("Enter the Amount to be withdraw : "))
    if (total_balance >= withdraw_amount):
        total_balance = total_balance-withdraw_amount
        print("The amount withdraw is : ", withdraw_amount)
        print("Successfully withdraw...")
    else:
        print("Not suffecient balance.")
    check_balance()


def check_balance():
    if (total_balance < maintain_balance):
        print("Low balance, min balance should be ",
              total_balance+maintain_balance)
    else:
        print("Your balance is ", total_balance+maintain_balance)


def deposit():
    global total_balance
    deposit_balance = int(input("Enter the amount to deposit : "))
    total_balance = deposit_balance+total_balance
    print("Successfully deposited...")
    check_balance()


def main():
    print("1\tWithdraw, 2\tCheck Balance, 3\tDeposit, 4 \t Exit")
    task = int(input("Enter the task to perform : "))

    if (task == 1):
        withdraw()
    elif (task == 2):
        check_balance()
    elif (task == 3):
        deposit()
    elif (task == 4):
        exit()
    else:
        print("Invalid input... Try again...")


while (True):
    main()
