#include<stdio.h>
int main()
{
    //declaration of variables.
    int num,num1,a=1,b=0,i,n;
    printf("Enter value of A : ");
    scanf("%d",&num);
    printf("Enter value of N : ");
    scanf("%d",&n);
    printf("\n--------------------------\n1");
    // Formula= 1+a+a^2+a^3.....n times
    i=n-1;
    while(i>0)
    {
        a=a*num;
        b=b+a;
        i--;
        printf("+%d",a);
        //The value as 1+a+a^2+a^3.....n times.
    }
    num1=1+b;
    printf("\n--------------------------\nresult=%d\n\n",num1);
    //Total sum of the n'th terms.
}
