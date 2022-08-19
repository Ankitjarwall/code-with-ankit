#include <iostream>
using namespace std;

//*************baisc calculator*************
int sum(int a, int b);
int mul(int a, int b);
int divi(int a, int b);
int sub(int a, int b);
int avg(int a, int b);

int sum(int a, int b)
{
    int c;
    c = a + b;
    cout << "The sum of " << a << " + " << b << " = " << c << "\n"
         << endl;
    return 0;
}
int mul(int a, int b)
{
    int c;
    c = a * b;
    cout << "The multiply of " << a << " * " << b << " = " << c << "\n"
         << endl;
    return 0;
}
int divi(int a, int b)
{
    int c;
    c = a / b;
    cout << "The divide of " << a << " / " << b << " = " << c << "\n"
         << endl;
    return c;
}
int sub(int a, int b)
{
    int c;
    c = a - b;
    cout << "The sub of " << a << " - " << b << " = " << c << "\n"
         << endl;
    return c;
}
int avg(int a, int b, int d)
{
    int c;
    c = (a + b + d) / 3;
    cout << "The Average of " << c << "\n"
         << endl;
    return c;
}

int main()
{
    int a, b, c, input, menu, try1, i = 3;
home:
    cout << "\t\tPRESS :\n\tAdd : 1\t\t\tMultiply : 2\tDivide : 3\n\tSubstraction : 4\tAverage : 5\tExit : 0\tMenu : 9" << endl;
    fflush(stdin);
    cout << "Enter the input : ";
    cin >> input;
    cout<<"\n\n"<<endl;
    switch (input)
    {
    case 1:
        cout << "****ADD****\nEnter the 1st number : ";
        cin >> a;
        cout << "Enter the 2nd number : ";
        cin >> b;
        cout << sum(a, b);
        break;
    case 2:
        cout << "****MULTIPLY****\nEnter the 1st number : ";
        cin >> a;
        cout << "Enter the 2nd number : ";
        cin >> b;
        cout << mul(a, b);
        break;
    case 3:
        cout << "***DIVIDE****\nEnter the 1st number : ";
        cin >> a;
        cout << "Enter the 2nd number : ";
        cin >> b;
        cout << divi(a, b);
        break;
    case 4:
        cout << "****SUBTRACTION****\nEnter the 1st number : ";
        cin >> a;
        cout << "Enter the 2nd number : ";
        cin >> b;
        cout << sub(a, b);
        break;
    case 5:
        cout << "****AVERAGE****\nEnter the 1st number : ";
        cin >> a;
        cout << "Enter the 2nd number : ";
        cin >> b;
        cout << "Enter the 3nd number : ";
        cin >> c;
        cout << avg(a, b, c);
        break;
    case 0:
        exit (1);
    case 9:
    cout<<"\n\n"<<endl;
        goto home;

    default:

    exit:
        cout << "INVALID INPUT\nPRESS - Exit : 0\tMenu : 9" << endl;
        cout << "Enter Input : ";
        cin >> menu;
        if (menu == 9)
        {
            cout<<"\n\n"<<endl;
            goto home;
        }
        else if (menu == 0)
        {
            exit(1);
        }
        else
        {
            i--;
            if (i == 1)
            {
                cout << "\nYou have exceeded the maximum number of wrong attempts \nThank you." << endl;
                exit(1);
            }
            goto exit;
        }
    }
    goto home;
    return 0;
}
