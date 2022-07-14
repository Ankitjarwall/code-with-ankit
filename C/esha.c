#include <stdio.h>
#include<math.h>

void main()
{
    int x, y, t, q;
    int c = -1, k, p;

    printf("Enter the value of x =");
    scanf("%d", &x);
    printf("Enter the value of y =");
    scanf("%d", &y);
    k = x - y;
    t = k;
    while (q != 0)
    {
        q = t / 10;
        t = q;
        c++;
    }
    p = pow(10, c);
    if (k % p == 0)
        printf("\nNO,x=%d is not contained in y=%d", x, y);
    else
    {
        printf("\nYES,x=%d is contained in y=%d", x, y);
    }
}