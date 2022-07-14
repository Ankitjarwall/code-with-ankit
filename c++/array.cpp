#include <iostream>
using namespace std;
int main()
{
    int roll = 0, total = 0;
    cout << "Enter the array number : ";
    cin >> total;
    int array[total];

    for (int i = 1; i <= total; i++)
    {
        cout << "Enter the no. " << i << " : ";
        cin >> array[i];
    }
    cout << "Your input array is : " << endl;
    for (int i = 0; i < total; i++)
    {
        cout << array[i] << ", ";
    }
    cout << endl
         << "Sorted array : " << endl;
    for (int i = 0; i < total; i++)
    {
        for (int k = 1; k <= i; k++)
        {

            if (array[k] < array[i])
            {
                int temp = array[k];
                array[k] = array[i];
                array[i] = temp;
            }
        }
        for (int i = 0; i < total; i++)
        {

            cout << i << " ";
        }
        cout << endl;
    }
    return 0;
}