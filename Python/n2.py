n = int(input())
faltu_list = []
if 1 <= n and n <= 20:
    for i in range(0, n):
        faltu_list.append(i)

    for i in range(0, n):
        sqr = faltu_list[i]*faltu_list[i]
        print(sqr)
else:
    print("Invalid")
