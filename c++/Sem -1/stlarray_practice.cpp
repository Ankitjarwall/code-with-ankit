#include <iostream>
#include <array>
using namespace std;
int main()
{
    array<int, 5> num = {1, 2, 3, 4, 5};
    for (int i = 0; i < num.size(); i++)
    {
        cout << "Element " << i << " : " << num.at(i) << endl;
    }

    return 0;
}