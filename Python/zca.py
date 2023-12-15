# Program to save even and odd numbers in 2 separate files ...for even roll nos

odd = open("ca_odd.txt", "w")
even = open("ca_even.txt", "w")

numbers = [12, 15, 97, 18, 25, 24, 83, 56]

for i in numbers:
    if (i % 2 == 0):
        even.write(str(i)+"\n")
    else:
        odd.write(str(i)+"\n")

odd.close()
even.close()

odd = open("ca_odd.txt", "r")
even = open("ca_even.txt", "r")
print("Odd")
print(odd.read())
print("Even")
print(even.read())
odd.close()
even.close()
