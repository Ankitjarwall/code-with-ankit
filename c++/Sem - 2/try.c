#include <malloc.h>
#include<stdio.h>
#include<conio.h>

struct node
{
    int info;
    struct node *link;
};

int main()
{

    int item, n, i;
    struct node *ptr, *START;

    printf("Enter total nummber of nodes ");
    scanf("%d", &n);
    ptr = malloc(sizeof(struct node));
    START = ptr;
    for (i = 1; i <= n; i++)
    {
        printf("Enter node number %d of list ", i);
        scanf("%d", &item);
        ptr->info = item;
        if (i == n)
            ptr->link = NULL;
        else
            ptr->link = malloc(sizeof(struct node));
        ptr = ptr->link;
    }
    ptr = START;
    while (ptr != NULL)
    {
        printf("\t%d", ptr->info);
        ptr = ptr->link;
    }
    return 0;
}