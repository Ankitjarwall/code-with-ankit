name = "Ananya "

f = open("file.txt", "w")
f.write("ankit   " + name)
f.close()


f = open("file.txt", "r")
print(f.read())
f.close()
