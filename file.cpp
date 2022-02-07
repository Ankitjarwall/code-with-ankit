#include <iostream>
#include <fstream>
#include <windows.h>
using namespace std;

int main()
{
    string str;
    int age, pin;
    fstream sahil;

    cout << "Name: ";
    cin >> str;
    cout << "Age: ";
    cin >> age;
    cout << "Pincode: ";
    cin >> pin;

    sahil.open("file.txt", ios::app);

    sahil << "Name: " << str << endl;
    sahil << "Age: " << age << endl;
    sahil << "Pincode: " << pin << endl;

    sahil.close();
    Beep(2000, 500);
    Sleep(150);
}