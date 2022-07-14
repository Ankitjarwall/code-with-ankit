#include <iostream>
#include <fstream>
using namespace std;
class num
{
public:
    void swap(int &x, int &y)
    {
        cout << "Before Swapping: A = " << x << ", B = " << y;
        x = x * y;
        y = x / y;
        x = x / y;
        cout << "\nAfter Swapping: A = " << x << ", B = " << y;
        write(x, y);
        cout << "\nContent in file :\n";
        read();
    }

    void write(int a, int b)
    {
        fstream file;
        file.open("swap file.txt", fstream::in);
        if (!file)
        {
            cout << "\nError Occurred!";
        }
        // Write in file.
        ofstream out("swap file.txt");
        out << "After Swapping : A = " << a << ", B = " << b << "\n";
        out.close();
        file.close();
        cout << endl;
    }

    void read()
    {
        fstream file;
        string ch;
        file.open("swap file.txt", fstream::in);
        if (!file)
        {
            cout << "\nError Occurred!";
        }
        // Read from file
        getline(file, ch);
        cout << ch;
        getline(file, ch);
        cout << "\n"
             << ch;
        file.close();
        cout << endl;
    }
};

int main()
{
    num obj;
    int first = 0, Second = 0;
    cout << "Value of A : ";
    cin >> first;
    cout << "Value of B : ";
    cin >> Second;
    obj.swap(first, Second);
    return 0;
}