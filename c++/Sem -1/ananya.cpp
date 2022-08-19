#include <iostream>
using namespace std;
class reverse
{
private:
    int n;

public:
    reverse()
    {
        n = 0;
    }
    reverse(int x)
    {
        n = x;
    }
    reverse(const reverse &obj1)
    {
        n = obj1.n;
    }

    friend void show(reverse);
};
void show(reverse r)
{
    int rev = 0;
    while (r.n > 0)
    {
        int rem = r.n % 10;
        rev = rev * 10 + rem;
        r.n = r.n / 10;
    }
    cout << "Reversed number is : " << rev << endl;
}
int main()
{
    int a;
    cout << "Enter Number to reverse: ";
    cin >> a;
    reverse obj(a);
    reverse ob = obj;

    show(ob);
}
