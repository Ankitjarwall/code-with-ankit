#include <stdio.h>
struct record1
{
	int id;
	float salary;
	char name[20], dep[20];

} rc, rc1,rc2;

int main()
{
	printf("**Employer-1***\n");
	printf("Employer Name : ");
	scanf("%s", &rc.name);
	printf("Employer id : ");
	scanf("%d", &rc.id);
	printf("Employer dep : ");
	scanf("%s", &rc.dep);
	printf("Employer Salary : ");
	scanf("%f", &rc.salary);

	printf("**Employer-2***\n");

	printf("Employer Name : ");
	scanf("%s", &rc1.name);
	printf("Employer id : ");
	scanf("%d", &rc1.id);
	printf("Employer dep : ");
	scanf("%s", &rc1.dep);
	printf("Employer Salary : ");
	scanf("%f", &rc1.salary);

	printf("**Employer-3***\n");

	printf("Employer Name : ");
	scanf("%s", &rc2.name);
	printf("Employer id : ");
	scanf("%d", &rc2.id);
	printf("Employer dep : ");
	scanf("%s", &rc2.dep);
	printf("Employer Salary : ");
	scanf("%f", &rc2.salary);

	printf("*********************************\n");
	printf("Name\tEmp ID\tDep\tsalary\n");
	printf(" %s\t%d\t%s\t%f\n %s\t%d\t%s\t%f\n %s\t%d\t%s\t%f\n", rc.name, rc.id, rc.dep, rc.salary, rc1.name, rc1.id, rc1.dep, rc1.salary, rc2.name, rc2.id, rc2.dep, rc2.salary);
	printf("\n*********************************\n\n");
	if(rc.salary>rc1.salary){
		if(rc.salary>rc2.salary){
			printf("\nThe highest salary is of Employ -1.\n");
			printf("Name\tEmp ID\tDep\tsalary\n");
			printf("%s\t%d\t%s\t%d",rc.name,rc.id,rc.dep,rc.salary);
		}
	}
	if(rc1.salary>rc.salary){
		if(rc1.salary>rc2.salary){
			printf("\nThe highest salary is of Employ -1.\n");
			printf("Name\tEmp ID\tDep\tsalary\n");
			printf("%s\t%d\t%s\t%d",rc.name,rc.id,rc.dep,rc.salary);
		}
	}
	if(rc2.salary>rc.salary){
		if(rc2.salary>rc1.salary){
			printf("\nThe highest salary is of Employ -1.\n");
			printf("Name\tEmp ID\tDep\tsalary\n");
			printf("%s\t%d\t%s\t%d",rc.name,rc.id,rc.dep,rc.salary);
		}
	}

	return 0;
}