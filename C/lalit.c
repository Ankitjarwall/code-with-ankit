#include<stdio.h>
int main()
{
    int a,b,c,d,e;
    printf("Enter the rows : ");
    scanf("%d",&a);
    printf("Enter the coloums : ");
    scanf("%d",&b);
    int z[a][b];
    for(c=0; c<a; c++)
    {
        for(d=0; d<b; d++)
        {
            scanf("%d",&z[c][d]);
        }
    }
    for(c=0; c<a; c++)
    {
        for(d=0; d<b; d++)
        {
            printf("Index [%d][%d]    value %d   Address %d \n",c, d, z[c][d], &z[c][d]);
            e=e+z[c][d];
        }
    }
    printf("The sum of all elements : %d",e);
    return 0;
}
