#include <iostream>
using namespace std;
int z=0,x=0;
class lpu
{
private:
    int mark = 0, roll = 0;

public:
    void total(int mark1, int roll1)
    {
        mark = mark1;
        roll = roll1;

        cout << "Mark = " << mark << endl;
        cout << "Roll = " << roll << endl;
        
    }
    
    void sum(int a, int b);

} bca;

void lpu::sum(int a, int b)
{
    int z = mark, x = roll;
    cout << "***A = " << z << endl;
    cout << "***B = " << x << endl;
}

int main()
{
    int roll = 1, mark = 89;
    bca.total(mark, roll);
    bca.sum(mark, roll);
    return 0;
}