#include <iostream> //header file.
using namespace std;
int main() // main body.
{
    int table, i = 1; // variable declaration.
    cout << "\nEnter the table number : ";
    cin >> table; // user input I.e : 6
    cout
        << "TABLE OF " << table << endl;
    do // do while loop.
    {
        cout << table << " x " << i << " = " << i * table << endl; // ouput.
        i++;
    } while (i <= 10);

    return 0;
}