#include <iostream>
using namespace std;
int main()
{
    int a;
    cout << "Enter the number : ";
    cin >> a;
    for (int i = 2; i < a; i++)
    {
        if (a % i==0)
        {
            cout << "The number is not prime number.";
            break;
        }
        else
        {
            
            cout << "The number is prime number.";
        break;
        }
    }
    return 0;
}