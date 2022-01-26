#include <iostream>
using namespace std;

class triangle
{

public:
    int a, b, c;
    triangle()
    {
        a = 4;
        b = 5;
        c = 5;
    }
};

int main()
{
    triangle area;
    cout << "a = " << area.a << endl
         << "b = " << area.b << endl
         << "c = " << area.c;
    return 0;
}