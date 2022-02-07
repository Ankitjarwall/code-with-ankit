#include <iostream>
using namespace std;
int main()
{
    int unit;
    cout<<"\n1st 100 unit charge : 7.5 INR\nTill 200 unit charge : 8.5 INR\nmore then 200 : 9.5 INR\n"<<endl;
    cout << "Enter the Unit consumed : ";
    cin >> unit;

    if (unit <= 100)
    {
        cout << "Amout to be payed : " << unit * 7.5;
    }
    if (unit > 100 && unit <= 200)
    {
        cout << "Amout to be payed : " << unit * 8.5;
    }
    if (unit >= 200)
    {
        cout << "Amout to be payed : " << unit * 9.5;
    }
    return 0;
}