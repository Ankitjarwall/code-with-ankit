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

void del_element(int ele)
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
        cout << "\nElement not found." << endl;
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
    int user_input=0,user_value=0,value_delete;
    cout<<"Enter the element number :";
    cin>>user_input;

    for(int i=0; i<user_input; i++)
    {
        cout<<"Enter the elements : ";
        cin>>user_value;
        insert_beg(user_value);
    }

    cout<<"Enter the element After you want to delete : ";
    cin>>value_delete;
    del_element(value_delete);

    traverse();
    return 0;
}
