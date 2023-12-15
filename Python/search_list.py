matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

print(matrix)
element = int(input("Enter the no from above list : "))
found = False

for i in matrix:
    newindex = matrix.index(i)
    for j in i:
        if (j == element):
            print("Element found : ", element)
            found = True
            print("Index : ", newindex, " ", i.index(element))
            exit

if found != True:
    print("No element")
