#include<stdio.h>
int main()
{
    int a;
    printf("Enter the class =");
    scanf("%d",&a);
    switch(a){
        case 1:
            printf("the no is one");
            break;
        case 2:
            printf("The no is two");
            break;
        case 3:
            printf("The no is three");
            break;
        default:
            printf("the no is invalid");
            break;
    }
}

