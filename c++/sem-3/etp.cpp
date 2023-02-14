#include <iostream>
using namespace std;

struct node
{
    int data;
    node *next;
} * head, *temp, *newnode;

void insert(int data)
{
    temp = new node;

    temp->data = data;
    temp->next = NULL;

    if (head == NULL)
    {
        head = temp;
    }
    else
    {
        temp->next = head;
        head = temp;
    }
}

void insert_end(int data)
{
    newnode = new node;

    newnode->next = NULL;
    newnode->data = data;

    temp = head;

    if (head == NULL)
    {
        head = newnode;
    }
    else
    {
        while (temp->next != NULL)
        {
            temp = temp->next;
        }
        temp->next = newnode;
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
    insert_end(30);
    insert_end(60);
    insert_end(20);
    insert_end(40);

    display();
    return 0;
}