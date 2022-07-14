#include <stdio.h>
int main()
{
    int a, i, i1, b[10], swap;
    printf("Enter the number : ");
    scanf("%d", &a);

    for (int i = 0; i < a; i++)
    {
        printf("Enter the %d number : ", i);
        scanf("%d", &b[i]);
    }

    for (int i = 0; i < a - 1; i++)
    {
        for (int i1 = 0; i1 < a - 1 - i; i1++)
        {
            if (b[i1] > b[i1 + 1])
            {
                swap = b[i1];
                b[i1] = b[i1 + 1];
                b[1 + i1] = swap;
            }
        }
    }
    for (int i = 0; i < a; i++)
    {
        printf("The number is %d \n", b[i]);
    }
}