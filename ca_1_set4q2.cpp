#include <iostream> //header file.
using namespace std;
int main() // main body.
{
    int math = 0, hindi = 0, english = 0, science = 0, sst = 0; // varibale declaration.

    cout << "Math : ";
    cin >> math; // user input I.e : 87

    cout << "Hindi : ";
    cin >> hindi; // user input I.e : 91

    cout << "English : ";
    cin >> english; // user input I.e : 88

    cout << "Science : ";
    cin >> science; // user input I.e : 88

    cout << "Sst : ";
    cin >> sst; // user input I.e : 88

    cout << "\tTotal mark : " << math + hindi + english + science + sst << endl; // output.
    cout << "\tPercentage : " << (math + hindi + english + science + sst) / 5.0 << "%"
         << " \t" << endl; // ouput.
    
    return 0;
}