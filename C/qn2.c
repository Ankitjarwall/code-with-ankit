#include <stdio.h> //header file
#include <conio.h> //header file

int fact(int);  //function declaration for recursive
int fact1(int); //function declaration for Iterative
int c = 1;      //declaring global variable
int fact(int a) //function defination for recursive
{
    if (a == 1)
    {
        return c;
    }
    c = c * a;
    a--;
    fact(a);
}

int fact1(int a) //function defination for Iterative
{
    int i;
    c = 1;
    for (int i = a; i > 0; i--)
    {
        c = c * i;
    }

    return c;
}
int main() //main body
{
    int d; //declaring variable
    printf("Enter the Number : ");
    scanf("%d", &d);                     //user input
    printf("recursive - %d\n", fact(d)); //function call for recursive
    printf("Iterative - %d", fact1(d));  //function call for iterative
}