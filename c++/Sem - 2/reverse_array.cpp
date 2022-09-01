#include <iostream>
using namespace std;
int main()
{
    int array[5] = {10, 20, 30, 40, 50}, array1[5],a;

    for (int i = 0; i < 5; i++)
    {
        a = 4;
        array1[i] = array[4];
        a--;
    }
    
    cout << "Array : ";
    
    for (int i = 0; i < 5; i++)
    {
        cout << array1[i]<<" ";
    }

    return 0;
}