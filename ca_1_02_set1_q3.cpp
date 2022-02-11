#include <iostream>
using namespace std;
int main()
{
    int array[10], user_input = 0, i = 0,k=0;
    for (int i = 0; i < 10; i++)
    {
        cout << "Enter the element " << i + 1 << " : ";
        cin >> array[i];
    }
top:
    cout << "Elements in the array are : ";
    while (i < 10)
    {
        cout << array[i] << " ";
        i++;
    }

    cout << "\nEnter the element from array : ";
    cin >> user_input;

    while (k < 10)
    {
        if (user_input == array[k])
        {
            cout << "The element is : " << user_input << endl;
            return 0;
        }
        k++;
    }

    cout << "Enter the valid number from array.\n"
         << endl;
    goto top;

    return 0;
}