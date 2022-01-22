#include <stdio.h>
int main()
{
    int area, sign, circle, pi = 3.14, radius, length, width;
    int ractangle, side, square, base, height, triangle;
    char exit;
    do
    {    
    printf("\tCircle - 1\tRectangle - 2\n\tSquare - 3\ttriangle - 4\n\t\t\tExit - 5\n");
    printf("\nEnter the no : ");
    scanf("%d", &sign);
    if (sign == 1)
    {
        printf("\t\t**CIRCLE**\nEnter the Radius : ");
        scanf("%d", &radius);
        circle = 2 * pi * radius;
        printf("The area of circle is : %d", circle);
    }
    else if (sign == 2)
    {
        printf("\t\t**RECTANGLE**\nEnter the lenght : ");
        scanf("%d", &length);
        printf("Enter the Width : ");
        scanf("%d", &width);
        ractangle = length * width;
        printf("The area of rectangle is : %d ", ractangle);
    }
    else if (sign == 3)
    {
        printf("\t\t**SQUARE**\nEnter the side : ");
        scanf("%d", &side);
        square = side * side;
        printf("The area of Square is : %d ", square);
    }
    else if (sign == 4)
    {
        printf("\t\t**TRIANGLE**\nEnter the Base side : ");
        scanf("%d", &base);
        printf("Enter the Height : ");
        scanf("%d", &height);
        triangle =base * height;
        printf("The area of Triangle is : %d ", triangle/2);
    }
    else
    {
        printf("INVALID INPUT\nPlease choose correct option from the menu.");
        
    }
    printf("\nContinue Press - y\n Exit Press n\nWant to continue ? ");
    fflush(stdin);
    scanf("%c",&exit);
    } while (exit=='y');
    printf("Thank you");
}