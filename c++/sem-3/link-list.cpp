#include <iostream>
using namespace std;

struct node
{
    int data;
    node *next;
} *head = NULL, *temp = NULL;

void traverse()
{
    temp = head;
    while (temp != NULL)
    {
        cout << temp->data << " ";
        temp = temp->next;
    }
}
// void traverse(struct node *temp)
// {
//     if (temp != NULL)
//     {
//         cout << temp->data << " ";
//         traverse(temp->next);
//     }
// }

void insert(int value)
{
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
    struct node *newnode;
    newnode = new node;

    temp = head;

    while (temp->next != NULL)
    {
        temp = temp->next;
    }
    newnode->data = value;
    newnode->next = NULL;

    temp->next = newnode;
}

void insert_mid(int value, int position)
{
    temp = head;

    struct node *newnode;
    newnode = new node;
    newnode->data = value;

    while (temp != NULL)
    {
        if (temp->data == position)
        {
            newnode->next = temp->next;
            temp->next = newnode;
            return;
        }
        temp = temp->next;
    }
}

void insert_before(int value, int position)
{
    temp = head;
    node *temp1;
    node *newnode;
    newnode = new node;

    newnode->data = value;
    while (temp->next != NULL)
    {
        if (temp->next->data == position)
        {
            temp1 = temp->next;
            temp->next = newnode;
            newnode->next = temp1;
        }
        temp = temp->next;
    }
}

void search(int value)
{
    temp = head;
    while (temp != NULL)
    {
        if (temp->data == value)
        {
            cout << "value found : " << value << endl;
            return;
        }
        temp = temp->next;
    }
    cout << "Value not found.";
}

void fdelete(int value)
{
    temp = head;
    while (temp != NULL)
    {
        if (temp->next->data==value)
        {
            delete temp->next;
            temp->next=temp->next->next;
            // cout << "Value : " << temp->data<<" ";
            return;
        }
        
        temp = temp->next;
    }
}

int main()
{
    insert(18);
    insert(21);
    insert(22);

    insert_end(17);

    int after_element = 21;
    int before_element = 18;
    insert_mid(20, after_element);
    insert_before(19, before_element);

    search(18);
    fdelete(18);

    traverse();
    // traverse(head);
    return 0;
}