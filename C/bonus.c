#include<stdio.h>
#include<conio.h>
int main()
{
    int salary,salary1,salary2,salary3, bonus;
    char name[20],name1[20],name2[20],name3[20];
    //name
    printf("Name :");
    scanf("%s",&name);
    printf("Salary :");
    scanf("%d",&salary);
    //name1
    printf("Name 2 :");
    scanf("%s",&name1);
    printf("Salary :");
    scanf("%d",&salary1);
    //name2
    printf("Name 3 :");
    scanf("%s",&name2);
    printf("Salary :");
    scanf("%d",&salary2);
    //name3
    printf("Name 4 :");
    scanf("%s",&name3);
    printf("Salary :");
    scanf("%d",&salary3);
    //print
    printf("The total salary of Employs :\n Name :%s\n Salary :%d\n Name :%s\n Salary :%d\n Name :%s\n Salary :%d\n Name :%s\n Salary :%d\n",name,salary,name1,salary1,name2,salary2,name3,salary3);

    if(salary<=10000)
        printf("You are given 20% bonus.");
    else if ()
    return (0);
}
