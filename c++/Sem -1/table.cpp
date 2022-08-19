#include<iostream>
using namespace std;
int main()
{
    int user =0 ;
    cout << "Konsi table : ";
    cin >> user;

    for (int i = 1; i <= 10;i++)
    {
        cout << user << "X" << i << "= " << user * i<<endl;
    }

        return 0;
}