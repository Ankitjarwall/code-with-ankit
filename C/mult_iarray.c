#include<stdio.h>
#include<string.h>
int main()
{
    FILE *ankit;
    char a[100],b;
    int i;
    ankit=fopen("hello.txt","w");
    printf("Enter the string :");
    gets(a);
    fprintf(ankit,"Name : ");
    for (int i = 0; i < strlen(a); i++)
    {
        fputc(a[i],ankit);
    }
    fscanf(ankit,"%s");
    fclose(ankit);
    return 0;
}