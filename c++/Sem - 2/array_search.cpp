#include <iostream>
using namespace std;

int main()
{
    int array[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, even[5] = {0}, odd[5] = {0}, temp = 0, j = 0, k = 0;

    cout << "Array before : ";
    for (int i = 0; i < 10; i++)
    {
        cout << array[i] << " ";
    }

    for (int i = 0; i < 10; i++)
    {

        if (array[i] % 2 == 0)
        {
            even[j] = array[i];
            j++;
        }
        if (array[i] % 2 != 0)
        {
            odd[k] = array[i];
            k++;
        }
    }

    cout << "\nEven Array after : ";

    for (int i = 0; i < 5; i++)
    {
        cout << even[i] << " ";
    }

    cout << "\nOdd Array after : ";
    for (int i = 0; i < 5; i++)
    {
        cout << odd[i] << " ";
    }

    return 0;
}