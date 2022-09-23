#include <iostream>
using namespace std;

struct bca
{
    int data;
    bca *next;
};

int main()
{
    struct bca *head;
    struct bca *first;
    struct bca *second;

    head = (struct bca *)malloc(sizeof(struct bca));
    first = (struct bca *)malloc(sizeof(struct bca));
    second = (struct bca *)malloc(sizeof(struct bca));

    head->data = 10;
    head->next = first;

    first->data = 20;
    first->next = second;

    second->data = 30;
    second->next = NULL;

    while (head != NULL)
    {
        cout << head->data << " ";
        head = head->next;
    }

    return 0;
}