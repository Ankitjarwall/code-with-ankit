set1 = {"Bob", "Ankit", "Ananya", "Arsh"}
set2 = {"Himanshu", "Jatin", "Bob", "Anup"}

final = set()

for i in list(set1):
    for j in list(set2):
        if (i == j):
            print(i)
