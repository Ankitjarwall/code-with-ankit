#include <iostream>
#include <deque>
using namespace std;
int main()
{
    deque<int> num;
    num.push_back(1);
    num.push_back(2);
    num.push_back(3);
    num.push_back(4);
    num.push_back(5);

    num.push_front(0);

    num.erase(num.begin(), num.begin() + 2);

    num.clear();

    for (int i = 0; i < num.size(); i++)
    {
        cout << "Element " << i << ": " << num.at(i) << endl;
    }
    return 0;
}