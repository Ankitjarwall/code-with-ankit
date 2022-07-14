#include <iostream>
using namespace std;
int main()
{
    int num, originalNum, remainder, result = 0, a, b = 1;
    cout << "Enter a three-digit integer: ";
    cin >> num;

    originalNum = num;

    while (originalNum != 0)
    {
        remainder = originalNum % 10;
        a = num;
        while (a != 0)
        {
            b = remainder * b;

            result += b;
            a = a / 10;
        }

        originalNum /= 10;
    }
    if (result == num)
        cout << num << "\n is an Armstrong number." << endl;
    else
        cout << num << " is not an Armstrong number." << endl;
    return 0;
}