#include <iostream>
using namespace std;
int main()
{
    int input;
    cout << "Enter the digit : ";
    cin >> input;

    for (int i = 0; i < input; i++)
    {
        if (input % i == 0)
        {
            cout << "the number is not prime.";
            break;
        }
        else
        {
            cout << "The number is prime " << input;
        }
        cout << "+";
    }
    cout << "AA";

    return 0;
}
