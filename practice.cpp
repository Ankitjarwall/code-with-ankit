#include <iostream>
using namespace std;
int main()
{
    int a, b = 0;
    cout << "Enter a number: ";
    cin >> a;
    while (1)
    {
        b++;
        if (b == 2 * a)
        {
            return 0;
        }
        if (b % 2 == 0)
        {
            continue;
        }
        cout << b << endl;
    }
    return 0;
}