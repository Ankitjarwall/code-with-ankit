#include <iostream>

using namespace std;

struct node
{
    int data;
    node *next;
} *head = NULL, *temp, *newnode;

void insert(int value)
{
    newnode = new node;
    if (head == NULL)
    {
        newnode->data = value;
        newnode->next = NULL;
        head = newnode;
    }
    else
    {
        newnode->data = value;
        newnode->next = head;
        head = newnode;
    }
}

void display()
{
    temp = head;
    while (temp != NULL)
    {
        cout << " " << temp->data;
        temp = temp->next;
    }
}

int main()
{
    insert(10);
    insert(20);
    insert(30);
    insert(40);
    insert(50);
    display();
    return 0;
}