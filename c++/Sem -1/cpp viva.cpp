#include <iostream>
using namespace std;
class abc
{
private:
    int a;
    float b;
public:
    friend void  get();
};

void get()
{
        cout << "Enter the value ";
        cin>>a,b;

}

int main()
{
    get();

    return 0;
}