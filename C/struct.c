#include<stdio.h>

//            c file    

int sum(int z,int x);

int sum(int z,int x)
{
    int c;
    c=z+x;
    printf("sum : %d",z+x);
    return c;
}



int main()
{
    int a=10 ,b=11,c=9;
    
    sum(a,b);
    

}