#include <stdio.h>          //header file
int main()                  //main body
{
    int s, t, u, v, w;      //declaring variables
    printf("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n");
    printf("Enter the rows you want: ");
    scanf("%d", &s);        //user input rows
    printf("Enter the coloums you want : ");
    scanf("%d", &t);        //user input coloums
    printf("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n");
    int z[s][t];            //declaring array z
    for (u = 0; u < s; u++) //loop
    {
        for (v = 0; v < t; v++) //nested loop
        {
            scanf("%d", &z[u][v]); //input array values
        }
    }
    w = z[0][0];              //declaring array W
    for (u = 0; u < s; u++)     //loop
    {
        for (v = 0; v < t; v++) //nested loop
        {
            printf("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n"); //displaying index, value,address
            printf("Index: [%d][%d]    Value: %d   Address: %d \n", u, v, z[u][v], &z[u][v]);
            if (w < z[u][v])        //if condition
            {
                w = z[u][v];        //storing greatest element in W
            }
        }
    }
    printf("                                          \n");     
    printf("* * * * * * * * * * * * * * * * * * * * * *\n");
    printf("    The greatest elements is : %d", w);         //Greatest element in array
    printf("\n* * * * * * * * * * * * * * * * * * * * * *");
    return 0;
}