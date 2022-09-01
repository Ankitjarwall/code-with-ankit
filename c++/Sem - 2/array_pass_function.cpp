#include <iostream>
using namespace std;

int pass_array(int array[])
{
    for (int i = 0; i < 10; i++)
    {
        cout << array[i] << " ";
    }

    return 0;
}

int main()
{
    int array[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    pass_array(array);
    return 0;
}