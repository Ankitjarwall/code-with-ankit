#include <stdio.h>
#include <string.h>

int sum(int a,int b);

int sum(int a, int b){
	int c;
	c=a+b;
	return c;
}

int main()
{
	int a=5,b=5,c;
	c=sum(a,b);
	printf(" %d",c);
	int a=10,*p;
	p=&a;
	printf("The value of A - %d, address of A is %d ",*p,p);

	
	return 0;
}


