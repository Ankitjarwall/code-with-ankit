#include <iostream>
using namespace std;

class bca
{
private:
    int a = 0, b = 0;

public:
    int add(int z, int x)
    {
        int c;
        a = z;
        b = x;
        c = a + b;

        return 0;
    }
    void display()
    {
        cout << "\nThis is the output.";
    }
} lpubca;

int main()
{
    int a, b;
    cout << "Enter input 1 :";
    cin >> a;
    cout << "Enter input 2 :";
    cin >> b;
    lpubca.add(a, b);
    lpubca.display();
    return 0;
}