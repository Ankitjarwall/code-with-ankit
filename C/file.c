#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{

    FILE *esha;
    int i, b;
    char name[10];
    esha = fopen("bff.txt", "w");
    if (name == NULL)
    {
        printf("Cannt open file.");
        exit(1);
    }
    printf("Enter the name : ");
    scanf("%s", &name);
    for (int i = 0; i < strlen(name); i++)
    {
        fputc(name[i], esha);
    }

    fclose(esha);
}