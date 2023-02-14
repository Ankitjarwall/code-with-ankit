#include <iostream>

using namespace std;

class queue
{
    int *array;
    int top, n;

public:
    queue()
    {
        n = 10;
        array[n];
        top = -1;
    }

    void push(int value)
    {
        if (top == n - 1)
        {
            cout << "Queue is full.";
            exit;
        }
        top++;
        array[top] = value;
    }

    void pop()
    {
        if (top == -1)
        {
            cout << "Queue is empty.";
            exit;
        }
        top--;
    }

    void display()
    {
        for (int i = top; i >= 0; i--)
        {
            cout << array[i] << endl;
        }
    }
};

int main()
{
    queue qu;

    qu.push(10);
    qu.push(20);
    qu.push(30);
    qu.push(40);

    qu.display();
    cout << "" << endl;

    qu.pop();
    qu.pop();

    qu.display();
    return 0;
}