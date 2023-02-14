#include <iostream>

using namespace std;

void display(int size, int arr[])
{
    cout << "\n\nArray : ";
    for (int i = 0; i < size; i++)
    {
        cout << arr[i] << " ";
    }
    cout << "\n\n";
}

void insert_start(int value, int size, int array[])
{
    for (int i = size - 1; i >= 0; i--)
    {
        array[i + 1] = array[i];
    }
    array[0] = value;
}

void insert_mid(int value, int position, int size, int array[])
{
    for (int i = size; i > 0; i--)
    {
        if (position <= i)
        {
            array[i + 1] = array[i];
            if (i == position)
            {
                array[i] = value;
            }
        }
    }
}

void insert_end(int value, int size, int array[])
{
    array[size - 1] = value;
}

void deletetionbyindex(int index, int size, int array[])
{
    for (int i = 0; i < size; i++)
    {
        if (array[i]>=index)
        {
            array[i-1]=array[i];
        }
        
    }
    
}

void search(int value, int size, int array[])
{
    for (int i = 0; i < size; i++)
    {
        if (value == array[i])
        {
            cout << "Element found at array[" << i << "]";
        }
    }
}

int main()
{
    int array[10] = {12, 13, 14, 15, 16};
    int size = sizeof(array) / 4, element = 999, position = 5,index=8;

    insert_start(element, size, array);
    insert_mid(element, position, size, array);
    insert_end(element, size, array);

    search(element,size,array);

    deletetionbyindex(index,size,array);
    display(size, array);
    return 0;
}