#include <iostream>
using namespace std;

struct bca
{
    int data;
    struct bca *next;
};

int main()
{
    struct bca head;
    struct bca first;
    struct bca second;

    head.data = 1;
    head.next = &first;

    first.data = 2;
    first.next = &second;

    second.data = 3;
    second.next = NULL;

    cout << head.data;
    cout << head.next;
    cout << &first;

    return 0;
}