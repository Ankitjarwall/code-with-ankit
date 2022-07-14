#include<iostream.h>
#include<String.h>
using namespace std;

class student
{
private:
    string name;
    int roll;
    float avg;
    long int contact;

public:

    void input()
    {
        cout << "Enter the name : ";
        cin >> name;
        cout << "Enter the roll no. : ";
        fflush(stdin);
        cin >> roll;
        cout << "Enter the Avg : ";
        cin >> avg;
        cout << "Enter the contact : ";
        cin >> contact;
    }
    void disp()
    {
        cout << "Name : " << name;
        cout << "Roll no : " << roll;
        cout << "Contact : " << contact;
    }
};

int main()
{
    int digit, i;
    cout << "Enter the number student record : ";
    cin >> digit;
    for (i = 0; i < digit; i++)
    {
        student bj[i];
        for (i = 0; i < digit; i++)
        {
            bj[i].input();
        }
        for (i = 0; i < digit; i++)
        {
            bj[i].disp();
        }
    }
    return 0;
}
