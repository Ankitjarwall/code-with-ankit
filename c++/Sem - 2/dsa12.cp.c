#include <iostream>
using namespace std;
struct node
{
    int data;
    node *next;
} *start = NULL, *nn, *temp, *loc;

// INSERTION AT THE BEGINING.
void insert_beg(int value)
{
    nn = (struct node *)malloc(sizeof(struct node));
    nn->data = value;
    if (start == NULL)
    {
        nn->next = NULL;
        start = nn;
    }
    else
    {
        nn->next = start;
        start = nn;
    }
}

void del_ele(int ele)
{
    temp = start;

    while (temp->next->data != ele)
    {
        temp = temp->next;
        if (temp->next->data == ele)
        {
            loc = temp->next;
        }
        else
        {
            loc = NULL;
        }
    }
    if (loc == NULL)
    {
        cout << "Element not found." << endl;
    }
    else
    {
        cout << "Element deleted." << endl;
        temp->next = temp->next->next;
        delete loc;
    }
}

// TRAVERSING A LINKED LIST.
void traverse()
{
    if (start == NULL)
    {
        cout << "\nLinked list is empty" << endl;
        return;
    }
    temp = start;
    cout << "\nlinked list is: " << endl;
    while (temp != NULL)
    {
        cout << temp->data << " ";
        temp = temp->next;
    }
}

int main()
{
    insert_beg(6);
    insert_beg(4);
    insert_beg(3);
    insert_beg(2);

    traverse();
    del_ele(4);
    traverse();
    return 0;
}
