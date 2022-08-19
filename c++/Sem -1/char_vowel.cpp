#include <iostream>
using namespace std;
int main()
{
    char a;
    cout << "Enter the char : ";
    cin >> a;
    if (a == 'a' ||a == 'e' ||a == 'i' ||a == 'o' ||a == 'u' ||a == 'A'||a == 'E' ||a == 'I' ||a == 'O' ||a == 'U')
    {
        cout << "The char is vowel.";
    }
    else
    {
        cout << "The char is consonant.";
    }
    return 0;
}