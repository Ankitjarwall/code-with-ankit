#include <iostream>
#include <stdlib.h>
using namespace std;

struct node
{
    int data;
    node *next;
} *head = NULL, *temp, *temp1;

void insert(int value)
{

    temp = (struct node *)malloc(sizeof(struct node));
    if (head == NULL)
    {
        temp->data = value;
        temp->next = NULL;
        head = temp;
    }
    else 
    {
        temp->data = value;
        temp->next = head;
        head = temp;
    }
}

void display()
{
    temp1 = head;
    while (temp1 != NULL)
    {
        cout << temp1->data << " ";
        temp1 = temp1->next;
    }
}

int main()
{
    insert(10);
    insert(20);
    insert(30);
    insert(40);

    display();
    return 0;
}
