#include <iostream>
using namespace std;
int main()
{
    int a, k, i;
    cout << "Enter the number :";
    cin >> a;
    for (i = 2; i < a; i++)
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