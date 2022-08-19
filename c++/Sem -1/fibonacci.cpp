#include <iostream>
using namespace std;
int main()
{
    int a = 0, d = 0, b = 1, c = 0;
    cout << "Enter the value : ";
    cin >> a;
    cout << "Series :\n";
    for (int i = 1; i <= a; ++i)
    {
        if (i == 1)
        {
            cout << d << ",";
            continue;
        }
        if (i == 2)
        {
            cout << b << ",";
            continue;
        }

        c = d + b;
        d = b;
        b = c;
        cout << c << ", ";
    }
    return 0;
}