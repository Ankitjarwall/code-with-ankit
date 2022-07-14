// Q1
#include <stdio.h> //header file
int main()
{
    //Decalaring variable a,b,c,i,n,d
    int a, b, c = 1, i, n, d = 0;
    printf("enter the value of a "); //input- 5
    scanf("%d", &a);
    printf("enter the value of n "); //input- 6
    scanf("%d", &n);
    printf("\n==================\n1");
    i = n - 1;
    //Formula= 1+a+a^2+a^3.....n times
    while (i > 0)
    {
        c = c * a;
        d = d + c;
        i--;
        printf("+%d", c);
    }
    b = 1 + d;
    printf("\n==================\n\nResult=%d", b);
    //Displaying the total sum n'th terms
}
