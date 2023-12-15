# a = "ananya"


# def word(a):
#     a = 'ankit'
#     print(a)

# word(a)
# print(a)


# mylist=["A",2,"b",False,["Ananya",True]]
# print(mylist[4][1])

# mytuple = ("Ankit", "BABA", 12, True)
# print(mytuple.index("BABA"))
# print(mytuple.count(12)) count the element present in the tupple data type
# mytuple[1]="new baba"
# print(mytuple[1])
# mylist=list(mytuple)
# mylist[1]="new baba"
# print(mylist)

# mytuple=tuple(mylist)
# print(mytuple)


# lista=[]
# tuplea=()
# seta={'d','s'}
# seta.
# dis={"key":"values","key":"values"}


# mylist=['q','w','v','b','n','m']

# if 10==10:
#     print("done")
# elif 11==11:
#     print("11")
# else:
#     print("end")

# if mylist == 10:
#     pass
# elif 11 == 11:
#     print("11")
# else:
#     print("end")

# find="2"
# milgaya=0

# for x in mylist:
#     if x==find:
#         print("got it")
#         milgaya=1

# if milgaya==0:
#     print("no item")

# for i in range(10,50,5):
#     print(i)

# name = "Ananya "

# f = open("file.txt", "w")
# f.write("ankit   " + name)
# f.close()


# f = open("file.txt", "r")
# print(f.read())
# f.close()


# name = "Ananya "

# f = open("file.txt", "w")
# f.write("ankit   " + name)
# f.close()


# function are code of block which can be used again and again for a specific task. function inesdngjshd the code reuseablity

# function are of two types
# 1 user define

# function which deinfed by users are known user defined functions.

z = x = y = 10


def google():
    b = (z+x+y)/3
    print("avg : ", b)


google()

# 2 built in functions
#  built in functions are pre defined functions which are pre installed with python, these are pre defined functions to help the developers.

print("name : ", z)


# 3 lambda
#  lambda functions don't have any specific name, and used for short and single exprestion type calculations
print((lambda x, y: (x + y)/2)(4, 3))

# 4 recursion
#  a function calls it self again and again ultill a conditions meets, comes to true

# without recursion
for i in range(0, 10):
    print(i)

# with recuresion


def counting(i):
    if i == 10:
        print("function exist")
        return 0
    print(i)
    i = i+1
    counting(i)


counting(0)


# * default functions
def news():  # prameter
    print("news")


news()  # arguments

# positional arg, arg order important,unexpected error


def news(i, z):
    print("i : ", i, "z : ", z)


news(10, 20)

# keyword arguments


def news(z, i):
    print("i : ", i, "z : ", z)


news(i=10, z=20)

# * arbitrary arguments


def news(*z):
    print(type(z))
    print("z : ", z)


news(10, 20, 30, 40, 20)

# ** arbitrary keyword arguments
# def news(**z):
#     print(type(z))
#     print("z : ", z["e"])

# news(a=10, b=20, c=30, d=40, e=50)

# _namw="kk"
# nn="ll"
# print(_namw,nn)


# age = int(input("Enter the age : "))
# print(type(age))
# if age >= 18:
#     print("gooo...")
# else:
#     print("wow!!!!")

# print("hi")


# n="0"

# if n.isnumeric():
#     print("It is non zero value")
# else:
#     print("it is zero value")


year=int(input("Enter year to be checked:"))
if(year%4==0 and year%100!= 0 or year%400==0):
    print("The year is a leap year!")
else:
    print("The year isn't a leap year!")


for i in range(0, 10):
    if i == 5:
        pass
    print(i)

# i=0
# while i<10:
#     print(i)
#     i=i+1


# wrong
# i=10
# if i==10:
#     break
# else:
#     print("h")



def sum():
    pass

def minus():
    print(90)

def main():
    sum()
    minus()
    
main()