#include <iostream>
using namespace std;

struct node
{
    int data;
    node *next;
} *head = NULL, *temp, *nn, *temp2;

void insert(int value)
{
    nn = new node;
    if (head == NULL)
    {
        nn->data = value;
        nn->next = NULL;
        head = nn;
    }
    else{
        nn->data = value;
        nn->next = head;
        head = nn;
    }
}

void display()
{
    temp = head;
    while (temp!=NULL)
    {
        cout << " " << temp->data;
        temp = temp->next;
    }
    
}

void insert_end(int val){
    temp = new struct node;
    temp->data = val;
    temp->next = NULL;
    
    temp2 = head;
    while(temp2->next!=NULL){
        temp2 = temp2->next;
    }
    temp2->next = temp;
}
int main()
{
    insert(12);
    insert(13);
    insert(14);
    insert(15);
    insert(16);

    display();
    insert_end(40);
    insert_end(50);
    insert_end(60);
    insert_end(70);
    cout << endl;
    display();
    return 0;
}