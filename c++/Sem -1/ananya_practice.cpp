#include <iostream>
using namespace std;
class reverse
{
private:
    int n, i;

public:
    reverse()
    {
        cout << "Enter Number to reverse: ";
        cin >> n;
    }
    reverse(const reverse &obj1)
    {

    }
    friend void show(reverse);
};
void show(reverse r)
{
    cout << "The reverse the Entered number: ";
    for (r.i = r.n; r.i > 0; r.i = r.i / 10)
    {
        cout << r.i % 10;
    }
}
int main()
{
    reverse r;
    show(r);
}