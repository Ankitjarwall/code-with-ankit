#include<stdio.h>
void main()
{
int month;
printf("\nEnter a month no.");
scanf("%d", &month);
switch(month)
{
case 1:
case 3:
case 5:
case 7:
case 8:
case 10:
case 12:
printf("\n31 days");
break;
case 2:
printf("\n28 days");
break;
case 4:
case 6:
case 9:
case 11:
printf("\n30 days");
break;
}
getch();
}
