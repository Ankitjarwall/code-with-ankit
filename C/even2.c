#include <stdio.h>
int main()
{
    int a, b, c, d, e;
    printf("Enter the rows : ");
    scanf("%d", &a);
    printf("Enter the coloums : ");
    scanf("%d", &b);
    printf("---Array 1----\n");
    int z[a][b];
    for (c = 0; c < a; c++)
    {
        for (d = 0; d < b; d++)
        {
            scanf("%d", &z[c][d]);
        }
    }
    printf("---Array 2----\n");
    int z1[a][b];
    for (c = 0; c < a; c++)
    {
        for (d = 0; d < b; d++)
        {
            scanf("%d", &z1[c][d]);
        }
    }
    printf("---Array 1----\n");
    for (c = 0; c < a; c++)
    {
        for (d = 0; d < b; d++)
        {
            printf("value %d\n", z[c][d]);
        }
    }
    printf("---Array 2----\n");
    for (c = 0; c < a; c++)
    {
        for (d = 0; d < b; d++)
        {
            printf("value %d\n", z1[c][d]);
        }
    }
    int y[a][b];
    for (c = 0; c < a; c++)
    {
        for (d = 0; d < b; d++)
        {
            y[c][d] = z[c][d] * z1[c][d];
        }
    }
    printf("---sub----\n");
    for (c = 0; c < a; c++)
    {
        for (d = 0; d < b; d++)
        {
            printf("%d\n", y[c][d]);
        }
    }
    return 0;
}