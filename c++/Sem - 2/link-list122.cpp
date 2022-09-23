#include <iostream>
using namespace std;

struct node
{
    int data;
    node *next;
} *head = NULL, *temp, *nn;

void insert(int value)
{
    nn = new node;
    if (head == NULL)
    {
        nn->data = value;
        nn->next = NULL;
        head = nn;
    }
    else
    {
        nn->data = value;
        nn->next = head;
        head = nn;
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
    insert(16);
    insert(15);
    insert(14);
    insert(13);
    insert(12);
    display();
    return 0;
}