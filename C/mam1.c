#include<stdio.h>

int main()
{
    int age;
    char name;
    printf("Enter the age : ");
    scanf("%d",&age);
    printf("IF STATEMENT\n");
    if (age<10)
        {
    printf("DO WHILE LOOP\n");
    do{
        printf("\t\t hello ankit %d......\n",age);
        age++;
    }while(age<=5);

    printf("FOR LOOP\n");

    for(age;age<=10;age++)
    {
        printf("\t\t hello sahil %d......\n",age);
    }

    printf("WHILE LOOP\n");

    while(age<=15)
    {
        printf("\t\t hello ankit sha %d.....\n",age);
        age++;
    }

    printf("GOTO /jump STATEMENT\n");
    goto age1;
    printf("Chup sale.");
    printf("Ye sale sab milke humko bevkuf bana rahe h.");
    age1:{
    printf("Hello all of you\n\n");
    }
        }
    else{
        printf("ELSE STATEMENT\n");
        printf("\t\t Thank you for using\n\n");
    }
    printf("FINAL OUTPUT \t\tThe age is %d \n\n",age);

}
