# Pooja wants to withdraw some money (X dollars) from an ATM, but there are certain rules:
# The ATM will only allow the withdrawal if the amount (X) is a multiple of 5 (i.e., it ends in 0 or 5).
# Every time she makes a successful withdrawal, the bank charges her 0.50 dollars.
# For each withdrawal attempt, you need to calculate Pooja's account balance after the transaction. If her account has enough money to complete the withdrawal, subtract the withdrawn amount and the bank charge from her account balance. If her account doesn't have enough money to complete the transaction, keep her current bank balance unchanged.
# Here's what you need to do:
# Read the number of test cases (t) to consider.
# For each test case, read two values (x and y), where x is the amount Pooja wants to withdraw, and y is the amount of money in her bank account.
# Calculate Pooja's account balance after the withdrawal attempt, considering the rules.
# Display the final account balance with two digits of precision.


def withdraw(amt, bal):
    if amt % 5 == 0:
        if amt < bal:
            bal = bal - amt
            bal -= 0.50
            # Using f-string to format to two decimal places
            print(f"Balance: {bal:.2f}")
        else:
            print("Insufficient balance")
    else:
        print(f"{amt} is not a multiple of 5.")


pooja_bal = 5000
pooja_input = int(input("Withdraw amount : "))
withdraw(pooja_input, pooja_bal)
