#include <iostream>
#include <cstring>
using namespace std;
class bca
{

    int p_age = 0, p_mark = 0;
    //char p_name[20];

    void p_user()
    {
        //cout << "Name : " << p_name << endl;
        cout << "Age : " << p_age << endl;
        cout << "Mark : " << p_mark << endl;
        cout << "Data uploaded in Private class.";
    }

public:
    int t_age = 0, t_mark = 0;
    //char t_name[20];

    void user(int f_age, int f_mark)//, char f_name[20])
    {
        bca p_class;
        p_age = f_age;
        p_mark = f_mark;
        //strcpy(p_name, f_name);
        //cout << "Public Name : " << t_name << endl;
        cout << "Public Age : " << t_age << endl;
        cout << "Public Mark : " << t_mark << endl;
        cout << "Public Data uploaded.\n";
        p_class.p_user();
    }
};
int main()
{
    int input = 0, age = 0, mark = 0;
    //char name[20];
    cout << "Enter the number of input students : ";
    cin >> input;
    for (int i = 1; i <= input; i++)
    {
        bca data[i];
        // cout << "Enter the Name : ";
        // cin >> name;
        cout << "Enter the age : ";
        cin >> age;
        cout << "Enter the mark : ";
        cin >> mark;
        //cout << "Public Name : " << t_name << endl;
        cout << "Direct input age : " << age << endl;
        cout << "Direct input Mark : " << mark << endl;
        data[i].user(age, mark);//, name);
    }
    return 0;
}