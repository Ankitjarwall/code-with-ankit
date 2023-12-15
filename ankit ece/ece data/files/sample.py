import random
email=""
otp=0
found = False

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



user_input=int(input("Enter your choice : \n\tAttendance :1\n\tDelete : 2\t View : 3"))

def gen_otp(email):
    otp= random.randrange(1111,9999,3)
    return otp

def user_attendance_marked():
    mark_attendance=int(input("Enter Reg : "))
    # if mark_attendance in user_data
    otp=gen_otp(email)
    print("Otp : ",otp)
    user_otp_verify=input("Enter the otp : ")
    
    if user_otp_verify==otp:
        for user_dict in user_data:
            if user_dict.get("reg") == user:
                found = True
                print(attendance marked...)
        if not found:
            print("User not found.")
    else:
        print("Invalid otp..")

def user_delete():
    user_delete=int(input("Enter Reg : ")) 
    otp=gen_otp(email)
    print("Otp : ",otp)
    user_otp_verify=input("Enter the otp : ")
    if user_otp_verify==otp:
        print("User Deleted.")
    else:
        print("Invalid otp..")


if user_input==2:
    user_attendance_marked()
elif user_input==3:
    user_delete()
elif user_input==4:
    print("View attendace : ")
else:
    print("Enter valid input")
