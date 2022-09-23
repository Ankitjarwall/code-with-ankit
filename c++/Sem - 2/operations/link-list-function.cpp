#include <iostream>
using namespace std;

void insert();
void display();

struct node
{
    int data;
    struct node *next;
} *head = NULL;

int main()
{
    
    insert();
    insert();
    insert();
    insert();

    display();

    return 0;
}

void insert( )
{
    struct node *temp;
    temp = new node();

    if (head == NULL)
    {
        cout << "Enter the element : ";
        cin >> temp->data;
        
        temp->next=NULL;
        head = temp;
    }
    else
    {
        cout << "Enter the element : ";
        cin >> temp->data;
        temp->next=head;
        head = temp;
    }
}

void display()
{
    struct node *temp;
    temp = head;

    while (temp != NULL)
    {
        cout << temp->data << " ";
        temp = temp->next;
    }
}