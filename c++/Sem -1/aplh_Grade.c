#include <stdio.h> //header file.

int main() //main body.
{
    char grade; //variable.
Menu:
    printf("\n+----------------------------+");
    printf("\n| Please enter the Grade : ");
    fflush(stdin);
    scanf("%c", &grade); //user input.
    printf("| ");
    switch (grade)
    {
    case 'A':
    case 'a': //Grade - A,a.
        printf("Excellent job!!\t     |\n");
        break;
    case 'B':
    case 'b': //Grade - B,b.
        printf("Keep it up!!\t\t     |\n");
        break;
    case 'C':
    case 'c': //Grade - C,c.
        printf("Great job!!\t\t     |\n");
        break;
    case 'D':
    case 'd': //Grade - D,d.
        printf("Need to work!!\t     |\n");
        break;
    case 'E':
    case 'e': //Grade - E,e.
        printf("Work hard!!\t\t     |\n");
        break;
    case 'F':
    case 'f': //Grade - F,f.
        printf("Fail,Try again!!\t     |\n");
        break;
    case 'Q':
    case 'q': //Grade - F,f.
        printf("Thank you!!\t     |\n");
        return 0;
        break;

    default: //Invalid Input.
        printf("Invalid input...\t     |\n");
        break;
    }
    printf("+----------------------------+\n");
    goto Menu; //Jump to grade line - 6.

    return 0;
}