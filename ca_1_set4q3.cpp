#include <iostream> //header file.
using namespace std;
int main() // main body.
{
    int number = 0, a = 0, b = 0; // varibale declaration.
    cout << "\n+ - - - - - - - - - - - - - - - - - - - - - +" << endl;
    cout << "\tEnter the number : ";
    cin >> number;                   // user input I.e : 500
    for (int i = 1; i < number; i++) // for loop.
    {
        int reminder = 0, result = 0; // for loop variables declaration.
        a = b = i;

        for (; b > 0;) // nested for loop.
        {
            reminder = b % 10;
            result += reminder * reminder * reminder;
            b = b / 10;
        }
        if (result == a) // if condition.
        {
            if (result == 1) // nested if condition.
            {
                cout << "|\t\t\t\t\t    |\n\tArmstrong Numbers From 1 to " << number << ".\n|\t"; // ouput.
            }

            cout << a << ","; // output.
        }
    }
    cout << "\b \t\t    |\n\n+ - - - - - - - - - - - - - - - - - - - - - +\n"
         << endl;

    return 0;
}