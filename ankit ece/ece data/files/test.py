user_data = [
    {
        "name": "ankit",
        "email": "ankitmeena@gmail.com",
        "reg": 12103196
    },
    {
        "name": "meena",
        "email": "meena@gmail.com",
        "reg": 12103197
    }
]

user = int(input("Enter registration number: "))

found = False  # Initialize a flag to check if the user was found

for user_dict in user_data:
    if user_dict.get("reg") == user:
        found = True
        print(attendance marked...)

if not found:
    print("User not found.")
