list1 = {1, 2, 3, 4, 5}
list2 = {3, 4, 5, 6, 7}

newvar=set(list1-list2)
newvar1=set(list2-list1)
print(list(newvar))
print(list(newvar1))