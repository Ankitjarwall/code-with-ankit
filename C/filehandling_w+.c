#include <string.h>
#include <stdlib.h>
#include <stdio.h>
int main()
{
    int i, age, age1, a, b, c, d;
    char name[20], course[20], hobby[50], name1[20], course1[20], hobby1[50];
    FILE *ca;
    ca = fopen("filehandling_write.txt", "w");
    if (ca == NULL)
    {
        printf("Sorry cann't load file.");
        exit(1);
    }
    printf("Enter the Name : ");
    gets(name);
    printf("enter the course : ");
    gets(course);
    printf("Enter the hobby : ");
    gets(hobby);
    printf("Enter the Name : ");
    gets(name1);
    printf("enter the course : ");
    gets(course1);
    printf("Enter the hobby : ");
    gets(hobby1);

    for (int i = 0; i < strlen(name); i++)
    {
        
        putc(name[i], ca);
        
    }

    for (int i = 0; i < strlen(course); i++)
    {
        putc(course[i], ca);
    }

    for (int i = 0; i < strlen(hobby); i++)
    {
        putc(hobby[i], ca);
    }
    for (int i = 0; i < strlen(name1); i++)
    {
        putc(name1[i], ca);
    }

    for (int i = 0; i < strlen(course1); i++)
    {
        putc(course1[i], ca);
    }

    for (int i = 0; i < strlen(hobby1); i++)
    {
        putc(hobby1[i], ca);
    }
    fclose(ca);
    char readme;
    FILE *read;
    read = fopen("filehandling_write.txt", "r");
    if (read == NULL)
    {
        printf("Sorry cann't load file.");
        exit(1);
    }
    readme = getc(read);
    while (!feof(read))
    {
        printf("%c", readme);
        readme = fgetc(read);
    }
    fclose(read);

    return 0;
}