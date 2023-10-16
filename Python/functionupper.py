# list1=[q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m]
# list2=[Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Z,X,C,V,B,N,M]

word = "Ankit"


def check(temp_word):
    upper = lower = 0
    for i in range(len(temp_word)):
        if (temp_word[i].isupper()):
            upper = upper+1
        else:
            lower = lower+1
    print("The string is : ", temp_word)
    print("The upper string count are : ", upper)
    print("The Lower string count are : ", lower)


check(word)
