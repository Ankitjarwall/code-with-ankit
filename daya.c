#include <stdio.h> //header file.
int main()         //main body.
{
    int a = 0, b = 0, c = 0, d = 0, z = 0, p = 0, q = 0, r = 0, s = 0, t = 0, u = 0, v = 0; //variables.
    printf("Enter the values of a : ");                                                     //user input.
    scanf("%d", &a);
    printf("Enter the values of b : "); //user input.
    scanf("%d", &b);
    printf("Enter the values of c : "); //user input.
    scanf("%d", &c);
    printf("Enter the values of d : "); //user input.
    scanf("%d", &d);

    r = a++; //post increment
    printf("Unary Expression = %d\n", r);
    s = --b; //pre decrement
    printf("Unary Expression = %d\n", s);
    t = a + b;
    printf("Binary Expression = %d\n", t);
    u = c - d;
    printf("Binary Expression = %d\n", u);
    v = a + (5 * b);
    printf("Primary Expression = %d\n", v);
    z = (5 > 3) ? 1 : 0;
    printf("Ternary Expression = %d\n", z);
    return 0;
}