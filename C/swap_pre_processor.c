#include <stdio.h>
#define swap(a, b) (b = (a + b) - (a = b))

int main()
{
    int value, value1, result;
    printf("Enter the first number : ");
    scanf("%d", &value);

    printf("Enter the second number : ");
    scanf("%d", &value1);

    printf("\n-------Before swap-------\n    First number : %d\n    Second number : %d\n", value, value1);
    result = swap(value, value1);
    printf("\n-------After swap-------\n     First number : %d\n    Second number : %d\n", value, value1);
    return 0;
}
