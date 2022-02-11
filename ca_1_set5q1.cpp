#include <iostream>
using namespace std;
int main()
{
    for (int i = 0; i < 3; i++)
    {
        int array[i];
        string Name[i];
        float total[i];
        int clas[i], roll[i], english, hindi, math, evs, sst;
        cout << "STUDENT " << i + 1 << endl;
        cout << "NAME: ";
        getline(cin, Name[i]);
        cout << "CLASS: ";
        cin >> clas[i];
        cout << "ROLL_NO: ";
        cin >> roll[i];

        cout << "English : ";
        cin >> english;
        cout << "Hindi : ";
        cin >> hindi;
        cout << "Maths : ";
        cin >> math;
        cout << "EVS : ";
        cin >> evs;
        cout << "SST : ";
        cin >> sst;
        array[i] = english + hindi + math + evs, sst;
        total[i] = array[i] / 5.0;

        if (i == 3)
        {
            for (int k = 0; k <= 0; k++)
            {
                cout << "\n**RESULT " << k + 1 << "**";
                cout << "\nName: " << Name[k] << endl;
                cout << "Class: " << clas[k] << endl;
                cout << "Roll_No: " << roll[k] << endl;
                cout << "Total_No: " << array[k] << "\t";
                cout << "Percentage: " << total[k] << endl;
            }
        }
    }
}