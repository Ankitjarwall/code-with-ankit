#include <iostream>
#include <vector>

using namespace std;

// int main()
// {
//     // copying vector elements to another...
//     vector<int> num;
//     num.push_back(10);
//     num.push_back(11);
//     num.push_back(12);
//     num.push_back(13);
//     for (int i = 0; i < num.size(); i++)
//     {
//         cout << "Elemetns : " << num.at(i) << endl;
//     }

//     vector<int> solvez(num);
//     cout << "Solvez : " << endl;
//     for (int i = 0; i < solvez.size(); i++)
//     {
//         cout << "Elemetns : " << solvez.at(i) << endl;
//     }
//     return 0;
// }

int main()
{
    vector<int> num;
    num.push_back(10);
    num.push_back(11);
    num.push_back(12);
    num.push_back(13);
    num.push_back(14);

    cout << "Print without copy : " << endl;
    for (int i = 0; i < num.size(); i++)
    {
        cout << "Element : " << num.at(i) << endl;
    }

    cout << "Print with copy : " << endl;

    vector<int> copy_num(num);

    for (int i = 0; i < copy_num.size(); i++)
    {
        cout << "Elements : " << copy_num.at(i) << endl;
    }

    return 0;
}