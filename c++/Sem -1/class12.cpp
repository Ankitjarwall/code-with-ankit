#include <iostream>
using namespace std;

class lpu
{
private:
    int p_roll = 0, p_mark = 0;

    void display()
    {
        cout << "Mark : " << p_mark<<endl;
        cout << "Roll no. : " << p_roll<<endl;
    }

public:
    int pub_rolll = 0, pub_mark = 0;
    void input(int fmark, int froll)
    {
        p_mark = fmark;
        p_roll = froll;
        display();
    }
};
int main()
{
    lpu bca;
    int roll = 0, mark = 0;
    cout << "Enter the mark : ";
    cin >> mark;
    cout << "Enter the Roll : ";
    cin >> roll;
    bca.input(mark, roll);
    return 0;
}