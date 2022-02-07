#include <iostream>
using namespace std;
int z = 0, x = 0;
class lpu
{
private:
    int p_mark = 0, p_roll = 0;
    void cal()
    {
        cout << "Private Mark = " << p_mark << endl;
        cout << "Private Roll = " << p_roll << endl;
    }

public:
    void total(int mark1, int roll1)
    {
        p_mark = mark1;
        p_roll = roll1;
        cout << "Public Mark = " << p_mark << endl;
        cout << "Public Roll = " << p_roll << endl;
        cal();
    }

    void sum(int a, int b);

} bca;

void lpu::sum(int a, int b)
{
    int z = p_mark, x = p_roll;
    cout << "Out public A = " << z << endl;
    cout << "Out public B = " << x << endl;
}

int main()
{
    int roll = 0, mark = 0;
    cout << "Enter the Roll : ";
    cin >> roll;
    cout << "Enter the mark : ";
    cin >> mark;
    bca.total(mark, roll);
    bca.sum(mark, roll);
    return 0;
}