user_preferences = {"apple", "banana", "cherry", "grape"}
recommended_items = {"apple", "kiwi", "cherry", "grape", "orange"}

for i in recommended_items:
    if i not in user_preferences:
        print(i)

# using difference() method
newlist = recommended_items.difference(user_preferences)
print(newlist)
