#include <iostream>
using namespace std;
int main()
{
    int value;
    cout << "Table : ";
    cin >> value;

    for (int i = 1; i < 11; i++)
    {
        cout << value << " X " << i << " = " << i * value << endl;
    }
    return 0;
}