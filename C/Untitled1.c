#include <stdio.h>

int main(void)
{
    
    FILE *fptr;
    char ch;
    fptr = fopen("username.txt", "w");
    printf("Enter your name: ");
    while ((ch = getchar()) != '\n')
    {
        putc(ch, fptr);
    }
    fclose(fptr);
    
    fopen("username.txt", "r");
    printf("\nFile content:\n");
    while ((ch = getc(fptr)) != EOF)
    {
        printf("%c", ch);
    }
    printf("\nEnd of file\n");
    fclose(fptr);

    return 0;
}