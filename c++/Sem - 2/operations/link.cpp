#include <iostream>

using namespace std;

class node
{
public:
    int data;
    node *next;

    node(int value)
    {
        data = value;
        next = NULL;
    };
};

void insertAtTail(node *&head, int input)
{
    node *newnode = new node(input);

    if (head == NULL)
    {
        head = newnode;
        return;
    }
    node *temp = head;
    while (temp->next != NULL)
    {
        temp = temp->next;
    }
    temp->next = newnode;
}

void display(node *head)
{
    node *temp = head;

    while (temp->next != NULL)
    {
        cout << " " << temp->data;
        temp = temp->next;
    }
    cout << endl;
}

bool search(node *head, int key)
{
    node *temp = head;
    while (temp != NULL)
    {
        if (temp->data == key)
        {
            return true;
        }
        temp=temp->next;
    }
    return false;
}

void insertAthead(node *&head, int val)
{
    node *newnode = new node(val);
    newnode->next = head;
    head = newnode;
}

int main()
{
    node *head = NULL;
    insertAtTail(head, 1);
    insertAtTail(head, 2);
    insertAtTail(head, 3);
    insertAtTail(head, 4);
    insertAtTail(head, 5);
    display(head);

    insertAthead(head, 0);
    insertAthead(head, 9);
    insertAthead(head, 8);
    display(head);

    cout<<search(head,3);

    return 0;
}