#include <iostream>
using namespace std;

class general
{
    string name, address;
    int roll, con_no;

public:
    void personal()
    {
        cout << "Enter the name : ";
        cin >> name;
        cout << "Enter the Roll : ";
        cin >> roll;
        cout << "Enter the address : ";
        cin >> address;
        cout << "Enter the contact no. : ";
        cin >> con_no;
    }
    void disp()
    {
        cout << "----Personal info---\nName : " << name << endl;
        cout << "Roll No : " << roll << endl;
        cout << "Contact no. : " << con_no << endl;
        cout << "Address : " << address << endl;
    }
};

class accademic : private general
{
    int math, english, hindi, science, sst;
    float avg;

public:
    void input1()
    {
        personal();
        cout << "Enter the mark :- \nEnglsih : ";
        cin >> english;
        cout << "Hindi : ";
        cin >> hindi;
        cout << "Math : ";
        cin >> math;
        cout << "Science : ";
        cin >> science;
        cout << "Sst : ";
        cin >> sst;
        avg = (english + hindi + math + sst + science) / 5.0;
    }
    void disp1()
    {
        disp();
        cout << "\n-----Details----" << endl;
        cout << "Hindi mark : " << hindi << endl;
        cout << "English mark : " << english << endl;
        cout << "Math mark : " << math << endl;
        cout << "Science mark : " << science << endl;
        cout << "Sst mark : " << sst << endl;
        cout << "Average : " << avg << endl;
    }
};

int main()
{
    
    accademic acc_info;

    acc_info.input1();
    acc_info.disp1();

    return 0;
}