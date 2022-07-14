#include <stdio.h>
int main()
{
    int rows, columns, i, k, sum = 0;
    printf("Enter the matrix Rows :");
    scanf("%d", &rows);
    printf("Enter the matrix Column :");
    scanf("%d", &columns);
    int matrix1[rows][columns];
    printf("\n\t**Matrix**\n");
    for (int i = 0; i < rows; i++)
    {
        for (int k = 0; k < columns; k++)
        {
            printf("Enter Element [%d][%d] : ", i, k);
            scanf("%d", &matrix1[i][k]);
        }
    }
    printf("Odd elements are : ");
    for (int i = 0; i < rows; i++)
    {
        for (int k = 0; k < columns; k++)
        {
            if (matrix1[i][k] % 2 != 0)
            {
                sum = sum + matrix1[i][k];
                printf("%d,", matrix1[i][k]);
            }
        }
    }
    printf("\nThe sum of odd elements in a matrix : %d", sum);

    return 0;
}