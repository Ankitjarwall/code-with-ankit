dic1={"Name":"Ankit","Year1":"2023"}
dic2={"Year":"2023"}

dic3={}
dic3.update(dic1)
dic3.update(dic2)
print(dic3)


# method 2
newdic={"New":"list"}
dic4=dic1 | dic2 | newdic
print(dic4)