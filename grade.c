#include <stdio.h> //header file

int main() //main body
{
    int score = 0; //variable
grade:
    printf("\n+----------------------------+");
    printf("\nPlease enter the Score : ");
    scanf("%d", &score); //user input

    if (score == 100) //conditional statement
    {
        printf("Grade - A+"); //Grade A+
    }
    else if ((score >= 90) && (score <= 99))
    {
        printf("Grade - A"); //Grade A
    }
    else if ((score >= 80) && (score <= 89))
    {
        printf("Grade - B"); //Grade B
    }
    else if ((score >= 70) && (score <= 79))
    {
        printf("Grade - C"); //Grade C
    }
    else if ((score >= 60) && (score <= 69))
    {
        printf("Grade - D"); //Grade D
    }
    else if ((score >= 0) && (score <= 59))
    {
        printf("Grade - F"); //Grade F
    }
    else
    {
        printf("INVALID INPUT"); //Invalid input
    }
    printf("\n+----------------------------+\n");
    goto grade; //Jump to grade line - 6

    return 0;
}