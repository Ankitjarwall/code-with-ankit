//WRITE A PROGRAM TO COMPUTE AREA OF A GEOMATRIC SHAPE AS PER USER CHOICE USING A USER-DEFINED FUNCTION FOR EACH OPERATION.
#include <stdio.h> //header file
#include <conio.h> //header file
#include <math.h>  //header file

float circle(float radius);                    //function declaration for circle
float triangle(float a, float b, float c);     //function declaration for triangle
float parallelogram(float base, float height); //function declaration for parallelogram
float rectangle(float length, float breadth);  //function declaration for rectangle

float circle(float radius) //function defination for circle
{
    float a;
    a = (float)3.14 * radius * radius;
    return a;
}
float triangle(float a, float b, float c) //function defination for triangle
{
    float z, s;
    s = (float)(a + b + c) / 2;
    z = (float)(sqrt(s * (s - a) * (s - b) * (s - c)));
    return z;
}
float parallelogram(float base, float height) //function defination for parallelogram
{
    float a;
    a = (float)base * height;
    return a;
}
float rectangle(float length, float breadth) //function defination for rectangle
{
    float a;
    a = (float)length * breadth;
    return a;
}

int main() //main body
{
    int choice; //declaring variable
    printf("+ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ +\n");
    printf("| To find Area :\t\t\t\t\t      |\n|\tPress - 1 : CIRCLE\t\tPress - 2 : TRIANGLE  |\n");
    printf("|\tPress - 3 : PARALLELOGRAM\tPress - 4 : RECTANGLE |\n|\t\t\t\t\t\t\t   "); //user enter his/her choice
    printf("   |\n+ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - +\n");
    printf("Press : ");
    fflush(stdin);        //function
    scanf("%d", &choice); //user input
    switch (choice)       //switch case
    {
    case 1: //case 1 for Circle
    {
        float radius;
        printf("\t\tCIRCLE\nEnter Radius : ");             //if enters 1
        scanf("%f", &radius);                              //user input
        printf("\tArea of CIRCLE : %f\n", circle(radius)); //function call for circle
        break;
    }
    case 2: //case 2 for Triangle
    {
        int a, b, c;
        printf("\t\tTRIANGLE\nEnter Base : "); //if enters 2
        scanf("%d", &a);                       //user input
        printf("Enter Height : ");
        scanf("%d", &b); //user input
        printf("Enter Length : ");
        scanf("%d", &c);                                        //user input
        printf("\tArea of TRIANGLE : %f\n", triangle(a, b, c)); //function call for triangle
        break;
    }
    case 3: //case 3 for Parallelogram
    {
        float base, height, area;
        printf("\t\tPARALLELOGRAM\nEnter Base : ");                            //if enters 3
        scanf("%f", &base);                                                    //user input
        printf("Enter Height : ");                                             //if enters 3
        scanf("%f", &height);                                                  //user input
        printf("\tArea of PARALLELOGRAM : %f\n", parallelogram(base, height)); //function call for parallelogram
        break;
    }
    case 4: //case 4 for Rectangle
    {
        float length, breadth;
        printf("\t\tRECTANGLE\nEnter length : ");                         //if enters 4
        scanf("%f", &length);                                             //user input
        printf("Enter breadth : ");                                       //if enters 4
        scanf("%f", &breadth);                                            //user input
        printf("\tArea of RECTANGLE : %f\n", rectangle(length, breadth)); //function call for rectangle
        break;
    }
    default: //default
    {
        printf("Invalid Choice\n"); //if user enters number other then 4
        break;
    }
    }
    printf("\n\n\n");
    return 0;
}