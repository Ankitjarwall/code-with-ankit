#include <iostream>
using namespace std;

int main()
{
    int array[10] = {9,6,5,8,7,3,4,1,2,10}, temp = 0;

    cout << "before array : ";
    for (int i = 0; i < 10; i++)
    {
        cout << array[i] << " ";
    }

    for (int j = 0; j < 10; j++)
    {
        for (int i = 0; i < 10; i++)
        {
            if (array[i] > array[i + 1])
            {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
            }
        }
    }

    cout << "\nafter array : ";
    for (int i = 0; i < 10; i++)
    {
        cout << array[i] << " ";
    }

    return 0;
}