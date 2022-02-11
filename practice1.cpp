#include <iostream>
using namespace std;
int main()
{
    int total_mark[3], english[3], hindi[3], math[3], sst[3], evs[3];
    float percentage[3];
    for (int i = 0; i <= 2; i++)
    {
        cout << "Student " << i+1 << endl;
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
        
        total_mark[i] = (math[i]+hindi[i]+english[i]+sst[i]+evs[i]);
        percentage[i] = (float)total_mark[i] / 5.00;
    }
    for (int i = 0; i <= 2; i++)
    {
        cout << " Total Mark of student " << i << " : " << total_mark[i] << endl;
        cout << " Total percentage of student " << i << " : " << percentage[i] << endl;
    }
    return 0;
}