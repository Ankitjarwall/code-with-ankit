#include <iostream>
using namespace std;
int main()
{
    int user_input = 0, i = 1;
    cout << "Table number : ";
    cin >> user_input;
main:
    cout << user_input << "x" << i << "=" << user_input * i << endl;
    i++;
    if (i <= 10)
    {
        goto main;
    }

    return 0;
}