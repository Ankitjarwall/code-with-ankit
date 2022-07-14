#include<stdio.h>
#include<conio.h>
int main()
{
    int sub,sub1,sub2;
    float avg,avg1;
    char name[30];
    printf("\t\t\tEnter the name please : ");
    scanf("%s",&name);
    printf("\t\t\tEnter the math mark : ");
    scanf("%d",&sub);
    printf("\t\t\tEnter the english mark : ");
    scanf("%d",&sub1);
    printf("\t\t\tEnter the hindi mark : ");
    scanf("%d",&sub2);
    avg1=(sub+sub1+sub2);
    avg=avg1/3;
    printf("Name : %s \nMarks : \nMath\t:%d\nEnglish\t:%d\nhindi\t:%d\nAverage Marks:\t%f",name,sub,sub1,sub2,avg);
    if(avg>35)
    {
        printf("\n\t\t\tpass\n\n\n");
    }
    else
    {
        printf("\n\t\t\tFail\n\n\n");
    }
}
