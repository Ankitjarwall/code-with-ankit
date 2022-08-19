#include <iostream>
#include <array>

using namespace std;
int main()
{
    cout << "\nNormal array...\n"
         << endl;
    int array1[4] = {1, 2, 3, 4};
    for (int i = 0; i < 4; i++)
    {
        cout << "Array " << i << " : " << array1[i] << endl;
    }


    cout << "\n\nstl Array\n"
         << endl;


    array<int, 4> a = {10, 29, 90, 47};
    int count = a.size();
    for (int i = 0; i < count; i++)
    {
        cout << a[i] << endl;
    }

    cout << "2nd index  : " << a.at(2) << endl;
    cout << "1st element : " << a.front() << endl;
    cout << "last emlement : " << a.back() << endl;
    cout << "empty or not : " << a.empty() << endl;
    return 0;
}