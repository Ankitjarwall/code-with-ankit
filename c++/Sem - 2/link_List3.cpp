#include <iostream>
using namespace std;

struct link_list
{
    int data;
    struct link_list *next;
};

void transverse(struct link_list *head)
{
    while (head != NULL)
    {
        cout << "Value 1 : " << head->data << endl;
        head = head->next;
    }
}

int main()
{

    struct link_list *head;
    struct link_list *first;
    struct link_list *second;
    struct link_list *three;
    struct link_list *four;
    struct link_list *five;

    head = (struct link_list *)malloc(sizeof(struct link_list));
    first = (struct link_list *)malloc(sizeof(struct link_list));
    second = (struct link_list *)malloc(sizeof(struct link_list));
    three = (struct link_list *)malloc(sizeof(struct link_list));
    four = (struct link_list *)malloc(sizeof(struct link_list));
    five = (struct link_list *)malloc(sizeof(struct link_list));

    head->data = 20;
    head->next = first;

    first->data = 30;
    first->next = second;

    second->data = 50;
    second->next = three;

    three->data = 40;
    three->next = four;

    four->data = 70;
    four->next = five;

    five->data = 90;
    five->next = NULL;

    transverse(head);

    return 0;
}