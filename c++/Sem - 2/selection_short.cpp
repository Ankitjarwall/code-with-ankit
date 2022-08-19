#include <iostream>
using namespace std;
int main()
{
    int temp = 0, array[10] = {5, 9, 8, 3, 7, 4, 2, 6, 1}, total = 9, min_index = 0;

    for (int i = 0; i < total; i++)
    {
        min_index = i;
        for (int j = i + 1; j < total; j++)
        {
            if (array[j] < array[min_index])
            {
                min_index = j;
            }
        }
        temp = array[min_index];
        array[min_index] = array[i];
        array[i] = temp;
    }

    cout << "Array : ";
    for (int i = 0; i < total; i++)
    {
        cout << array[i] << " ";
    }

    return 0;
}