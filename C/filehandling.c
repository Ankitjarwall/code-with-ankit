#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main()
{
    FILE *ankit;
    int i;
    char string[100];
    ankit = fopen("sonam", "w");
    printf("Enter the string : ");
    gets(string);
    if (ankit == NULL)
    {
        printf("File cannot be open");
        exit(1);
    }
    for (int i = 0; i < strlen(string); i++)
    {
        fputc(string[i], ankit);
    }
    fclose(ankit);
    printf("Length = %d ", strlen(string));
}