#include <iostream>
using namespace std;
int main()
{
    int odd = 0, sum = 0;
    cout << "Enter the number : ";
    cin >> odd;
    for (int i = 0; i <= odd; i++)
    {
        if (i%2==0)
        {
            continue;
        }
        else
        {
            sum = i + sum;
        }
    }

    cout << sum;
    return 0;
}