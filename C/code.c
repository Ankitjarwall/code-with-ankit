#include <stdio.h>
int i = 0;

int get();
int disp();

struct employee
{
    char name[20], department[30];
    int id;
    float salary;

} emp[50];

int get()
{
    printf("Enter the Name : ");
    scanf("%s", &emp[i].name);

    printf("Enter the id : ");
    scanf("%d", &emp[i].id);

    printf("Enter the Department :");
    scanf("%s", &emp[i].department);

    printf("Enter the salary :");
    scanf("%f", &emp[i].salary);
}

int disp()
{
    printf("\nName : %s", emp[i].name);
    printf("\nId : %d", emp[i].id);
    printf("\nDepartment : %s", emp[i].department);
    printf("\nSalary : %.2f\n", emp[i].salary);
}

int main()
{
    int record;
top:
    printf("No. of Records you want to enter : ");
    scanf("%d", &record);
    if (record <= 50)
    {
        for (i = 1; i < record; i++)
        {
            emp[record];
        }

        for (i = 1; i <= record; i++)
        {
            printf("\n\n----------Record No. %d ---------\n", i);
            get();
        }
        for (i = 1; i <= record; i++)
        {
            printf("\n----------Displaying Record No. %d ---------\n", i);
            disp();
        }
    }
    else
    {
        printf("Max value limit : 50\n");
        goto top;
    }
}
