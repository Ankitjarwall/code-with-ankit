#include <stdio.h>
int main()
{
	int rows, columns, i, k;
	printf("Enter the matrix Rows :");
	scanf("%d", &rows);
	printf("Enter the matrix Column :");
	scanf("%d", &columns);
	int matrix1[rows][columns];
	printf("\n\t**Matrix 1**\n");
	for (int i = 0; i < rows; i++)
	{
		for (int k = 0; k < columns; k++)
		{
			printf("Enter Element [%d][%d] : ",i,k);
			scanf("%d",&matrix1[i][k]);
		}
	}
	int matrix2[rows][columns];
	printf("\n\t**Matrix 2**\n");
	for (int i = 0; i < rows; i++)
	{
		for (int k = 0; k < columns; k++)
		{
			printf("Enter Element [%d][%d] : ",i,k);
			scanf("%d",&matrix2[i][k]);
		}
	}
	int sum[rows][columns];
	printf("\n\t\t**Sum**\n     Matrix 1   +    Matrix 2   =\tSum\n");
	for (int i = 0; i < rows; i++)
	{
		for (int k = 0; k < columns; k++)
		{
			sum[i][k] = matrix1[i][k] + matrix2[i][k];
			printf("\t%d\t+\t%d\t=\t%d\n",matrix1[i][k], matrix2[i][k], sum[i][k]);
		}
	}

	return 0;
}