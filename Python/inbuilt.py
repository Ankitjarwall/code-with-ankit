def userinput():
    userinput = input("Enter the input : ")
    return userinput


try:
    try:
        f = open("inbuilt.txt", "w+")
        f.write(userinput())
    except:
        print("file not found.")
    finally:
        print(f.read())
        f.close()

except:
    print("file not found.")
