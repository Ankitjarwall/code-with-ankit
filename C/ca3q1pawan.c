#include <stdio.h>              //header file.
int main()                     //main body.
{ 
    char string[150];           //declearing variable.
    int vowels, consonant, a;

    vowels = consonant = 0;

    printf("\nEnter a string: ");   //user input.
    fflush(stdin);
    scanf("%s",&string);

    for (int i = 0; string[i] != '\0'; ++i)   //checking vowels & consonant.
    {
        if (string[i] == 'a' || string[i] == 'e' || string[i] == 'i' ||   //condition for vowels.
            string[i] == 'o' || string[i] == 'u' || string[i] == 'A' ||
            string[i] == 'E' || string[i] == 'I' || string[i] == 'O' ||
            string[i] == 'U')
        {
            ++vowels;
        }
        else if ((string[i] >= 'a' && string[i] <= 'z') || (string[i] >= 'A' && string[i] <= 'Z'))
        {                                                                   //condition for consonant.
            ++consonant;
        }
    }
    printf("String : %s", string);                                          //ouput.
    printf("\n* * * * * * * * * * * * * * * * * * *");
    printf("\n*\t* Vowels *\tConsonants  *");
    printf("\n* Count *   %d\t *\t    %d\t    *", vowels, consonant);
    printf("\n*\t*\t *\t\t    *\n");
    printf("* * * * * * * * * * * * * * * * * * *\n");
    return 0;
}