#include <iostream>
using namespace std;

int sum()
{
    cout << "Hello..";
    return 0;
}
int main()
{
    cout << "Address of sum : " << (unsigned *)&sum << endl;
    cout << "Size of sum : " << sizeof(sum());
    return 0;
}