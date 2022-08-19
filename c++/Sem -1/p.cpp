#include <iostream>
using namespace std;

class lpu
{
    int reg;
    string name;
    float tmark;

public:
    void details()
    {
        cout << "Enter the name : ";
        cin >> name;
        cout << "Enter the Registration : ";
        cin >> reg;
    }
};

class bca : lpu
{
    int mark1, mark2;

public:
    void student()
    {
        cout << "Enter the mark : ";
        cin >> mark1;
        cout << "Enter the mark : ";
        cin >> mark2;
        tmark = mark1 + mark2;
    }
    void disp()
    {
        cout << "Name : " << name;
        cout << "Registration : " << reg;
        cout << "Total mark : " << tmark;
    }
};

int main()
{

    return 0;
}