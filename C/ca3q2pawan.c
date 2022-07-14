#include <stdio.h> //header file.
int main() //main body.
{
    int i, a; //declearing variable.
    float l;
    printf("Number of students : "); //user input.
    scanf("%d", &a);
    struct student //structure for students data
    {
        char Name[50];
        int roll;
        float marks;
    } s[a];
    for (i = 0; i < a; ++i) //input for students
    {
        s[i].roll = i + 1;
        printf("\nRoll number %d:\n", s[i].roll);
        printf("Name: ");
        scanf("%s", s[i].Name);
        printf("Marks: ");
        scanf("%f", &s[i].marks);
    }
    l = s[1].marks;
    printf("\n------Highest Marks------\n");

    for (i = 0; i < a; ++i)
    {
        if (l < s[i].marks)
        {
            l = s[i].marks;
        }
    }
    for (i = 0; i < a; ++i)
    {
        if (l == s[i].marks) //output
        {
            printf("* * * * * * * * * * * * *");
            printf("\n* Name\t    : %s\t*\n* Roll no   : %d\t\t*\n* Marks     : %2f *\n", s[i].Name, s[i].roll, s[i].marks);
            printf("* * * * * * * * * * * * *");
        }
    }
    return 0;
}
