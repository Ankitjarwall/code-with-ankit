#include <iostream>
using namespace std;

struct node
{
    int data;
    // node *previous;
    node *next;
} *head = NULL, *temp = NULL, *newnode = NULL;

void insert(int data)
{
    newnode = new node;

    // newnode->previous = NULL;
    newnode->data = data;
    newnode->next = NULL;

    if (head == NULL)
    {
        head = newnode;
    }
    else
    {
        newnode->next =head;
        // newnode->previous = head;
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
    insert(11);
    insert(12);
    insert(13);
    insert(14);
    display();
    return 0;
}