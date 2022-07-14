#include <iostream>
using namespace std;

class account
{
    string c_name, account;
    int account_no = 0, account_status = 0, type_account = 0, balance = 10000;
    int inputoption = 0, withdrawal = 0;
    float compound_interest = 1.75;

public:
    void input()
    {
    top:
        cout << "Saving : 1\tCurrent : 2\nInput : ";
        cin >> type_account;
        switch (type_account)
        {
        case 1:
            account_status = 1;
            account = {"Saving account"};
            cout << "The Type of account : " << account << endl;
            cout << "Enter the name of customer : ";
            cin >> c_name;
            cout << "Enter the Account number : ";
            cin >> account_no;
            success();
            break;

        case 2:
            account_status = 2;
            account = {"Current account"};
            cout << "The Type of account : " << account << endl;
            cout << "Enter the name of customer : ";
            cin >> c_name;
            cout << "Enter the Account number : ";
            cin >> account_no;
            success();
            break;
        default:
            cout << "INVALID INPUT!!" << endl;
            goto top;
        }
    }
    void success()
    {
        int transaction = 0;
        cout << "Account created! Successfully..." << endl;
    top1:
        cout << "Do Transaction now!!!\nContinue : 1\tLater : 2\nInput : ";
        cin >> transaction;
        switch (transaction)
        {
        case 1:
            cout << "Please wait..." << endl;
            switch (account_status)
            {
            case 1:
                saving_account();
                break;
            case 2:
                current_account();
            default:
                cout << "Cann't cannot to server!!\nPlease try again after sometime..." << endl;
                break;
            }

        case 2:
            cout << "Thank you. For using." << endl;
            break;
        default:
            cout << "Invalid input." << endl;
            goto top1;
            break;
        }
    }

    void saving_account()
    {
        cout << "Welcome " << c_name << " in Saving account." << endl;
        options();
        cout << "Enter the option : " << endl;
        cin >> inputoption;
        optionswitch(inputoption);
    }

    void current_account()
    {
        cout << "Welcome " << c_name << " in Currrent account." << endl;
        options();
        cout << "Enter the option : " << endl;
        cin >> inputoption;
        optionswitch(inputoption);
    }

    void options()
    {
        cout << "Select Option..." << endl;
        cout << "Balance - 1\tWithdrawal - 2\nCompound Interest - 3\tCheque - 4" << endl;
    }

    void optionswitch(int a)
    {
        switch (a)
        {
        case 1:
            cout << "Your balance is " << balance << " in account " << account_no;
            break;
        case 2:
            cash();
            break;
        case 3:
            compund();
            break;
        case 4:
            cheque();
            break;
        default:
            cout << "INVALID INPUT!!!" << endl;
            break;
        }
    }

    void cash()
    {
        cout << "Min - 500\tLimit - 5000/.\nAmount to Withdrawal : ";
        cin >> withdrawal;
        if (withdrawal <= 5000 & withdrawal >= 500)
        {
            balance = balance - withdrawal;
            cout << "Withdrawal Successfull..." << endl;
        }
        else
        {
            cout << "Amount ecceded.";
        }
    }

    void compund()
    {
        int input1;
        if (account_status == 1)
        {
            cout << "The compound Interest is 6.5 /." << endl;
            cout << "The compund Interest : " << compound_interest << endl;
            cout << "The balance : " << balance << endl;
            cout << "Want to Withdrawal to Account\nYes - 1\tNo - 2" << endl;
            cin >> input1;
            if (input1 == 1)
            {
                balance = balance + compound_interest;
                cout << "The Total amount : " << balance << endl;
            }
        }
        else if (account_status == 2)
        {
            cout << "You are elegible for Compound Interest." << endl;
        }
        else
        {
            cout << " ";
        }
    }

    void cheque()
    {
        if (account_status == 1)
        {
            cout << "You are not elegible for Cheque book." << endl;
        }
        else if (account_status == 2)
        {
            cout << "You are elegible for Cheque book." << endl;
        }
        else
        {
            cout << " ";
        }
    }

    void disp()
    {
        cout << "------Details of Customer-------" << endl;
        cout << "Customer Name : " << c_name << endl;
        cout << "Type of account : " << account << endl;
        cout << "Account number : " << account_no << endl;
        cout << "Compound Interest : " << compound_interest << endl;
        cout << "Balance : " << balance << endl;
    }
};

int main()
{
    account customer;

    cout << "Enter the details : " << endl;
    customer.input();
    customer.disp();
    return 0;
}