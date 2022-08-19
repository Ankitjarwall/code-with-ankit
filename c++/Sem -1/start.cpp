#include <iostream>
using namespace std;

//             c++
class lpu
{
    int z = 1, x = 2;

public:
    int sum(int m, int n)
    {
        int v;
        v = m + n;

        z=m;
        x=n;

        cout<<"z = "<<z<<endl;
        cout<<"x = "<<x<<endl;

        return v;
    }

} bca;

int main()
{
    int a, b, c;
    cout << "Enter your number : " << endl;
    cin >> a;
    cout << "Enter your 2nd number : ";
    cin >> b;
    cout << "SUM : " << bca.sum(a, b);
    
    return 0;
}