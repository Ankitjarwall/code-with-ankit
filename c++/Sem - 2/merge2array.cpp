#include <iostream>
using namespace std;
int main()
{
    int array1[5] = {1, 2, 3, 4, 5}, array2[5] = {6, 7, 8, 9, 10}, n = 0, j = 0;

    n = sizeof(array1) / 4;
    int array3[n], size = (sizeof(array1) / 4) * 2;

    for (int i = 0; i < n; i++)
    {
        array3[i] = array1[i];
    }

    for (n; n < size; n++)
    {
        array3[n] = array2[j];
        j++;
    }

    cout << "Array : ";
    for (int i = 0; i < size; i++)
    {
        cout << array3[i] << " ";
    }

    return 0;
}