#include <iostream> //header file.
using namespace std;
int main() // main body.
{
    int table, i = 1; // variable declaration.
    cout << "\nEnter the table number : ";
    cin >> table; // user input I.e : 6
    cout << "\n* * * * "
         << "TABLE OF " << table << "  * * *\n*\t\t\t*" << endl;
    do // do while loop.
    {
        cout << "*\t" << table << " x " << i << " = " << i * table << " \t*" << endl; // ouput.
        i++;
    } while (i <= 10);
    cout << "*\t\t\t*\n* * * * * * * * * * * * * \n"
         << endl;

    return 0;
}