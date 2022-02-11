#include <iostream>
using namespace std;
int main()
{
    int total_mark[3], english[3], hindi[3], math[3], sst[3], evs[3], i = 0;
    float percentage[3];
    for (i = 1; i <= 3; i++)
    {
        cout << "Student " << i << endl;
        cout << "Math : ";
        cin >> math[i];
        cout << "english : ";
        cin >> english[i];
        cout << "hindi : ";
        cin >> hindi[i];
        cout << "evs : ";
        cin >> evs[i];
        cout << "Sst : ";
        cin >> sst[i];
        total_mark[i] = math[i] + hindi[i] + english[i] + evs[i] + sst[i];
        percentage[i] = total_mark[i] / 5.00;
        
    }
    for (i = 1; i <= 3; i++)
    {
        cout << " Total Mark of student " << i << " : " << total_mark[i] << endl;
        cout << " Total percentage of student " << i << " : " << percentage[i] << "\n"
             << endl;
    }
    return 0;
}