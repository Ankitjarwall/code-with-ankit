name = input("Name : ")
stream = input("School : ")
total_subject = 3
math = input("Math mark : ")
english = input("English mark : ")
hindi = input("Hindi mark : ")


def total_marks():
    total_marks = math+english+hindi
    return total_marks


def avg():
    global total_subject
    total = int(total_marks())
    avg = total/total_subject
    return avg


def CGPA():
    global total_subject
    total = int(total_marks())
    CGPA = (total/total_subject)*100
    return CGPA


def display():
    print("Name : ", name)
    print("Stream : ", stream)
    print("Math : ", math)
    print("Hindi : ", hindi)
    print("English : ", english)
    print("Avg Marks : ", avg())
    print("Total Marks : ", total_marks())
    print("CGPA : ", CGPA())


f = open("D:\code-with-ankit\Python\calculate_mark.txt", "w")
f.write('name\tstream\tMath Marks\tHindi Marks\tEnglish Marks\tAvg\t\tCGPA"')
f.write("\n")
text = str(name+"\t"+stream+"\t\t"+str(math)+"\t\t\t\t"+str(hindi)+"\t\t\t\t" +
           str(english)+"\t\t"+str('{:.2f}'.format(avg()))+"\t"+str(total_marks())+"\t"+str('{:.2f}'.format(CGPA())))
f.write(text)

f.close()

f = open("D:\code-with-ankit\Python\calculate_mark.txt", "r")
print(f.read())
f.close()
