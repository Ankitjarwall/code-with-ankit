#include <stdio.h>
int main()
{
    int a, b, i;
    printf("Enter number 1: ");
    scanf("%d", &a);
    printf("Enter number 2: ");
    scanf("%d", &b);
    printf("\nPlease Wait.");
    for (i = 0; i < 10000000000; i++)
    {
        if (i == 150000000)
        {
            printf(".");
        }
        if (i == 250000000)
        {
            printf(".");
        }
        if (i == 350000000)
        {
            printf(".");
            break;
        }
        if (i == 450000000)
        {
            printf(".");
            break;
        }
    }
    for (i = 0; i < 10000000000; i++)
    {
        if (i == 150000000)
        {
            printf("\nRunning amd super processor.");
        }
        if (i == 250000000)
        {
            printf(".");
        }
        if (i == 350000000)
        {
            printf(".");
            break;
        }
    }
    printf("Done");
    for (i = 0; i < 1000000000; i++)
    {
        if (i == 1500000000)
        {
            printf("\nRunning cache memory data.");
        }
        if (i == 2500000000)
        {
            printf(".");
        }
        if (i == 3500000000)
        {
            printf(".");
            break;
        }
    }
    printf("Done");
    for (i = 0; i < 1000000000; i++)
    {
        if (i == 1500000000)
        {
            printf("\nAnalyzing your Data.");
        }
        if (i == 2500000000)
        {
            printf(".");
        }
        if (i == 3500000000)
        {
            printf(".");
            break;
        }
    }
    printf("Done");
    for (i = 0; i < 100000000000; i++)
    {
        if (i == 1500000000)
        {
            printf("\nSending your Data to NASA for analyzing.");
        }
        if (i == 2500000000)
        {
            printf(".");
        }
        if (i == 3500000000)
        {
            printf(".");
            break;
        }
    }
    for (i = 0; i < 100000000000; i++)
    {
        if (i == 1500000000)
        {
            printf("\nReceving your Data from NASA.");
        }
        if (i == 2500000000)
        {
            printf(".");
        }
        if (i == 3500000000)
        {
            printf(".");
            break;
        }
    }
    printf("Done");
    for (i = 0; i < 100000000000; i++)
    {
        if (i == 1500000000)
        {
            printf("\nAlmost there.");
        }
        if (i == 2500000000)
        {
            printf(".");
        }
        if (i == 3500000000)
        {
            printf(".");
            break;
        }
    }
    for (i = 0; i < 100000000000; i++)
    {
        if (i == 1500000000)
        {
            printf(".");
            break;
        }
    }
    printf("\nDone\n\nSum=%d", a + b);
}