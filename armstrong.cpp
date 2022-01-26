#include <iostream>
using namespace std;

int main() {
    int num, originalNum, remainder, result = 0;
    cout << "Enter a three-digit integer: ";
    cin >> num;
    originalNum = num;

    while (originalNum != 0) {
        // remainder contains the last digit
        remainder = originalNum % 10;
        
        cout<<"\n*rem**"<<remainder;
        result += remainder * remainder * remainder;
        
        // removing last digit from the orignal number
        originalNum /= 10;
        cout<<"\n**org**"<<originalNum;
        cout<<"\n**res**"<<result;
    }

    if (result == num)
        cout << num << "\n is an Armstrong number.";
    else
        cout << num << " is not an Armstrong number.";

    return 0;
}