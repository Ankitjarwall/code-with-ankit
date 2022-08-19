#include <iostream>
#include <vector>

using namespace std;

int main()
{
    vector<int> num;
    num.push_back(3);
    num.push_back(45);
    num.push_back(40);
    num.push_back(90);

    cout << "Capacity : " << num.capacity() << endl;
    cout << "Size : " << num.size() << endl;

    for (int i = 0; i < num.size(); i++)
    {
        cout << "Element " << i << " : " << num[i] << endl;
    }
    num.pop_back();
    num.pop_back();
    cout << "1st element : " << num.front() << endl;
    cout << "Last element : " << num.back() << endl;
    cout << "Element or not  : " << num.empty() << endl;
    cout << "Index Element : " << num.at(2) << endl;
    // num.begin();

    return 0;
}