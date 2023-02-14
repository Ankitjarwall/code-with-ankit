#include <stdio.h>

void swap(int *x, int *y);

void swap(int *x, int *y)
{

    int temp;

    temp = *x; /* save the value of x */
    *x = *y;   /* put y into x */
    *y = temp; /* put temp into y */
}

int main()
{
    int a = 100;
    int b = 200;

    printf("Before swap a : %d\n", a);
    printf("Before swap b : %d\n", b);

    
    swap(&a, &b);

    printf("After swap, a : %d\n", a);
    printf("After swap, b : %d\n", b);

    return 0;
}

