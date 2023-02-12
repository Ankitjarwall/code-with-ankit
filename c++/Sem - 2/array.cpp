#include <iostream>
using namespace std;

void insert(int array[], int element, int position, int array_size)
{
    for (int i = array_size - 1; i >= 0; i--)
    {
        if (i == position)
        {
            array[i] = element;
        }
        else
        {
            array[i + 1] = array[i];
        }
    }

    for (int i = 0; i < 6; i++)
    {
        cout << array[i] << endl;
    }
}

int main()
{
    int array[6] = {1, 2, 3, 4, 5};
    int element = 99, position = 3, array_size = 6;

    insert(array, element, position, array_size);
    printf("stop");
    return 0;
}
