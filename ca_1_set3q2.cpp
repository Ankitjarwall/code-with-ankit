#include <iostream>
using namespace std;

class telephone
{
private:
    int p_call, p_min;
    float p_total_call, p_total_min, p_bill;
    void display()
    {
        cout << "\nTotal Calls :" << p_call<<"\t";
        cout << "Total Minute :" << p_min << endl;
        cout << "Total Call Cost :" << p_total_call << "\t";
        cout << "Total Minute Cost :" << p_total_min << endl;
        cout << "\nAmount to be payed :" << p_bill << endl;
    }

public:
    void tele_cost(int f_call, int f_min)
    {
        p_call = f_call;
        p_min = f_min;
        p_total_call = f_call * 0.50;
        p_total_min = f_min * 0.20;
        p_bill = p_total_call + p_total_min;
        display();
    }
} bill;

int main()
{
    int call, min;

    cout << "1 call : 0.50 INR.\n 1 min  duration : 0.20 INR " << endl;

    cout << "Enter the call done : ";
    cin >> call;
    cout << "Enter the Minute talked on call : ";
    cin >> min;

    bill.tele_cost(call, min);

    return 0;
}