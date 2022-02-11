#include <iostream>
using namespace std;
int main()
{
    int num = 0;
    cout << "Enter the number : ";
    cin >> num;
    for (int i = 0; i <= num; i++)
    {
        if (i % 2 != 0)
        {
            continue;
        }
        cout << i << " ";
    }

    return 0;
}