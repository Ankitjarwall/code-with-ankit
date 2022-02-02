#include <stdio.h> //header file

int main() //main body
{
    int grade = 0, a = 0; //variable

    printf("A ");
    scanf("%d", &grade); //user input 5

    a = ++grade;
    printf("A = %d", a);

    return 0;
}