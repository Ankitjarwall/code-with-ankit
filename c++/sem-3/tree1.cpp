#include <iostream>
using namespace std;

struct node
{
    int data;
    node *left;
    node *right;
} *root = NULL, *newnode = NULL;

void display(node *temp)
{
    if (temp != NULL)
    {
        cout << " " << temp->data;
        display(temp->left);
        display(temp->right);
    }
}

node *create(node *current, int data)
{
    if (current == NULL)
    {
        newnode = new node;
        newnode->data = data;
        newnode->left = NULL;
        newnode->right = NULL;

        return newnode;
    }
    else if (current->data > data)
    {
        current->left = create(current->left, data);
    }
    else if (current->data < data)
    {
        current->right = create(current->right, data);
    }
    return current;
}

void insert(int data)
{
    root = create(root, data);
}

int main()
{
    insert(20);
    insert(21);
    insert(22);
    insert(23);
    insert(24);

    display(root);
    return 0;
}