#include <iostream>
using namespace std;
int main()
{
    int user;
    cout << "design : ";
    cin >> user;
    for (int i = 0; i < user; i++)
    {
        for (int j = 0; j < i; j++)
        {
            cout << "*";
        }
        cout << "\n";
    }
    
    return 0;
}