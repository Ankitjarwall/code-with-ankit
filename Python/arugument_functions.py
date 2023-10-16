#default and keyword argument
# def add(a,b=5,c=10):
#     return (a+b+c)
# print(add(10))
# print()

#ARBITRARY argument    key value pair
# def fn(**a):
#     for i in a.items():
#         print (i)
# fn(numbers=5,colors="blue",fruits="apple")

#ARBITRARY argument    non-keyword variable-length arguments.key value pair
def add(*b):
    result=0
    for i in b:
         result=result+i
    return result

print (add(1,2,3,4,5))

