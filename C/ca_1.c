#include <stdio.h>
#include <conio.h>

int recursive(int);
int iterative(int);
int z = 1;
int recursive(int a)
{
	if (a == 1)
	{
		return z;
	}
	z = z * a;
	a--;
	recursive(a);
}

int iterative(int a)
{
	int i;
	z = 1;
	for (int i = a; i > 0; i--)
	{
		z = z * i;
	}

	return z;
}
int main()
{
	int digit;
	printf("Enter the Number - ");
	scanf("%d", &digit);
	printf("The recursive of %d - %d\n", digit, recursive(digit));
	printf("The Iterative of %d - %d", digit, iterative(digit));
}
