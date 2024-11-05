#include <iostream>
using namespace std;

class Student
{
private:
    int rNo;
    float perc;
    // private member functions
    void inputOn(void)
    {
        cout << "Input start..." << endl;
    }
    void inputOff(void)
    {
        cout << "Input end..." << endl;
    }

public:
    // public member functions
    void read(void)
    {
        // calling first member function
        inputOn();
        // read rNo and perc
        cout << "Enter roll number: ";
        cin >> rNo;
        cout << "Enter percentage: ";
        cin >> perc;
        // calling second member function
        inputOff();
    }
    void print(void)
    {
        cout << endl;
        cout << "Roll Number: " << rNo << endl;
        cout << "Percentage: " << perc << "%" << endl;
    }
};

// Main code
int main()
{
    // declaring object of class student
    Student std;

    // reading and printing details of a student
    std.read();
    std.print();

    return 0;
}