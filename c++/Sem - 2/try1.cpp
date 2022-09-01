#include <iostream>
using namespace std;
int main()
{

    int array[6] = {1, 2, 4, 5, 6};
    int index = 2, z = 5, k = 3;

    for (int i = 6; i > 0; i--)
    {
        if (i >= index)
        {
            array[i] = array[z];
            z--;
        }

        if (index == i)
        {
            array[i] = k;
        }
    }

    for (int i = 0; i < 6; i++)
    {
        cout << " " << array[i];
    }

    return 0;
}
