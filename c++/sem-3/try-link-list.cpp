#include <iostream>
using namespace std;

struct node
{
    int data;
    node *next;
} *head = NULL;

void display()
{
    node *temp;
    temp = head;

    while (temp != NULL)
    {
        cout << temp->data << " ";
        temp = temp->next;
    }
}

void search(int element)
{
    node *temp;
    temp = head;

    while (temp != NULL)
    {
        if (temp->data == element)
        {
            cout << "Element found : " << element << endl;
            return;
        }
        temp = temp->next;
    }
    cout << "Element not found." << endl;
}

void insert(int value)
{
    node *temp;
    temp = new node;

    temp->data = value;
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

void insert_end(int value)
{
    node *temp;
    temp = head;

    node *newnode;
    newnode = new node;

    newnode->data = value;
    newnode->next = NULL;

    while (true)
    {
        if (temp->next==NULL)
        {
            temp->next = newnode;
            return;
        }
        temp=temp->next;
    }
}

int main()
{
    insert(10);
    insert(11);
    insert(12);
    insert(13);

    insert_end(11);
    insert_end(12);
    insert_end(13);
    insert_end(14);
    
    display();

    return 0;
}