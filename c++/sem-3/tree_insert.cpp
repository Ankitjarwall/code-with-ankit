#include <iostream>
using namespace std;

struct node
{
    int data;
    node *left;
    node *right;
} *root = NULL, *newnode;

void inorder(node *temp)
{
    if (temp != NULL)
    {
        inorder(temp->left);
        cout << temp->data << " ";
        inorder(temp->right);
    }
}

node *create(node *cur, int data)
{
    if (cur == NULL)
    {
        newnode = new node;
        newnode->data = data;
        newnode->left = NULL;
        newnode->right = NULL;
        return newnode;
    }
    else if (cur->data > data)
    {
        cur->left = create(cur->left, data);
    }
    else if (cur->data < data)
    {
        cur->right = create(cur->right, data);
    }
    return cur;
}

void insert(int data)
{
    root = create(root, data);
}

int main()
{
    insert(11);
    insert(1);
    insert(12);
    insert(140);

    inorder(root);
    return 0;
}