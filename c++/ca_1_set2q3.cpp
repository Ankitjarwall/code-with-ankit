#include <iostream>
using namespace std;
int main()
{
    int even = 0, k;
    cout << "Enter the number : ";
    cin >> even;
    for (k = 0; k <= even; k++)
    {
        if (k % 2 != 0)
        {
            continue;
        }
        cout << k << " ";
    }

    return 0;
}