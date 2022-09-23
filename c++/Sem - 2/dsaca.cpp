#include <iostream>
using namespace std;
int main()
{
    int element = 0, z = 0, j = 0, size = 0;

    cout << "Enter the size of the Single Array : ";
    cin >> size;

    int first_array[size], second_array[size], final_array[size * 2];

    cout << "\n\n\tFirst array" << endl;

    for (int i = 0; i < size; i++)
    {
        cout << "Element : " << i << " : ";
        cin >> first_array[i];
    }

    cout << "\n\n\tSecond array" << endl;

    for (int i = 0; i < size; i++)
    {
        cout << "Element : " << i << " : ";
        cin >> second_array[i];
    }
    cout << "Enter the element to be search : ";
    cin >> element;

    for (int i = 0; i < size; i++)
    {
        final_array[i] = first_array[i];
    }

    for (int f = 5; f < size * 2; f++)
    {
        final_array[f] = second_array[z];
        z++;
    }

    cout << "\nARRAY : ";
    for (int i = 0; i < size * 2; i++)
    {
        cout << final_array[i] << " ";
    }

    for (int i = 0; i < size * 2; i++)
    {
        if (final_array[i] == element)
        {
            cout << "\nElement found : " << element << endl;
            cout << "Index : " << i;
            j++;
            break;
        }
    }
    if (j == 0)
    {
        cout << "Element not found.";
    }

    return 0;
}