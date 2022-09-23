#include<iostream>
using namespace std;

void insert();

struct node
{
    int data;
    node *next;
}*start=NULL;


int main()
{
    
return 0;
}

void insert()
{
    struct node *temp;
    temp = new node();

    if (start==NULL)
    {
        cout << "Enter the element : ";
        cin >> temp->data;
        
    }
    
}