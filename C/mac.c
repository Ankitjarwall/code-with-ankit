#include <stdio.h>

int main()
{
    int input=0;

    printf("Enter First number: ");
    scanf("%d", &input);
    
    if (0==input%2)
    {
        printf("%d is even number.", input);
    }
    else{
        printf("%d is odd number.", input);
    }

    return 0;
}
