# Problem Statement 1: Provide the solution with user defined functions
# You have a truck, and you want to calculate how efficiently it uses fuel. To do this, you need to know two things:
# How much diesel you put in the truck's tank (in liters).
# How far the truck goes before it runs out of diesel (in kilometers).
# You want to find out how much fuel the truck consumes and display it in two ways:
# First Way:

# Display the fuel consumption in a format called "liters per 100 kilometers." This tells you how many liters of diesel the truck uses to travel 100 kilometers.
# Second Way:

# Convert the result to the U.S. style of "miles per gallon." This tells you how many miles the truck can travel on one gallon of diesel.
# You need to consider that 1 kilometer is equal to 0.6214 miles, and 1 liter is equal to 0.2642 gallons.

def disp_kilo(ffule, favg):
    fuel_consumption = (ffule / favg) * 100
    print("\n\nFule in leters : ", ffule)
    print("Total Distance : ", ffule*favg, " km")
    return fuel_consumption


def disp_mile(ffule, favg):
    gallon = 0.2642*ffule
    mile = 0.6213*favg
    print("\n\nFule in gallons : ", gallon)
    print(f"Total Distance :  {gallon*mile :.2f} miles\n\n")


def user_input():
    totalkm = totalmiles = 10
    fule_input = int(input("Enter the fule in leters : "))
    disp_kilo(fule_input, totalkm)
    disp_mile(fule_input, totalmiles)


user_input()
