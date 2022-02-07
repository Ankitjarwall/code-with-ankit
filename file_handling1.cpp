#include <iostream>
#include <fstream>
#include <windows.h>
using namespace std;

class lpu
{
private:
    int p_roll = 0, p_mark = 0;
    string p_name;
    void display()
    {
        cout << "private Name : " << p_name << endl;
        cout << "private Roll : " << p_roll << endl;
        cout << "private Mark : " << p_mark << endl;
    }

public:
    void input(string fname, int froll, int fmark)
    {
        p_name = fname;
        p_roll = froll;
        p_mark = fmark;
        file_data(p_name, p_roll, p_mark);
    }

    void file_data(string fname, int froll, int fmark)
    {
        cout << "File input..." << endl;
        fstream data;
        data.open("File.txt", ios ::out|ios::in);
        data << "*Name : " << fname << endl;
        data << "*Roll : " << froll << endl;
        data << "*Mark : " << fmark << endl;
        data.close();
        Beep(400, 200);
        
    }
    void read_data()
    {
        cout << "Read file" << endl;
        string xyz;
        fstream readfile;
        readfile.open("File.txt");
        getline(readfile, xyz);
        cout << xyz;
        readfile.close();
        Beep(400, 200);
        
    }
};

int main()
{
    lpu bca;
    int roll = 0, mark = 0;
    string name;
    cout << "Enter the name : ";
    cin >> name;
    cout << "Enter the roll : ";
    cin >> roll;
    cout << "Enter the mark : ";
    cin >> mark;
    bca.input(name, roll, mark);
    bca.read_data();
    return 0;
}