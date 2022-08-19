#include <iostream>
using namespace std;
int main()
{
    int input = 0, input_2 = 0, i = 0, j = 2, update = 0;
    cout << "Initial Number:";
    cin >> input;
    cout << "Final Number:";
    cin >> input_2;

    if (input > input_2)
    {
        input_2 = (input * input_2) / (input = input_2);
    }

    for (i = input; i <= input_2; i++)
    {
        update = 1;
        for (j = 2; j <= i / 2; j++)
        {
            if (i % j == 0)
            {
                update = 0;
                break;
            }
        }
        if (update == 1 && i >= 2)
        {
            cout << i << " ";
        }
    }
    return 0;
}