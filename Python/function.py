list1 = [2, 3, 4, 5]


def multi(temp_list3):
    multi_output = 1
    for i in temp_list3:
        multi_output = i*multi_output
    return (multi_output)


print(multi(list1))
