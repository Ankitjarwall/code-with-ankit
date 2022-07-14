#include <stdio.h>
#include <conio.h>

int fact(int);
int fact1(int);
int c = 1;
int fact(int a)
{
	if (a == 1)
	{
		return c;
	}
	c = c * a;
	a--;
	fact(a);
}

int fact1(int a)
{
	int i;
	c = 1;
	for (int i = a; i > 0; i--)
	{
		c = c * i;
	}

	return c;
}
int main()
{
	int d = 5;
	printf("recursive - %d\n", fact(d));
	printf("Iterative - %d", fact1(d));
}

