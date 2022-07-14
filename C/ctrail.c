#include <stdio.h>
#include <string.h>
int main()
{
    int a, c, d, e, f, b;
    int i, count = 0;
    printf("ENTER THE STRING:");
    scanf("%d", &a);
    scanf("%d", &c);
    scanf("%d", &d);
    scanf("%d", &e);
    scanf("%d", &f);
    printf("Enter the character you want to be searched:");
    scanf("%d", &b);
    for (i = 0; i < 1; i++)
    {
        if (b == a)
        {
            count++;
            if (b == c)
            {
                count++;
                if (b == d)
                {
                    count++;
                    if (b == e)
                    {
                        count++;
                        if (b == e)
                        {
                            count++;
                            printf("CHARACTER '%d' occurs %d times \n", b, count);
                        }
                    }
                }
            }
        }

        else
        {
            printf("THank you");
        }
    }
    return 0;
}