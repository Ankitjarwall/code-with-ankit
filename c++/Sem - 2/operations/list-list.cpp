#include <iostream>
using namespace std;

struct node
{
    int data;
    struct node *next;
};

int main()
{
    struct node *head;
    struct node *first;
    struct node *second;
    struct node *third;
    struct node *forth;

    head = new node();
    first = new node();
    second = new node();
    third = new node();
    forth = new node();

    head->data = 10;
    head->next = first;

    first->data = 20;
    first->next = second;

    second->data = 30;
    second->next = third;

    third->data = 40;
    third->next = forth;

    forth->data = 50;
    forth->next = NULL;

    while (head != NULL)
    {
        cout << head->data << " ";
        head = head->next;
    }

    return 0;
}