#include <iostream>
using namespace std;
int main()
{
    int prime, k = 2, i = 2;
    cout << "Enter the first number :";
    cin >> i;
    cout << "Enter the last number :";
    cin >> prime;
    if (i > prime)
    {
        prime = (i + prime) - (i = prime);
    }
    while (i < prime)
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
        i++;
    }
   
    return 0;
}