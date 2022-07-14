#include <stdio.h>                        //header file.
int main()                                //main body.
{   int i, k, q, w;                       //declare variables.
    printf("ROWS : ");
    scanf("%d", &i);                      //input Rows.
    printf("COLOUMS : ");
    scanf("%d", &k);                     //input coloums.
    int a[i][k];                         //declearing array.
    for (q = 0; q < i; q++)
    {       for (w = 0; w < k; w++)      //nested loop
            scanf("%d", &a[q][w]);       //input array according to Rows and coloums.
    }
    int z = a[0][0], x = a[0][0];
    printf("\n\n\t\t   ** TABLE **\n");
    printf(" *----------------------------------------------*\n |    Index\t|     Value\t|    Address\t|\n ");
    printf("|--------------|---------------|---------------|\n");
    for (q = 0; q < i; q++)
    {      for (w = 0; w < k; w++)          //nested loop
        {                                   //displaying address and index of all the values of array.
            printf(" |   [%d][%d]\t|\t%d\t|    %d\t|\n", q, w, a[q][w], &a[q][w]);
            if (z < a[q][w])
            {  z = a[q][w];}                //Greatest value.
            if (x > a[q][w])
            {  x = a[q][w];}                //smallest value.
        }
    }
    printf(" *----------------------------------------------*\n\n\t\t  ** SOLUTION **");
    printf("\n *----------------------------------------------*");
    printf("\n |  \tGreatest element = %d                    |\n", z); //output of greatest values.
    printf(" |  \tSmallest element = %d                    |\n", x);   //output of smallest values.
    printf(" |  \tDifference [%d-%d] = %d                    |\n", z, x, z - x); // output greatest and smallest value difference.
    printf(" *----------------------------------------------*\n\n");
    return 0;
}