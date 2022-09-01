#include <iostream>
using namespace std;

int main()
{
    int a[5] = {1, 2, 3, 4, 5}, temp = 0, j = 0;

    for (int i = 4; i > 5/2; i--)
    {
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        j++;
    }
    for (int i = 0; i < 5; i++)
    {
        cout << a[i] << " ";
    }
}