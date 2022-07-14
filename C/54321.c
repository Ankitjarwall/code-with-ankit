#include <stdio.h>
#include <string.h>

int main()
{
    char string[20], str1[20], str2[20];
    int i, a = 0;
    printf("Enter the string : ");
    scanf("%s", &string);
    printf("String to be replaced : ");
    scanf("%s", &str1);
    printf("String to be placed : ");
    scanf("%s", &str2);

    for (int i = 0; i < string[i]; i++)
    {
        if (string[i] == str1[a])
        {
            string[i] = str2[a];
            a++;
        }
    }
    printf("String - %s", string);
}