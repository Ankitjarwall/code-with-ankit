#include <iostream>
using namespace std;

struct node
{
    int data;
    node *next;
} *head = NULL, *temp, *temp1;

void insert_beg(int value)
{
    temp = new node;
    if (head == NULL)
    {
        temp->data = value;
        temp->next = NULL;
        head = temp;
    }
    else
    {
        temp->data = value;
        temp->next = NULL;
        temp1 = head;
        while (temp1->next != NULL)
        {
            temp1 = temp1->next;
        }
        temp1->next = temp;
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
    insert_beg(10);
    insert_beg(20);
    insert_beg(30);
    insert_beg(40);

    display();
    return 0;
}