#include <fstream>
#include <iostream>
using namespace std;
class lpu
{
private:
    int p_roll;
    string p_name;

public:
    int pub_roll;
    string pub_name;

    void file_input(string fname, int froll)
    {
        p_name = fname;
        p_roll = froll;

        fstream input;
        input.open("file1.txt", ios::app);
        input << "Name : " << p_name << endl;
        input << "Roll : " << p_roll << endl;
        input.close();
        cout << "File input.";
    }
};

int main()
{
    int roll, data;
    string name;

    cout << "Enter the number of input : ";
    cin >> data;
    lpu bca[data];

    for (int i = 0; i < data; i++)
    {
        cout << "Enter the name : ";
        cin >> name;
        cout << "Enter the roll : ";
        cin >> roll;
        bca[i].file_input(name, roll);
    }
    

    return 0;
}