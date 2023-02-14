#include <iostream>

using namespace std;
struct node
{
    int data;
    node *left, *right;
} * root, *dummy, *temp, *newnode;

node *removeSuccessor(struct node *cur, struct node *temp)
{
    if (cur->left == NULL)
    {
        temp->data = cur->data;
        return cur->right;
    }
    cur->left = removeSuccessor(cur->left, temp);
}

node *Remove(struct node *cur, int data, struct node *dummy)
{
    if (cur == NULL)
    {
        return NULL;
    }
    else if (data < cur->data)
    {
        cur->left = Remove(cur->left, data, dummy);
    }
    else if (data > cur->data)
    {
        cur->right = Remove(cur->right, data, dummy);
    }
    else
    {
        dummy->data = cur->data;
        if (cur->left == NULL && cur->right == NULL)
        {
            return NULL;
        }
        else if (cur->left == NULL)
        {
            return cur->right;
        }
        else if (cur->right == NULL)
        {
            return cur->left;
        }
        else
        {
            cur->right = removeSuccessor(cur->right, temp);
            cur->data = temp->data;
        }
    }
    return cur;
}

int remove(int data)
{
    root = Remove(root, data, dummy);
    return dummy->data;
}

void create(int data)
{
    newnode = new node;
    newnode->data = data;
    newnode->left = NULL;
    newnode->right = NULL;

    if (root == NULL)
    {
        root = newnode;
    }
    else
    {
        search_me(newnode, data);
    }
}

void search_me(struct node *nnode, int data)
{
    if (root->data > data)
    {
        if (root->left==NULL)
        {
            root->left=nnode;
        }
        else{
            
        }
        
    }
    else if (root->data < data)
    {
    }
}

int main()
{

    return 0;
}