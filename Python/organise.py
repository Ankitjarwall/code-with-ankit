guests = [("Lily", 56), ("Adam", 22), ("Danil", 35),
          ("Dave", 45), ("Grace", 24), ("Helen", 38)]

guests_under_30 = []
guests_above_30 = []

for i in guests:
    if i[1] <= 30:
        guests_under_30.append(i)
    elif i[1] > 30:
        guests_above_30.append(i)
    else:
        print("Invalid input")

f = open("guests_list.txt", "w")
f.write(str(guests))
f.write("\n\nGuests Under 18 : ")
f.write(str(guests_under_30))
f.write("\n\nGuests avove 30:")
f.write(str(guests_above_30))

f.close
f = open("guests_list.txt", "r")
print("\n\n", f.read())
