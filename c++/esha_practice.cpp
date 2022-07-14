#include <iostream>
using namespace std;
int c;
class base
{
public:
    int a, b;
    void input()
    {
        cout << "Enter the value of A : ";
        cin >> a;
        cout << "Enter the value of B : ";
        cin >> b;
        cout << "1 : + \n2 : - \n";
        cin >> c;
        if (c == 1)
        {
            c = 1;
        }
        if (c == 2)
        {
            c = 2;
        }
    }
    void disp()
    {
        cout << "A : " << a << endl;
        cout << "b : " << b << endl;
        cout << "c : " << c << endl;
    }
};
class sum : public base
{
public:
    void condition()
    {
        cout << "Condition sum..." << endl;
        cout << "A : " << a << endl;
        cout << "b : " << b << endl;
        cout << "c : " << c << endl;
    }
};
class sub : public base
{
public:
    void condition1()
    {
        cout << "Condition sub..." << endl;
        cout << "A : " << a << endl;
        cout << "b : " << b << endl;
        cout << "c : " << c << endl;
    }
};
int main()
{
    base bca;
    sub m;
    sum s;
    bca.input();
    bca.disp();
    m.condition1();
    s.condition();

    // if (c == 1)
    // {
    //     sum add;
    //     cout << "Main c Add : " << c;
    //     add.condition();
    // }
    // if (c == 2)
    // {
    //     sub minus;
    //     cout << "Main c minus : " << c;
    //     minus.condition1();
    // }
    return 0;
}