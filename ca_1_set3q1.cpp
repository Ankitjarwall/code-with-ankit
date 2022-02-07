#include <iostream>
using namespace std;
int main()
{
    int prime, k = 2, i = 2;
    cout << "Enter the number :";
    cin >> prime;

    for (i = 2; i < prime; i++)
    {
        for (k = 2; k < i; k++)
        {
            if (i % k == 0)
            {
                cout << "";
                break;
            }
        }
        if (k == i)
        {
            cout << k << " ";
        }
    }
    return 0;
}