#include <iostream>
using namespace std;

void tele_cost(int call, int min);

void tele_cost(int call, int min)
{
    float p_total_call, p_total_min, p_bill;

    p_total_call = call * 0.80;
    p_total_min = min * 0.10;
    p_bill = p_total_call + p_total_min;

    cout << "Total Call Cost :" << p_total_call << " INR" << endl;
    cout << "Total Minute Cost :" << p_total_min << " INR" << endl;
    cout << "Pay Amount :" << p_bill << " INR" << endl;
}

int main()
{
    int calling, minute;

    cout << "Per call cost = 0.80\n Per min cost = 0.10" << endl;

    cout << "Total call done : ";
    cin >> calling;
    cout << "Total Minute talked on call : ";
    cin >> minute;

    tele_cost(calling, minute);

    return 0;
}