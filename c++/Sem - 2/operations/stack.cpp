#include <iostream>

using namespace std;

class stack
{
    int n;
    int *array;
    int top;

public:
    stack()
    {
        cout << "Hello";
        n = 10;
        array[n];
        top = -1;
    }
    
    void push(int value)
    {
        if (top == (n - 1))
        {
            cout << "Stck is Overflow.";
            return;
        }
        top++;
        array[top] = value;
        cout << "hello2";
    }

    void pop()
    {
        if (top == -1)
        {
            cout << "Stack underFlow.";
            return;
        }
        top--;
    }

    int topElement()
    {
        if (top == -1)
        {
            cout << "Stack underFlow, No element.";
        }
        else
        {
            return (array[top]);
        }
    }

    void display()
    {
        if (top == -1)
        {
            cout << "No element to display.";
            return;
        }
        for (int i = top; i >= 0; i--)
        {
            cout << array[top] << endl;
        }
    }
};

int main()
{
    stack st;
    st.push(10);
    st.push(20);
    st.push(30);

    st.display();
    return 0;
}