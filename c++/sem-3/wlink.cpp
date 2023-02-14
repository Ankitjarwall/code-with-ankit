#include <iostream>

using namespace std;

struct node
{
    int data;
    node *next;
} *head = NULL, *temp = NULL, *newnode = NULL;

void insert(int value)
{
    newnode = new node;

    newnode->data = value;
    newnode->next = NULL;

    if (head == NULL)
    {
        head = newnode;
    }
    else
    {
        newnode->next = head;
        head = newnode;
    }
}

void display()
{
    temp = head;
    while (temp != NULL)
    {
        cout << temp->data << " ";
        temp = temp->next;
    }
}
int main()
{
    insert(20);
    insert(21);
    insert(22);
    insert(23);
    insert(24);

    display();
    return 0;
}