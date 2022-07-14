// #include <fstream>
// #include <iostream>
// using namespace std;
// class lpu
// {
// private:
//     int p_roll;
//     string p_name;

// public:
//     int pub_roll;
//     string pub_name;

//     void file_input(string fname, int froll)
//     {
//         p_name = fname;
//         p_roll = froll;

//         fstream input;
//         input.open("file1.txt", ios::app);
//         input << "Name : " << p_name << endl;
//         input << "Roll : " << p_roll << endl;
//         input.close();
//         cout << "File input.";
//     }
// };

// int main()
// {
//     int roll, data;
//     string name;

//     cout << "Enter the number of input : ";
//     cin >> data;
//     lpu bca[data];

//     for (int i = 0; i < data; i++)
//     {
//         cout << "Enter the name : ";
//         cin >> name;
//         cout << "Enter the roll : ";
//         cin >> roll;
//         bca[i].file_input(name, roll);
//     }
    

//     return 0;
// }


#include <iostream>
#include <fstream>
//#define base 200;
using namespace std;

class bill
{
private:
    string name;
    int call;
    float total = 200;

public:
    void get()
    {
        cout << "Enter User name...";
        cin >> name;
        cout << "\nEnter number of calls....";
        cin >> call;
    }
    void calculate()
    {

        if (call > 100 && call <= 150)
        {
            total = total + (call - 100) * 0.6;
        }
        else if (call > 150 && call <= 200)
        {
            total = total + (call - 100) * 0.5;
        }
        else if (call > 200)
        {
            total = total + (call - 100) * 0.4;
        }
    }
    void disp()
    {
        cout << "The total bill generated is of Rs " << total << endl;
    }
    void save()
    {
        fstream new_file;
        new_file.open("new_file.txt", ios::out | ios::app);
        if (!new_file)
        {
            cout << "File creation failed";
        }
        else
        {
            new_file << "Name of the person : " << name << "  calls made:" << call << "  Total bill:" << total<<"\n";

            new_file.close(); // Step 4: Closing file
        }
    }
};
int main()
{
    bill ovj;
    ovj.get();
    ovj.calculate();
    ovj.save();
    ovj.disp();
    return 0;
}