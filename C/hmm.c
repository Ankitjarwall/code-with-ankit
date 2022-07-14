#include <stdio.h>
#include <conio.h>
int main()
{
    int age;
    char name[20];
reload:
    printf("Enter your name : ");
    scanf("%s", &name);
    printf("Enter your age : ");
    scanf("%d", &age);
    if (age >= 18)
    {
        printf("You are eligible for voting.\n");
    }
    else
    {
        printf("Sorry you are not eligible.\n");
        goto reload;
    }
    switch (age)
    {
    case 18:
        printf("You are on right place, on right time.\n");
        break;
    case 100:
        printf("You are on right place,but you are late.\n");
        break;
    case 15:
        printf("You are of 15 only.\n");
        break;

    default:
        break;
    }

    int rows, i, i1, i2=0;
    printf("\n\n\nEnter the rows number : ");
    scanf("%d", &rows);
    for (int i = 1; i <= rows; i++)
    {
        
        for (int i2 = 5; i2 <= rows; i2--)
        {
            printf("*");
        }
        printf("\n");
    }
}