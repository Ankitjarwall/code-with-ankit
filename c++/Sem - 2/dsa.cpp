#include <iostream>
using namespace std;
struct node
{
    int data;
    node *next;
} *start = NULL, *next_node, *temp, *loc;

// SEARCHING FOR A PARTICULAR ELEMENT.IT RETURNS THE NODE WHERE THE ELEMENT IS FOUND.
node *search(int element)
{
    temp = start;
    while (temp != NULL)
    {
        if (temp->data == element)
        {
            loc = temp;
            return loc;
        }
        temp = temp->next;
    }
    return NULL;
}

// INSERTION AT THE BEGINING.
void insert_beg(int value)
{
    next_node = (struct node *)malloc(sizeof(struct node));
    next_node->data = value;
    if (start == NULL)
    {
        next_node->next = NULL;
        start = next_node;
    }
    else
    {
        next_node->next = start;
        start = next_node;
    }
}

// INSERTION AFTER THE GIVEN ELEMENT.FIRST SEARCH THE ELEMENT, STORE THE NODE IN TEMP AND THEN INSERT THE NODE AFTER TEMP NODE.
void insert_after_element(int element, int value)
{
    next_node = (struct node *)malloc(sizeof(struct node));
    temp = search(element);
    if (temp == NULL)
    {
        cout << "\nElement not found." << endl;
    }
    else
    {
        next_node->data = value;
        next_node->next = temp->next;
        temp->next = next_node;
    }
}

// INSERTION AT THE END.
void insert_end(int value)
{
    next_node = (struct node *)malloc(sizeof(struct node));
    next_node->data = value;
    next_node->next = NULL;
    if (start == NULL)
    {
        start = next_node;
    }
    else
    {
        temp = start;
        while (temp->next != NULL)
        {
            temp = temp->next;
        }
        temp->next = next_node;
    }
}

// DELETION AT THE BEGINING.
void del_beg()
{
    if (start == NULL)
    {
        cout << "\nThere is no node to delete." << endl;
    }
    else
    {
        temp = start;
        start = start->next;
        cout << "\nDeleted element is: " << temp->data << endl;
        delete temp;
    }
}

// DELETION AFTER GIVEN ELEMENT.
void del_after_element(int element)
{
    temp = search(element);
    if (temp == NULL)
    {
        cout << "\nElement not found." << endl;
    }
    else
    {
        loc = temp->next;
        temp->next = temp->next->next;
        cout << "\nDeleted element is : " << loc->data << endl;
        delete loc;
    }
}

// DELETION AT THE END.
void del_end()
{
    if (start == NULL)
    {
        cout << "\nThere is no node to delete." << endl;
        return;
    }
    if (start->next == NULL)
    {
        cout << "\ndeleted element is :" << start->data << endl;
        temp = start;
        start = NULL;
        delete temp;
        return;
    }
    temp = start;
    while (temp->next->next != NULL)
    {
        temp = temp->next;
    }
    loc = temp->next;
    temp->next = NULL;
    cout << "\nDeleted element is : " << loc->data << endl;
    delete loc;
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

// IN THE MAIN FUNCTION YOU CAN ABOVE FUNCTIONS AS MANY TIMES AS YOU WANT IN ANY ORDER AS REQUIRED.
// I CALLED SOME FUNCTIONS FOR TEST PURPOSE ONLY.IT IS NOT NECESSARY TO CALL ALL FUNCTIONS.
// ONLY WRITE THOSE FUNCTIONS WHICH ARE GIVEN IN QUESTION.
// CALL TRAVERSE FUNCTION AFTER EACH INSERTION OR DELETION CALL.
int main()
{
    insert_beg(6);
    insert_beg(4);
    insert_beg(3);
    insert_after_element(4, 5);
    insert_end(7);
    traverse();
    del_beg();
    traverse();
    del_end();
    traverse();
    del_after_element(5);
    traverse();
    return 0;
}