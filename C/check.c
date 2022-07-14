#include <stdio.h>
int main()
{
    int num1, num2, input;
    printf("Enter the number : ");
    scanf("%d", &num1);
    printf("Enter the number : ");
    scanf("%d", &num2);
    printf("\n\t\tA = %d, B = %d", num1, num2);

    do
    {
        printf("\n+ + + + + + + + + + + + + + + + + + + + + + + +");
        printf("\n+ PRESS 1 : &\tPRESS 2 : |\tPRESS 3 : ^   +\n+ PRESS 4 ");
        printf(": ~\tPRESS 5 : <<\tPRESS 6 : >>  +\n+\t\tEXIT 7 : EXIT\t\t      +\n");
        printf("+ + + + + + + + + + + + + + + + + + + + + + + +");
        printf("\nTask perform : ");
        scanf("%d", &input);
        switch (input)
        {
        case 1:
            printf("\n\t\ta&b = %d\n", num1 & num2);
            break;
        case 2:
            printf("\n\t\ta|b = %d\n", num1 | num2);
            break;
        case 3:
            printf("\n\t\ta^b = %d\n", num1 ^ num2);
            break;
        case 4:
            printf("\n\t\t~a = %d\n", num1 = ~num1);
            break;
        case 5:
            printf("\n\t\tb<<1 = %d\n", num2 << 1);
            break;
        case 6:
            printf("\n\t\tb>>1 = %d\n", num2 >> 1);
            break;
        case 7:
            printf("\nEXIT\nTHANK YOU! NICE TO MEET YOU.");
            goto exit;
            break;
        default:
            printf("\nInvalid input.\n");
            break;
        }
    } while (input != 0);
exit:
    return 0;
}
