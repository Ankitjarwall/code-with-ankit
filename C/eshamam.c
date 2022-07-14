#include <stdio.h>
#include <conio.h>
#include <string.h>

int main()
{
    char b[20], c[20];
    int a = 0, d = 0, z;

menu:
    printf("\t--------MAIN MENU--------\n");
    printf("\n\t\tFor Task PRESS\nReverse \t- 1.\t Uppercase \t- 2.\n");
    printf("Find Length \t- 3.\t Lowercase \t- 4.\n");
    printf("Copy \t\t- 5.\t Exit \t\t- 6.\nMAIN MENU \t-7.\n");
    printf("Enter String 1: ");
    scanf("%s", b);
    printf("Enter String 2: ");
    scanf("%s", c);

    do
    {
    taskmenu:
        printf("\nTask no : ");
        fflush(stdin);
        scanf("%d", &a);

        switch (a)
        {

        case 1:
        {
            printf("\nBEFORE\t\tAFTER\n");
            printf("String\t\tReverse");
            printf("\n%s\t\t", b); //before
            strrev(b);
            printf("%s \n", b); //after
            break;
        }
        case 2:
        {
            printf("\nBEFORE\t\tAFTER\n");
            printf("String\t\tUpper Case");
            printf("\n%s\t\t", b); //before
            strupr(b);
            printf("%s \n", b); //after
            break;
        }
        case 3:
        {
            printf("\nBEFORE\t\tAFTER\n");
            printf("String\t\tLength");
            printf("\n%s\t\t", b); //before

            printf("%d \n", strlen(b)); //after
            break;
        }
        case 4:
        {
            printf("\nBEFORE\t\tAFTER\n");
            printf("String\t\tLower Case");
            printf("\n%s\t\t", b); //before
            strlwr(b);
            printf("%s \n", b); //after
            break;
        }
        case 5:
        {

            printf("\nBEFORE\t\tAFTER\n");
            printf("String\t\t Copy");
            printf("\n%s \t\t", b); //before
            printf("\n%s \t\t", c); //before
            strcpy(b, c);
            printf("%s \n", b); //after
            printf("%s \n", c); //after
            break;
        }
        case 6:
        {
            printf("\nNice to meet you.\n");
            break;
        }
        case 7:
        {
            goto menu;
        }

        default:
        {
            printf("\nPlease Enter the valid Task no. from Main menu\nMAIN MENU press - 7\n");
            d++;

            if (d == 5)
            {
                goto exit;
            }
        }
        }

    } while (a == 1 || a == 2 || a == 3 || a == 4 || a == 5);

exit:

    getch();
    return 0;
}
