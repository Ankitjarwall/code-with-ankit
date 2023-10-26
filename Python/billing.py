class discount(Exception):
    pass


def userinput():
    items = input("Enter the no. of items : ")
    try:
        items = int(items)
        raise discount
    except:
        print("Item Quantity should be in integers.")
    finally:
        print("Thank you for visiting.")


print("Welcome to the shop...")
userinput()
