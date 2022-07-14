#include <iostream>
using namespace std;

class company
{
private:
    string name, dep;
    int id;

public:
    void employee_info()
    {
        cout << "Enter the Name : ";
        cin >> name;
        cout << "Enter the Department : ";
        cin >> dep;
        cout << "Enter the Employee ID : ";
        cin >> id;
    }
    void employee_disp()
    {
        cout << "\n---Employee Information---\nName : " << name << endl;
        cout << "Department : " << dep << endl;
        cout << "ID : " << id << endl;
    }
};

class salary_details : public company
{
private:
    int ta = 500, da = 400, hr = 200, basic_salary, total_salary;

public:
    void salary()
    {
        employee_info();
        cout << "Enter Basice Salary : ";
        cin >> basic_salary;
        total_salary = basic_salary + ta + da + hr;
        employee_disp();
        display_salary();
    }
    void display_salary()
    {
        cout << "TA : " << ta << endl;
        cout << "DA : " << da << endl;
        cout << "HR : " << hr << endl;
        cout << "Basic Salary : " << basic_salary << endl;
        cout << "The total salary : " << total_salary << endl;
    }
};
int main()
{
    salary_details office;
    office.salary();
    return 0;
}