#include <iostream>
#include <stdlib.h>

using namespace std;

struct node
{
    int data;
    node *left;
    node *right;
};

void search(node *temp, int element)
{
    if (temp != NULL)
    {
        // cout << " " << temp->data;

        if (temp->data == element)
        {
            cout << "Element found : " << temp->data;
            return;
        }

        search(temp->left, element);
        search(temp->right, element);
    }
}
void preorder(node *temp)
{
    if (temp != NULL)
    {
        cout << " " << temp->data;
        preorder(temp->left);
        preorder(temp->right);
    }
}
void inorder(node *temp)
{
    if (temp != NULL)
    {
        inorder(temp->left);
        cout << " " << temp->data;
        inorder(temp->right);
    }
}

void postorder(node *temp)
{
    if (temp != NULL)
    {
        postorder(temp->left);
        postorder(temp->right);
        cout << " " << temp->data;
    }
}

node *create_node(int value)
{
    node *newnode = new node;
    newnode->data = value;
    newnode->left = NULL;
    newnode->right = NULL;

    return (newnode);
}

int main()
{
    node *root = create_node(10);
    root->left = create_node(5);
    root->right = create_node(15);
    root->left->left = create_node(2);
    root->left->right = create_node(6);

    cout << "pre order" << endl;
    preorder(root);
    cout << "\nin order " << endl;
    inorder(root);
    cout << "\npost order" << endl;
    postorder(root);

    cout << "\n\nsearch : ";
    search(root, 15);

    return 0;
}