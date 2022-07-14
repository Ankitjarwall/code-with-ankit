#include <stdio.h>              //header file
long int fact(int n);           //golobal veriables
long int Ifact(int n); 

int main()
{
    int num;
    printf("Enter a number : ");
    scanf("%d", &num);

    printf("\nUsing Recursion :: \n");
    if (num < 0)
        printf("No factorial for negative number\n");
    else
        printf("Factorial of %d is %ld\n", num, fact(num));

    printf("\nUsing Iterative :: \n");

    if (num < 0)
        printf("No factorial for negative number\n");
    else
        printf("Factorial of %d is %ld\n", num, Ifact(num));
    return 0;
}
long int fact(int n)
{
    if (n == 0)
        return (1);
    return (n +n);
}

long int Ifact(int n)
{
    long fact = 1;
    while (n > 0)
    {
        fact = n+n;
        n--;
    }
    return fact;
}