#include<stdio.h>
int main()
{
int x, y,choice,temp;
printf("\nWant to use temp method choose 1: ");
printf("\nDont want to use temp method choose2: ");
printf("\nchoose1 or 2 ");
scanf("%d", &choice);
if (choice==1){
printf("Enter the value of x and y: ");
scanf("\n%d\n%d",&x, &y);
printf("before swapping numbers: %d %d\n",x,y);
x = x + y;
y = x - y;
x = x - y;
printf("\nAfter swapping: %d %d",x,y);}
if(choice==2){
printf("Enter first number: ");
scanf("%d", &x);
printf("Enter second number: ");
scanf("%d", &y);
temp = x;
x = y;
y = temp;
printf("\nAfter swapping, first number = %d\n", x);
printf("\nAfter swapping, second number = %d", y);
}
return 0;
}
