#include <iostream>
using namespace std;
int main()
{
    int a = 9;
    for (int i = 2; i < a; i++)
    {
        if (i % a == 0)
        {
            cout << "The number is not prime number."<<endl;
        }
        else
        {
            cout << "the number is prime number."<<endl;
        }
    }
    return 0;
}