value=int(input("Enter the value : "))
data={}
for i in range(1,value+1):
    data[i]=i*i
    # data.update({i:i*i})

print(data)