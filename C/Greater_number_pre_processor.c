#include <stdio.h>
#define high(a,b) ((a>b)?a:b)

int main()
{
	int value,value1,highest;

	printf("Enter first number: ");
	scanf("%d",&value);
	printf("Enter second number: ");
	scanf("%d",&value1);

	highest=high(value,value1);
	printf("Heightest number is: %d\n",highest);

	return 0;
}
