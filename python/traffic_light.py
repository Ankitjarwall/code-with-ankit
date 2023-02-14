#import machine
#import time
#red = machine.Pin(2, machine.Pin.OUT)
#green = machine.Pin(4, machine.Pin.OUT)
#yellow = machine.Pin(5, machine.Pin.OUT)

def eastd():
    print("in east function");
def westd():
    print("in west function");
def southd():
    print("in south function");
def northd():
    print("in north function");


print("start");
while True:
    
    east=west=south=north=0;
    print("East : 1 West :2 South :3 North : 4");
    user_input=int(input("Enter the direction : "));

    for i in range(1):
        if(user_input==1):
            print("East on.");
            eastd();
        elif(user_input==2):
            print("West on. ");
            westd();
        elif(user_input==4):
            print("North on");
            northd();
        elif(user_input==3):
            print("South on.");
            southd();
        else:
            print("Invalid input.");


   
