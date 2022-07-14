#include <stdio.h>

#define high(x,y) ((x>y)?x:y)

int main()
{
	int a,b,max;
	
	printf("Enter first number: ");
	scanf("%d",&a);
	printf("Enter second number: ");
	scanf("%d",&b);	
	
	max=high(a,b);
	printf("Maximum number is: %d\n",max);
	
	return 0;
}