#include <iostream> //header file.
using namespace std;
int main() // main body.
{
    int math = 0, hindi = 0, english = 0; // varibale declaration.

    cout << "\n*  *  *  *  *  *  * *  *  *  *  *\n";
    cout << "\tMath : ";
    cin >> math; // user input I.e : 87
    cout << "*\t\t\t\t*\n";

    cout << "\tHindi : ";
    cin >> hindi; // user input I.e : 91
    cout << "*\t\t\t\t*\n";

    cout << "\tEnglish : ";
    cin >> english; // user input I.e : 88
    cout << "*\t\t\t\t*\n";

    cout << "\tTotal mark : " << math + hindi + english << endl; // output.
    cout << "*\tPercentage : " << (math + hindi + english) / 3.0 << "%"
         << " \t*" << endl; // ouput.
    cout << "\n*  *  *  *  *  *  * *  *  *  *  *\n"<<endl;
    return 0;
}