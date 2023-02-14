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

void insert_end(int value)
{
    temp = head;
    newnode = new node;

    newnode->data = value;
    newnode->next = NULL;

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

void insert_mid(int value, int pos)
{
    temp = head;
    newnode = new node;

    newnode->data = value;

    if (head == NULL)
    {
        head = newnode;
    }
    else
    {
        while (temp != NULL)
        {
            if (temp->data == pos)
            {

                newnode->next = temp->next;
                temp->next = newnode;
                return;
            }
            temp = temp->next;
        }
    }
}

void deleteNode(int data)
{
    temp = head;
    while (temp != NULL)
    {
        if (temp->next->data == data)
        {
            node *loc = temp -> next;
            temp->next = temp->next->next;
            free(loc);
            return;
            
        }
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

    insert_end(25);
    insert_end(26);
    insert_end(27);
    insert_end(28);
    insert_end(29);

    insert_mid(99, 20);
    deleteNode(99);
    display();
    return 0;
}